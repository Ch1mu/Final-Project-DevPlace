package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters;

import FinalProject.MvcMessages.Models.Chat;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.PersonPerChat;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ChatAdapter {
    private final String url = "http://localhost:8080/chats/";
    public ArrayList<Chat> findAll() {

        RestTemplate rt = new RestTemplate();
        ArrayList<Chat> chats;
        chats = rt.getForObject(url,ArrayList.class);
        return chats;
    }

    public Chat getById(long chatId){

        RestTemplate rt = new RestTemplate();
        Chat chats;
        chats = rt.getForObject(url +chatId, Chat.class);
        return chats;
    }
    public List<PersonPerChat> getChatsPerUser(String username){

        RestTemplate rt = new RestTemplate();
        List<PersonPerChat> chats;
        chats = rt.getForObject(url+"all/" + username, ArrayList.class);
        return chats;
    }

    public void delete(long idChat) {

        RestTemplate rt = new RestTemplate();
        rt.delete(url  + idChat);
    }

    public void newChat(UserPerson u1, UserPerson u2) {

        RestTemplate rt = new RestTemplate();
        rt.postForObject(url + "save/" + u1, u2,UserPerson.class, UserPerson.class);
    }
}
