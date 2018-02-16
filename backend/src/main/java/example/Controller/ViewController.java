package example.Controller;

import example.Entity.LoginForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by FateXRebirth on 20/10/2017.
 */

@Controller
public class ViewController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute LoginForm loginForm) {

        if(loginForm.authenticate()) {
            return "success";
        }

        return "redirect:/failure";
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

    @GetMapping("/failure")
    public String NotFound() {
        return "failure";
    }

}
