package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.ChatPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    ChatPort cP;
    @Autowired
    UserService uS;
    @GetMapping("/all/{username}")
    public String getChatsPerUser(@PathVariable("username") String username, Model model)
    {


        model.addAttribute("chats", cP.getChatsPerUser(username));

        return "ChatTemplates/chats";
    }
}
