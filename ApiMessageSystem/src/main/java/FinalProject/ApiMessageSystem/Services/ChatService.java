package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.*;
import FinalProject.ApiMessageSystem.Repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ChatService {
    @Autowired
    private ChatRepository cR;
    @Autowired
    private PersonPerChat ppcR;

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

            PersonPerChat mpc = new PersonPerChat(, msg);
            mpcR.save(mpc);
            return  true;
        }
        catch (Exception e)
        {
            return  false;
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
}
