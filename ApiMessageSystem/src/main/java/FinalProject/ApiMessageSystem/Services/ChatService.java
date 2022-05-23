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
    private PpcService ppcS;

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

    public Chat newChat(ArrayList<UserPerson> upl, String name, boolean isGroup)
    {
        try
        {
           Chat c = cR.save(new Chat(name, isGroup));
            for(UserPerson u: upl)
            {
                PersonPerChat mpc1 = new PersonPerChat(c, u);
                ppcS.save(mpc1);
            }


            return  c;
        }
        catch (Exception e)
        {
            return  null;
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
