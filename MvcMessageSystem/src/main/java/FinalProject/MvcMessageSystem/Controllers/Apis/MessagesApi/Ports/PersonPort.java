package FinalProject.MvcMessageSystem.Controllers.Apis.MessagesApi.Ports;

import FinalProject.MvcMessageSystem.Controllers.Apis.MessagesApi.Adapters.PersonAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonPort {
    @Autowired
    PersonAdapter pA;

    public void save(Users user)
    {
        uR.save(user);
    }

    //I Premium
    public boolean isPremium(Users user)
    {
        return uR.isAdmin(user.getEmail());
    }

    //GET ALL
    public ArrayList<Users> getAll()
    {
        return (ArrayList<Users>) uR.findAll();
    }


    //GET BY ID
    public Users getByEmail(String email)
    {
        return uR.getByEmail(email);
    }

    //DELETE
    public void delete(long id) {
        uR.delete(id);
    }
}
