package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters.ChatAdapter;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters.MessageAdapter;
import FinalProject.MvcMessages.Models.Chat;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.PersonPerChat;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChatPort {
    @Autowired
    ChatAdapter cA;

    //SAVE
    public void newChat(UserPerson u1, UserPerson u2)
    {
        cA.newChat(u1, u2);
    }


    //GET ALL
    public ArrayList<Chat> getAll()
    {
        return cA.findAll();
    }


    //GET BY ID
    public Chat getById(long chatId)
    {
        return cA.getById(chatId);
    }

    //GET BY ALL CHATS BY USER
    public List<PersonPerChat> getChatsPerUser(String user) {
        return cA.getChatsPerUser(user);
    }


    //DELETE
    public void delete(long chatId) {
        cA.delete(chatId);
    }
}
