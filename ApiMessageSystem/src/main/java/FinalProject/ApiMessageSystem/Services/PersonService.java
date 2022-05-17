package FinalProject.ApiMessageSystem.Services;

import FinalProject.ApiMessageSystem.Models.UserPerson;
import FinalProject.ApiMessageSystem.Repositories.PersonRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository pR;

    public List<UserPerson> getAll()
    {
        return pR.findAll();
    }

    public UserPerson getByUsername(String username)
    {


            return pR.getByUsername(username);

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
    @Transactional
    public UserPerson update(UserPerson c, String username)
    {
        if(pR.getByUsername(username) != null)
        {
            c.setUsername(username);
            return  pR.save(c);
        }
        else {
            return  null;
        }

    }
}
