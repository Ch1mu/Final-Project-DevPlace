package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters.MessageAdapter;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters.PersonAdapter;
import FinalProject.MvcMessages.Models.Message;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagePort {
    @Autowired
    MessageAdapter mA;

    //SAVE
    public void save(Message msg, long chatId)
    {
       mA.save(msg, chatId);
    }


    //GET ALL
    public ArrayList<Message> getAll()
    {
        return mA.findAll();
    }


    //GET BY ID AND USER
    public List<Message> getByUserAndChat(String user, long chatId)
    {
        return mA.getByUserAndChat(user, chatId);
    }

    //GET BY CHAT
    public List<Message> getByChat(long chatId) {
        return mA.getByChat(chatId);
    }


    //DELETE
    public void delete(long msgId) {
        mA.delete(msgId);
    }
}
