package FinalProject.MvcMessages.Controllers;


import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.LanguagePort;
import FinalProject.MvcMessages.Controllers.Apis.MessagesApi.Ports.PersonPort;
import FinalProject.MvcMessages.Controllers.Services.UserService;
import FinalProject.MvcMessages.Models.Account.MyUser;
import FinalProject.MvcMessages.Models.Adapters.UserAdapter;
import FinalProject.MvcMessages.Models.Language;
import FinalProject.MvcMessages.Models.UserPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService uS;
    @Autowired
    private PersonPort pP;
    @Autowired
    private LanguagePort lp;

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



    @GetMapping("/update/{username}")
    public String updateForm(@PathVariable("username") String username, Model model){
        UserAdapter userAdapter = new UserAdapter();
        UserPerson userPerson = pP.getByUsername(username);
        MyUser myUser = uS.findByUsername(username);
        userAdapter.setLanguageName(userPerson.getLanguage().getName());
        userAdapter.setUsername(userPerson.getUsername());
        userAdapter.setDni(userPerson.getDni());
        userAdapter.setFirstName(userPerson.getFirstName());
        userAdapter.setLastName(userPerson.getLastName());
        model.addAttribute("user", userAdapter);
        model.addAttribute("languages", lp.getAll());
        return "UserTemplates/update";
    }

    @PostMapping("/update/{username}")
    public String updateUser(@ModelAttribute("user") UserAdapter uA, @PathVariable("username")String username, RedirectAttributes redirect){
        uA.setLanguage(lp.getByName(uA.getLanguageName()));
        uS.update(uA);
        redirect.addFlashAttribute("message", "User Updated");
        return "redirect:/user/profile";
    }

}
