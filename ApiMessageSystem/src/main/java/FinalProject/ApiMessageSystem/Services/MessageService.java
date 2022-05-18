package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.Chat;
import FinalProject.ApiMessageSystem.Models.Message;
import FinalProject.ApiMessageSystem.Models.MessagePerChat;
import FinalProject.ApiMessageSystem.Repositories.ChatRepository;
import FinalProject.ApiMessageSystem.Repositories.MessagePerChatRepository;
import FinalProject.ApiMessageSystem.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository mR;
    @Autowired
    private MessagePerChatRepository mpcR;
    @Autowired
    private ChatRepository cR;

    public List<Message> getAll()
    {
        return mR.findAll();
    }
    public List<Message> getByUserAndChat(String username, long chatId)
    {
        return mR.getByUserAndChat(username,  chatId);
    }

    public List<Message> getByChat(long chatId)
    {
        return  mR.getByChat(chatId);
    }

    public Message save(Message msg, long chatId)
    {

        try
        {
           Message msge = mR.save(msg);
            Chat chat = cR.getById(chatId);
            MessagePerChat mpc = new MessagePerChat(chat, msg);
            mpcR.save(mpc);
            return  msge;
        }
        catch (Exception e)
        {
            return  null;
        }
    }
    public boolean delete(long id_msg)
    {
        try {
            mR.deleteById(id_msg);
            return true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }
    }
    @Transactional
    public Message update(String newContent, long id)
    {
        Optional<Message> message = mR.findById(id);
        if(!message.isEmpty())
        {
            Message msg = message.get();
            msg.setIdMessage(id);
            msg.setContent(newContent);
            return  mR.save(msg);
        }
        else {
            return  null;
        }

    }

}
