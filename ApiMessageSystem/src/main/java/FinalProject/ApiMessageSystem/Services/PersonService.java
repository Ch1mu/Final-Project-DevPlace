package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Repositories.PersonRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository pR;

    public List<UserPerson> getAll()
    {
        return pR.findAll();
    }

    public UserPerson getByUsername(String username)
    {
        UserPerson uP = pR.getByUsername();

            return pR.getByUsername();

    }

    public boolean save(UserPerson uP)
    {
        try
        {
            pR.save(uP);
            return  true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public boolean delete(String dni)
    {
        try {
            pR.deleteByDni(dni);
            return true;
        }
        catch (EmptyResultDataAccessException e)
        {
            return false;
        }
    }
}
