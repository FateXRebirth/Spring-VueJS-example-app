package example.Controller;

import example.Request.LoginRequest;
import example.Entity.Person;
import example.Request.RegisterRequest;
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
    public String loginForm(LoginRequest loginRequest, HttpSession session) {
        if(session.getAttribute("uid") != null){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(LoginRequest loginRequest, HttpSession session) {

        Person person = personService.getPersonByUsername(loginRequest.getUsername());
        session.setAttribute("uid", person.getUsername());

        return "redirect:/success";
    }

    @GetMapping("/register")
    public String registerForm(RegisterRequest registerRequest, HttpSession session) {
        if(session.getAttribute("uid") != null){
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(RegisterRequest registerRequest, HttpSession session) {

        Person newUser = new Person(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail(), registerRequest.getType());

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
