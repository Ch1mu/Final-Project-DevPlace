package FinalProject.ApiMessageSystem.Controllers;

import FinalProject.ApiMessageSystem.Models.Message;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService ms;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        try {
            List<Message> messages = ms.getAll();
            if (messages.isEmpty()) {
                return ResponseEntity.status(204).body("Empty. There are no messsages");
            }
            return ResponseEntity.status(200).body(messages);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error.");
        }
    }

    @GetMapping("/{username}/{chatId}")
    public ResponseEntity<Object> getByUserAndChat(@PathVariable("username") String username,
                                                   @PathVariable("chatId") long chatId) {
        List<Message> msgs = ms.getByUserAndChat(username, chatId);
        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).body("No messages.");
        } else {
            return ResponseEntity.status(200).body(msgs);
        }
    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Object> getByChat(@PathVariable("chatId") long chatId) {
        List<Message> msgs = ms.getByChat(chatId);
        if (msgs.isEmpty()) {
            return ResponseEntity.status(204).body("No chat found for the specified Id");
        } else {
            return ResponseEntity.status(200).body(msgs);
        }
    }

    @PostMapping("/save/{chatId}")
    public ResponseEntity<Object> save(@PathVariable("chatId") long chatId, @RequestBody Message msg) {
        boolean flag = ms.save(msg, chatId);
        if (flag) {
            return ResponseEntity.status(200).body("Sucess.");
        } else {
            return ResponseEntity.status(500).body("Error.");
        }
    }

    @DeleteMapping("/{idmsg}")
    public ResponseEntity<Object> deleteMessage(@PathVariable("id_msg") long id_msg) {
        boolean flag = ms.delete(id_msg);
        if (!flag) {
            return ResponseEntity.status(204).body("There is no message.");
        } else {
            return ResponseEntity.status(200).body("Success.");
        }
    }

    @PutMapping("/update/{idmsg}")
    public ResponseEntity<Object> update(@PathVariable("idmsg") long idmsg, @RequestBody String newContent){
        Message newMessage = ms.update(newContent, idmsg);
        if(newMessage == null){
            return ResponseEntity.status(204).body("Empty");
        } else{
            return ResponseEntity.status(200).body("Updated.");
        }
    }



}

