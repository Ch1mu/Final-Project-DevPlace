package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.ChatPort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.PersonPerChat;
import FinalProject.MvcMessages.Models.UserPerson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatPort cP;
    @Autowired
    private UserService uS;
    @Autowired
    private PersonPort pP;
    @GetMapping("/all")
    public String getChatsPerUser(Model model)
    {
        String userChatName ="";
        String username = uS.getSessionUsername();
        model.addAttribute("chats", cP.getChatsPerUser(username));
        model.addAttribute("newChat", userChatName);
        return "ChatTemplates/chats";
    }

    @PostMapping("/new")
    public String newChat(@ModelAttribute("newChat") String user)
    {
        boolean flag = true;
        ArrayList<UserPerson> ups = new ArrayList<>();
        String username = uS.getSessionUsername();
        ups.add(pP.getByUsername(username));
        UserPerson up =  pP.getByUsername(user);
        ObjectMapper mapper = new ObjectMapper();
        List<PersonPerChat> list= new ArrayList<>();


        if(up!=null && !up.getUsername().equals(username) ) {

            if(cP.getChatsPerUser(username) != null)
            {
               list=  mapper.convertValue(cP.getChatsPerUser(username),  new TypeReference<List<PersonPerChat>>() { });
            }



            for(PersonPerChat ppc: list)
            {
                if(ppc.getUser().getUsername().equals(up.getUsername()))
                {
                    flag = false;
                }

            }
            if(flag)
            {
                ups.add(up);
                cP.newChat(ups);
            }

        }


        return "redirect:/chats/all/";
    }
}
