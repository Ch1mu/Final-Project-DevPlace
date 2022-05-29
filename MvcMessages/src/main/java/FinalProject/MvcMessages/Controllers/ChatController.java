package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.ChatPort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.MessagePort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.Chat;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.PersonPerChat;
import FinalProject.MvcMessages.Models.UserPerson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatPort cP;

    @Autowired
    private MessagePort mP;

    @Autowired
    private UserService uS;
    @Autowired
    private PersonPort pP;

    @GetMapping("/all")
    public String getChatsPerUser(Model model) {
        String userChatName = "";
        String groupName = "";
        String username = uS.getSessionUsername();

        model.addAttribute("chats", cP.getChatsPerUser(username));
        model.addAttribute("newChat", userChatName);
        model.addAttribute("newGroup", groupName);
        return "ChatTemplates/chats";
    }

    @PostMapping("/filter")
    public String filterChats(@ModelAttribute("filter") String filter, Model model, RedirectAttributes redirect){
        String userChatName = "";
        String groupName = "";
        String username = uS.getSessionUsername();

        ObjectMapper mapper = new ObjectMapper();

        List<Chat> chats = mapper.convertValue(cP.getChatsPerUser(username), new TypeReference<List<Chat>>() { });

        ArrayList<Chat> fChat = new ArrayList<>();

        for(Chat c : chats) {
            if(c.getName().contains(filter)) {
                fChat.add(c);

            }
        }

        model.addAttribute("chats", fChat);
        model.addAttribute("newChat", userChatName);
        model.addAttribute("newGroup", groupName);
        return "ChatTemplates/chats";
    }

    @PostMapping("/new")
    public String newChat(@ModelAttribute("newChat") String user, RedirectAttributes redirect) {
        boolean flag = true;
        ArrayList<UserPerson> ups = new ArrayList<>();
        String username = uS.getSessionUsername();//get session username
        ups.add(pP.getByUsername(username)); //add user yo array
        UserPerson up = pP.getByUsername(user); //get person to add to our chat
        ObjectMapper mapper = new ObjectMapper();
        List<Chat> list = new ArrayList<>();


        if (up != null && !up.getUsername().equals(username)) { //if user to add, if trying to add ourself

            if (cP.getChatsPerUser(username) != null) { //if there are chats
                list = mapper.convertValue( cP.getChatsPerUser(username),new TypeReference<List<Chat>>() { //Get All Chats
                });
            }

            for (Chat chat : list) {

                if(!chat.isGroup()) //if is
                {

                    List<PersonPerChat> ppc = mapper.convertValue( cP.getAllUsersPerChat(chat.getIdChat()),new TypeReference<List<PersonPerChat>>() {});//Get All Chats
                    for(PersonPerChat p : ppc) {
                        if (p.getUser().getUsername().equals(up.getUsername())) { //if there already is a chat with that person
                            redirect.addFlashAttribute("message", "Error. Chat already Exists");
                            flag = false;
                        }
                    }
                }
            }
            if (flag) {
                ups.add(up);
                cP.newChat(ups, username + " & " + up.getUsername());
                redirect.addFlashAttribute("message", "Chat Created Successfully");
            }

        }


        return "redirect:/chats/all/";
    }

    @PostMapping("/newGroup")
    public String newGroup(@ModelAttribute("newGroup") String groupName, RedirectAttributes redirect) {
        ArrayList<UserPerson> ups = new ArrayList<>();
        String username = uS.getSessionUsername();
        ups.add(pP.getByUsername(username));
        cP.newGroup(ups, groupName);
        redirect.addFlashAttribute("message", "Group Created Successfully");
        return "redirect:/chats/all/";
    }

    @GetMapping("/delete/{chatId}")
    public String deletePersonFromChat(@PathVariable("chatId") long chatId, RedirectAttributes redirect) {
        cP.delete(chatId, uS.getSessionUsername());
        redirect.addFlashAttribute("message", "Chat Deleted.");
        return "redirect:/chats/all";
    }

    @PostMapping("/addPerson/{chatId}")
    public String addPersonToGroup(@ModelAttribute("add") String username, @PathVariable("chatId") long chatId, RedirectAttributes redirect) {

        boolean flag = true;
        UserPerson up = pP.getByUsername(username);
        ObjectMapper mapper = new ObjectMapper();
        List<Chat> list = new ArrayList<>();
        String myUser = uS.getSessionUsername();

        if (up != null && !up.getUsername().equals(myUser)) { //if user to add isnt null, if trying to add ourself

            List<PersonPerChat> ppc = mapper.convertValue(cP.getAllUsersPerChat(chatId), new TypeReference<List<PersonPerChat>>() {});
            for(PersonPerChat p : ppc) {

                if(p.getUser().getUsername().equals(username))
                {
                    redirect.addFlashAttribute("message", "User is already in this chat");
                    flag = false;
                }


            }

            if (flag) {
                redirect.addFlashAttribute("message", "User added Successfully");
                cP.addPersonToGroup(up, chatId);
            }


                }
        return "redirect:/messages/" +chatId;
    }






}


