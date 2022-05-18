package FinalProject.MvcMessages.Controllers.Services;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Repositories.UserRepository;
import FinalProject.MvcMessages.Models.Account.MyUser;
import FinalProject.MvcMessages.Models.Account.Role;
import FinalProject.MvcMessages.Models.Adapters.UserAdapter;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private PersonPort pp;
    @Autowired
    PasswordEncoder pE;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser u = ur.getByUsername(username);
        if (u == null)
        {
            throw new UsernameNotFoundException(username);
        }
        else{

            Set<GrantedAuthority> set = new HashSet<>();
            set.add(new SimpleGrantedAuthority(u.getRole().getName()));

            return new User(u.getUsername(), u.getPassword(), set);
        }

    }

    public boolean save(UserAdapter u )
    {
        try {
            Role role = new Role();
            role.setId(2);
            role.setName("USER");
            MyUser myuser = new MyUser(u.getUsername(), pE.encode(u.getPassword()), role);
            UserPerson person = new UserPerson(u.getUsername(), u.getDni(), u.getFirstName(), u.getLastName(), u.getLanguage());
            ur.save(myuser);
            pp.save(person);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }

    public MyUser findByUsername(String username)
    {
        MyUser u = ur.getByUsername(username);
        if(u != null)
        {
            return u;
        }
        else
            return null;

    }
}
