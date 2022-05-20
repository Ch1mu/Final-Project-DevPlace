package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters;

import FinalProject.MvcMessages.Models.Chat;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.PersonPerChat;
import FinalProject.MvcMessages.Models.UserPerson;
import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<PersonPerChat> getAllUsersPerChat(long chatId){

        RestTemplate rt = new RestTemplate();
        List<PersonPerChat> chats;
        chats = rt.getForObject(url+"allUsers/" + chatId, ArrayList.class);
        return chats;
    }

    public void delete(long idChat, String username) {

        RestTemplate rt = new RestTemplate();
        rt.delete(url  + idChat +"/"+username);
    }

    public void newChat(ArrayList<UserPerson> ups) {

        RestTemplate rt = new RestTemplate();
         rt.postForObject(url + "new", ups ,ArrayList.class);
    }
}
