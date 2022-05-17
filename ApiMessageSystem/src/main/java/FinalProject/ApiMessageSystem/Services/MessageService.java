package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository mR;
}
