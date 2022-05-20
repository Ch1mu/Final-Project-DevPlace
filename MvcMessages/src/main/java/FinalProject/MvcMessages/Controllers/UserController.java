package FinalProject.MvcMessages.Controllers;

import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService uS;
    @Autowired
    private PersonPort pP;

    @GetMapping("/home")
    public String userHome()
    {
        return "UserTemplates/home";
    }

    @GetMapping("/profile")
    public String profile(Model model)
    {
        model.addAttribute("user", pP.getByUsername(uS.getSessionUsername()));
        return "UserTemplates/profile";
    }

}
