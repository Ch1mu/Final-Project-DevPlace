package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.Message;
import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository mR;

    public List<Message> getAll()
    {
        return mR.findAll();
    }
    public List<Message> getByUser()
    {
        return mR.findAll();
    }
}
