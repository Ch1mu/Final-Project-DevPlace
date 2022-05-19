package FinalProject.ApiMessageSystem.Services;


import FinalProject.ApiMessageSystem.Models.Security.MyUser;
import FinalProject.ApiMessageSystem.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser u = ur.findByUsername(username);

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

    public MyUser save(MyUser u )
    {
        return ur.save(u);
    }
    public MyUser findByUsername(String username)
    {
       MyUser u = ur.findByUsername(username);
        if(u != null)
        {
            return u;
        }
        else
        return null;

    }
    
}
