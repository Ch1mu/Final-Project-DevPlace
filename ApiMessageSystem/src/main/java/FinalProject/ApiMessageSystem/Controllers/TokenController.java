package FinalProject.ApiMessageSystem.Controllers;


import FinalProject.ApiMessageSystem.Config.JwtTokenUtil;
import FinalProject.ApiMessageSystem.Models.Security.MyUser;
import FinalProject.ApiMessageSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private UserService uS;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jTU;

    @GetMapping("/{user}/{password}")
    public ResponseEntity createToken(@PathVariable("user") String user, @PathVariable("password") String password) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user, password));
        UserDetails uD = uS.loadUserByUsername(user);
        String token = jTU.generateToken(uD);
        return ResponseEntity.status(200).body(token);
    }
}