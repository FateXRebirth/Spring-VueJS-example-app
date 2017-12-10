package example.Controller;

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

    // Only case for thymeleaf, if you want to use it as template engine
    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
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

}
