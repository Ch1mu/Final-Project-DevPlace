package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.PersonPerChat;
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

    public boolean delete(long chatId, String username)
    {
        try {
            ppcR.deletePersonFromChat(chatId, username);
            return true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }
    }
    public List<PersonPerChat> getAllPersonsPerChat(long chatId)
    {
        return ppcR.getAllPersonsPerChat(chatId);
    }
}
