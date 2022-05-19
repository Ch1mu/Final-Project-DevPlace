package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.*;
import FinalProject.ApiMessageSystem.Repositories.ChatRepository;
import FinalProject.ApiMessageSystem.Repositories.PersonPerChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ChatService {
    @Autowired
    private ChatRepository cR;
    @Autowired
    private PersonPerChatRepository ppcR;

    public List<Chat> getAll()
    {
        return cR.findAll();
    }


    public Chat getById(long chatId)
    {
        return cR.getById(chatId);
    }
    public List<Chat> getChatPerUser(String username)
    {
        return cR.getChatPerUser(username);
    }
    public PersonPerChat getReceiver(String username, long chatId)
    {
        return ppcR.getChatUserName(username, chatId);
    }
    public boolean newChat(ArrayList<UserPerson> upl)
    {
        try
        {
           Chat c = cR.save(new Chat());
            for(UserPerson u: upl)
            {
                PersonPerChat mpc1 = new PersonPerChat(c, u);
                ppcR.save(mpc1);
            }


            return  true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }

    public boolean delete(long chatId)
    {
        try {
            cR.deleteById(chatId);
            return true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }
    }
}
