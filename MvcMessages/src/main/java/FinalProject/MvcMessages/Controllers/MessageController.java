package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.MessagePort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessagePort mP;
    @Autowired
    private PersonPort pp;
    @Autowired
    private UserService uS;
    @GetMapping("/{idChat}")
    public String getMessagesPerChat(@PathVariable("idChat") long idChat, Model model)
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userN = "";
        if (principal instanceof UserDetails) {

            userN = ((UserDetails) principal).getUsername();

        }

        Message msg = new Message(pp.getByUsername(userN));
        msg.setUp(pp.getByUsername(userN));
        try {
            model.addAttribute("messages", mP.getByChat(idChat));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("newMsg", msg);
        model.addAttribute("chatId", idChat);
        model.addAttribute("user", userN);
        return "ChatTemplates/messages";
    }

    @PostMapping("/save/{chatId}")
    public String sendMessages(@ModelAttribute("newMsg") Message msg, @PathVariable("chatId") long chatId)
    {
        String userN = uS.getSessionUsername();
        msg.setUp(pp.getByUsername(userN));
           mP.save(msg, chatId);
        return "redirect:/messages/" + chatId;
    }
}
