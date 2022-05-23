package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.LanguagePort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.Adapters.UserAdapter;
import FinalProject.MvcMessages.Models.Language;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService uS;
    @Autowired
    private LanguagePort lp;
    @Autowired
    private PersonPort pP;

    @GetMapping("/login")
    public String loginPage() {

        return "LoginTemplates/login";
    }

    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("user", new UserAdapter());
        model.addAttribute("language", new Language());
        model.addAttribute("languages", lp.getAll());
        return "LoginTemplates/signup";
    }

    @PostMapping("/signup")
    public String performSignUp(@ModelAttribute("user")UserAdapter uA, RedirectAttributes redirect) {

        UserPerson p= pP.getByUsername(uA.getUsername());
        UserPerson dni = pP.getByDni(uA.getDni());

         if(p!= null){
            redirect.addFlashAttribute("message", "User already in use");
            return "redirect:/signup";
        }
        else if(dni!= null){
            redirect.addFlashAttribute("message", "Existing user with that DNI");
            return "redirect:/signup";
        }
        else
         {
                 uA.setLanguage(lp.getByName(uA.getLanguageName()));
                 boolean result = uS.save(uA);
                 redirect.addFlashAttribute("message", "User created Successfully");
                 return "redirect:/login";
         }




    }

}

