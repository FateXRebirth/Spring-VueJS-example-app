package example.Controller;

import example.Entity.LoginForm;
import example.Entity.Person;
import example.Entity.RegisterForm;
import example.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;


/**
 * Created by FateXRebirth on 20/10/2017.
 */

@Controller
@RequestMapping("/")
public class ViewController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String index(HttpSession session) {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(LoginForm loginForm, HttpSession session) {
        if(session.getAttribute("uid") != null){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(LoginForm loginForm, HttpSession session) {

        Person person = personService.getPersonByUsername(loginForm.getUsername());
        session.setAttribute("uid", person.getUsername());

        return "redirect:/success";
    }

    @GetMapping("/register")
    public String registerForm(RegisterForm registerForm, HttpSession session) {
        if(session.getAttribute("uid") != null){
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(RegisterForm registerForm, HttpSession session) {

        Person newUser = new Person(registerForm.getUsername(), registerForm.getPassword(), registerForm.getEmail(), registerForm.getType());

        personService.create(newUser);
        session.setAttribute("uid", newUser.getUsername());

        return "redirect:/success";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("uid") != null){
            session.removeAttribute("uid");
        }
        return "redirect:/";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if(session.getAttribute("uid") == null) {
            return "redirect:/login";
        }
        return "dashboard";
    }

    @GetMapping("/success")
    public String Success() {
        return "success";
    }

    @GetMapping("/failure")
    public String NotFound() {
        return "failure";
    }

    @GetMapping("/*")
    public String Error() {
        return "failure";
    }

}
