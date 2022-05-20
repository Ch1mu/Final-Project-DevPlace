package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.Language;
import FinalProject.ApiMessageSystem.Repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository lR;

    public List<Language> getAll()
    {
        return lR.findAll();
    }

    public Language getById(long id)
    {
        return lR.getById(id);
    }
    public Language getByName(String name)
    {
        return lR.getByName(name);
    }
}
