package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Token;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;



@Repository
public class TokenManager {
    private final String url = "http://localhost:8080/token";
    public String  generateToken() {

        RestTemplate rt = new RestTemplate();

        return rt.getForObject(url+"/user"+"/123" , String.class);
    }

}
