package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.PersonPerChat;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Repositories.PersonPerChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PpcService {
    @Autowired
    private PersonPerChatRepository ppcR;



    public boolean save(PersonPerChat uP)
    {
        try
        {
            ppcR.save(uP);
            return  true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public PersonPerChat getReceiver(String username, long chatId)
    {
        return ppcR.getChatUserName(username, chatId);
    }
    public boolean delete(long chatId)
    {
        try {
            ppcR.deleteById(chatId);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public PersonPerChat takeASinglePerson(long chatId,  String username){
        PersonPerChat pp =  ppcR.takeASinglePerson(chatId, username);
        if(pp == null)
        {
            return null;
        }
        else
        {
            return pp;
        }
    }
    public List<PersonPerChat> getAllPersonsPerChat(long chatId)
    {
        return ppcR.getAllPersonsPerChat(chatId);
    }
}
