package FinalProject.MvcMessageSystem.Controllers.Apis.MessagesApi.Adapters;

import FinalProject.MvcMessageSystem.Models.Message;
import FinalProject.MvcMessageSystem.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageAdapter {
    private final String url = "http://localhost:8080/messages/";
    @Autowired
    public ArrayList<Message> findAll() {

        RestTemplate rt = new RestTemplate();
        ArrayList<Message> messages;
        messages = rt.getForObject(url, ArrayList.class);
        return messages;
    }

    public List<Message> getByUserAndChat(String username, long chatId){

        RestTemplate rt = new RestTemplate();
        ArrayList<Message> messages;
        messages = rt.getForObject(url + username + "/" + chatId, ArrayList.class);
        return messages;
    }
    public List<Message> getByChat(long chatId){

        RestTemplate rt = new RestTemplate();
        ArrayList<Message> messages;
        messages = rt.getForObject(url + chatId, ArrayList.class);
        return messages;
    }

    public void delete(long idMsg) {

        RestTemplate rt = new RestTemplate();
        rt.delete(url  + idMsg);
    }

    public void save(Message es, long chatId) {

        RestTemplate rt = new RestTemplate();
        rt.postForObject(url + "save/" + chatId, es, Message.class);
    }
}
