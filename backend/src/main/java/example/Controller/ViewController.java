package example.Controller;

import example.Entity.LoginForm;
import example.Entity.Person;
import example.Entity.RegisterForm;
import example.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.util.Set;


/**
 * Created by FateXRebirth on 20/10/2017.
 */

@Controller
public class ViewController {

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, HttpSession session) {

        if(bindingResult.hasErrors()) {
            return "login";
        }

        Person person = personService.getPersonByUsername(loginForm.getUsername());

        if(person != null && person.getType().equals(("manager"))) {
            session.setAttribute("uid", person.getUsername());
            return "success";
        }

        return "redirect:/failure";
    }

    @GetMapping("/register")
    public String registerForm(RegisterForm registerForm) {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@Valid RegisterForm registerForm, BindingResult bindingResult) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<RegisterForm>> violations = validator.validate(registerForm);

        for (ConstraintViolation<RegisterForm> violation : violations) {
            // System.out.println(violation.getMessage());
        }

        if(bindingResult.hasErrors()) {
            return "register";
        }

        System.out.println(registerForm);

        return "redirect:/";
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

    @GetMapping("/error")
    public String NotFound() {
        return "failure";
    }

    // example 1
    @GetMapping("/get/{id}")
    public @ResponseBody ResponseEntity<String> get(@PathVariable int id) {
        return new ResponseEntity<String>("GET Response" + id, HttpStatus.OK);
    }

    // example 2
    @PostMapping(path = "/members", consumes = "application/json", produces = "application/json")
    public void example() {
        //code
    }

    // example 3
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
