package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.ChatPort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatPort cP;
    @Autowired
    private UserService uS;
    @Autowired
    private PersonPort pP;
    @GetMapping("/all/{username}")
    public String getChatsPerUser(@PathVariable("username") String username, Model model)
    {
        String userChatName ="";
        model.addAttribute("chats", cP.getChatsPerUser(username));
        model.addAttribute("newChat", userChatName);
        return "ChatTemplates/chats";
    }
    @GetMapping("/redirect")
    public String redirectToChats()
    {
       String username = uS.getSessionUsername();
        return "redirect:/chats/all/" +username;
    }
    @PostMapping("/new")
    public String newChat(@ModelAttribute("newChat") String user)
    {
        ArrayList<UserPerson> ups = new ArrayList<>();
        String username = uS.getSessionUsername();
        ups.add(pP.getByUsername(username));
        UserPerson up =  pP.getByUsername(user);
        if(up!=null && !up.getUsername().equals(username))
        {
            ups.add(up);
            cP.newChat(ups);
        }

        return "redirect:/chats/all/" +username;
    }
}
