package FinalProject.MvcMessageSystem.Controllers.Apis.MessagesApi.Adapters;

import FinalProject.MvcMessageSystem.Models.UserPerson;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
@Repository
public class PersonAdapter {
    private final String url = "http://localhost:8080/persons/";
    @Autowired
    public ArrayList<UserPerson> findAll() {

        RestTemplate rt = new RestTemplate();
        ArrayList<UserPerson> users;
        users = rt.getForObject(url, ArrayList.class);
        return users;
    }

    public UserPerson getByUsername(String username){

        RestTemplate rt = new RestTemplate();
        UserPerson user;
        user = rt.getForObject(url + username, UserPerson.class);
        return user;
    }

    public void delete(String username) {

        RestTemplate rt = new RestTemplate();
        rt.delete(url + "delete/" + username);
    }

    public void save(UserPerson es) {

        RestTemplate rt = new RestTemplate();
        rt.postForObject(url + "save", es, UserPerson.class);
    }
}
