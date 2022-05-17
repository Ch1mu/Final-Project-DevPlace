package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.*;
import FinalProject.ApiMessageSystem.Repositories.ChatRepository;
import FinalProject.ApiMessageSystem.Repositories.PersonPerChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    public boolean newChat(UserPerson user1, UserPerson user2)
    {

        try
        {
           Chat c = cR.save(new Chat());

            PersonPerChat mpc1 = new PersonPerChat(c, user1 );
            PersonPerChat mpc2 = new PersonPerChat(c, user2);
            ppcR.save(mpc1);
            ppcR.save(mpc2);
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
