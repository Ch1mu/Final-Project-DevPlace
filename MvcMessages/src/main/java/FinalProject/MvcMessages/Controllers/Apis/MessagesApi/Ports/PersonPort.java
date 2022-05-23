package FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Adapters.PersonAdapter;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonPort {
    @Autowired
    PersonAdapter pA;

    public void save(UserPerson user)
    {
        pA.save(user);
    }



    //GET ALL
    public ArrayList<UserPerson> getAll()
    {
        return pA.findAll();
    }


    //GET BY ID
    public UserPerson getByUsername(String user)
    {
        return pA.getByUsername(user);
    }
    public UserPerson getByDni(String dni)
    {
        return pA.getByDni(dni);
    }

    //UPDATE

    public void update(UserPerson userPerson){
        pA.update(userPerson);
    }



    //DELETE
    public void delete(String user) {
        pA.delete(user);
    }
}
