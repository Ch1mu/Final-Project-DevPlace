package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters;


import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Token.TokenManager;
import FinalProject.MvcMessages.Models.Message;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;

import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Message> getByChat(long chatId) throws URISyntaxException {

       RestTemplate rt = new RestTemplate();
        ArrayList<Message> messages = new ArrayList<>();
        messages = rt.getForObject(url + chatId, ArrayList.class);

      /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tM.generateToken());

        try {

            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<List<Message>> response = rt.exchange(url+chatId, HttpMethod.GET, entity, .class);

             messages = new Gson().fromJson(response.getBody(), ArrayList.class);

        }
        catch (Exception eek) {
            System.out.println("** Exception: "+ eek.getMessage());
        }
*/

        return messages;
    }

    public void delete(long idMsg) {

        RestTemplate rt = new RestTemplate();
        rt.delete(url  + idMsg);
    }

    public void save(Message es, long chatId) {

        RestTemplate rt = new RestTemplate();
       rt.postForObject(url + "save/" + chatId, es, Message.class);

       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+ tM.generateToken());

        HttpEntity<Message> entity = new HttpEntity<Message>(es,headers);
        String result = rt.postForObject(url, entity, String.class); */
    }

    public List<Message> getByMessage(String message){
        RestTemplate rt = new RestTemplate();
        ArrayList<Message> messages;
        messages = rt.getForObject(url+"/filter/"+message, ArrayList.class);
        return messages;
    }
}
