package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters.LanguageAdapter;
import FinalProject.MvcMessages.Models.Chat;
import FinalProject.MvcMessages.Models.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LanguagePort {

    @Autowired
    private LanguageAdapter cA;

    //GET ALL
    public ArrayList<Language> getAll()
    {
        return cA.findAll();
    }

    //GET BY ID
    public Language getById(long chatId)
    {
        return cA.getById(chatId);
    }

    //GET BY NAME
    public Language getByName(String name)
    {
        return cA.getByName(name);
    }
}
