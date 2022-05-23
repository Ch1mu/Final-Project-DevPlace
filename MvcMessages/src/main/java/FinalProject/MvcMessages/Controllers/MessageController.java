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

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessagePort mP;
    @Autowired
    private PersonPort pp;
    @Autowired
    private UserService uS;
    @Autowired
    private ChatPort cP;

    @GetMapping("/{idChat}")
    public String getMessagesPerChat(@PathVariable("idChat") long idChat, Model model, List<Message> messages) throws URISyntaxException, IOException, IOException {
        boolean flag = false;
        boolean redirect = false;
        ObjectMapper mapper = new ObjectMapper();
        List<PersonPerChat> ppcs=  mapper.convertValue(cP.getAllUsersPerChat(idChat),  new TypeReference<List<PersonPerChat>>() { });

        String userN = uS.getSessionUsername();
        for(PersonPerChat ppc: ppcs)
        {
            if(ppc.getUser().getUsername().equals(userN))
            {
                flag = true;
                redirect = true;
            }

        }
        if(!redirect)
        {
            return "redirect:/chats/all";
        }

        if(flag) {

            Message msg = new Message(pp.getByUsername(userN));
            msg.setUp(pp.getByUsername(userN));
            if(messages == null)
            messages=  mapper.convertValue(mP.getByChat(idChat), new TypeReference<List<Message>>() { });

            //Translate Config
            Translate gT = TranslateOptions
                    .newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("APIkey.json")))
                    .build().getService();

            Translation translation;
            Detection detection;
            String languageDetected;

            UserPerson userSession = pp.getByUsername(uS.getSessionUsername());
            String languageUser = userSession.getLanguage().getCode();

            if (messages!=null) {
                for (Message m : messages) {
                    detection = gT.detect(m.getContent());
                    languageDetected = detection.getLanguage();
                    if (!languageUser.equals(languageDetected)) {
                        translation = gT.translate(m.getContent(),
                                Translate.TranslateOption.sourceLanguage(languageDetected),
                                Translate.TranslateOption.targetLanguage(languageUser));
                        m.setContent(translation.getTranslatedText());
                    }
                }
            }

            model.addAttribute("messages", messages);

            String add ="";
            Chat chat = cP.getById(idChat);
            model.addAttribute("add",  add);
            model.addAttribute("newMsg", msg);
            model.addAttribute("chat", chat);
            model.addAttribute("user", userN);
            return "ChatTemplates/messages";
        }
        else
        {
            return"redirect:/chats/all";
        }
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
