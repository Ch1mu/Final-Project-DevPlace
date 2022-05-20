package FinalProject.ApiMessageSystem.Controllers;

import FinalProject.ApiMessageSystem.Models.Chat;
import FinalProject.ApiMessageSystem.Models.PersonPerChat;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Repositories.ChatRepository;
import FinalProject.ApiMessageSystem.Services.ChatService;
import FinalProject.ApiMessageSystem.Services.PpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService cs;
   @Autowired
   private PpcService ppc;


    @GetMapping("/")
    public ResponseEntity<Object> getAll(){
        List<Chat> chats = cs.getAll();
        if(chats.isEmpty()){
            return ResponseEntity.status(204).body(chats);
        }
        else {
            return ResponseEntity.status(200).body(chats);
        }
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Object> getByID(@PathVariable("chatId") long chatId){

        Chat chat = cs.getById(chatId);
        if(chat == null){
            return ResponseEntity.status(204).body("Empty");
        } else {
            return ResponseEntity.status(200).body(chat);
        }
    }


    @GetMapping("/all/{username}")
    public ResponseEntity<Object> getChatPerUser(@PathVariable("username") String username){

        List<Chat> chats = cs.getChatPerUser(username);

        ArrayList<PersonPerChat> ppc = new ArrayList<>();

        for(int i = 0; i<chats.size(); i++)
        {
             ppc.add(cs.getReceiver(username, chats.get(i).getIdChat()));
        }

        if(ppc.isEmpty()){
            return ResponseEntity.status(204).body("Empty");
        } else {
            return ResponseEntity.status(200).body(ppc);
        }
    }

    @PostMapping("/new")
    public void newChat(@RequestBody ArrayList<UserPerson> upl){
        Chat flag = cs.newChat(upl);
        if(flag != null){
             ResponseEntity.ok(200);
        } else {
             ResponseEntity.status(400).body("Error.");
        }
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Object> deleteChat(@PathVariable("chatID") long chatId){
        boolean flag = cs.delete(chatId);
        if(flag){
            return ResponseEntity.status(200).body("Success.");
        } else {
            return ResponseEntity.status(400).body("Error.");
        }
    }

    @DeleteMapping("/{chatId}/{user}")
    public ResponseEntity<Object> deletePersonFromChat(@PathVariable("chatID") long chatId, @PathVariable("user") String user){
        boolean flag = ppc.delete(chatId, user);
        if(flag){
            return ResponseEntity.status(200).body("Success.");
        } else {
            return ResponseEntity.status(400).body("Error.");
        }
    }


}
