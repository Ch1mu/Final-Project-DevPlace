package FinalProject.ApiMessageSystem.Controllers;

import FinalProject.ApiMessageSystem.Models.Chat;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    ChatService cs;

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

    @GetMapping("/{username}")
    public ResponseEntity<Object> getChatPerUser(@PathVariable("username") String username){
        List<Chat> chat = cs.getChatPerUser(username);
        if(chat.isEmpty()){
            return ResponseEntity.status(204).body("Empty");
        } else {
            return ResponseEntity.status(200).body(chat);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<Object> newChat(@RequestBody UserPerson user1, @RequestBody UserPerson user2){
        boolean flag = cs.newChat(user1,user2);
        if(flag){
            return ResponseEntity.status(200).body("Success.");
        } else {
            return ResponseEntity.status(400).body("Error.");
        }
    }

    @DeleteMapping("/{chatId}")
    public ResponseEntity<Object> deleteChat(@PathVariable("chatID") long chatId){
        boolean flag = cs.delete(chatId);
        if(flag){
            return ResponseEntity.status(200).body("Sucess.");
        } else {
            return ResponseEntity.status(400).body("Error.");
        }
    }


}