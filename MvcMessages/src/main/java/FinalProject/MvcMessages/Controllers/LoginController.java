package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.LanguagePort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.Adapters.UserAdapter;
import FinalProject.MvcMessages.Models.Language;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService uS;
    @Autowired
    private LanguagePort lp;

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
    public String performSignUp(@ModelAttribute("user") UserAdapter uA) {
        uA.setLanguage(lp.getByName(uA.getLanguageName()));
        boolean result = uS.save(uA);

        return "redirect:/login";
    }

}

