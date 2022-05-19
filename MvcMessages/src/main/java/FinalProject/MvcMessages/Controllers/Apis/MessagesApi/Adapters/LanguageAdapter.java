package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters;

import FinalProject.MvcMessages.Models.Chat;
import FinalProject.MvcMessages.Models.Language;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Repository
public class LanguageAdapter {
    private final String url = "http://localhost:8080/languages/";

    public ArrayList<Language> findAll() {

        RestTemplate rt = new RestTemplate();
        ArrayList<Language> chats;
        chats = rt.getForObject(url, ArrayList.class);
        return chats;
    }

    public Language getById(long chatId) {

        RestTemplate rt = new RestTemplate();
        Language chats;
        chats = rt.getForObject(url + chatId, Language.class);
        return chats;
    }
    public Language getByName(String name) {

        RestTemplate rt = new RestTemplate();
        Language chats;
        chats = rt.getForObject(url +"/name/"+ name, Language.class);
        return chats;
    }
}