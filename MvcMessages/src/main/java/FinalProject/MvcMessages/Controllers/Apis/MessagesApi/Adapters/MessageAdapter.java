package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters;


import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Token.TokenManager;
import FinalProject.MvcMessages.Models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageAdapter {
    @Autowired
    private TokenManager tM;
    private final String url = "http://localhost:8080/messages/";
    public ArrayList<Message> findAll() {

        RestTemplate rt = new RestTemplate();
        ArrayList<Message> messages;
        messages = rt.getForObject(url,ArrayList.class);
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ tM.generateToken());

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        messages = rt.getForObject(url + chatId,ArrayList.class);
        return messages;
    }

    public void delete(long idMsg) {

        RestTemplate rt = new RestTemplate();
        rt.delete(url  + idMsg);
    }

    public void save(Message es, long chatId) {

        RestTemplate rt = new RestTemplate();
        //rt.postForObject(url + "save/" + chatId, es, Message.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ tM.generateToken());

        HttpEntity<Message> entity = new HttpEntity<Message>(es,headers);
        String result = rt.postForObject(url, entity, String.class);
    }
}
