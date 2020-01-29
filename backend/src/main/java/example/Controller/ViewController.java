package example.Controller;

import example.Request.MemberLogin;
import example.Request.MemberRegister;
import example.Response.CarList;
import example.Response.News;
import example.Response.Result;
import example.Response.User;
import example.Service.CarService;
import example.Service.CommonService;
import example.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by FateXRebirth on 20/10/2017.
 */

@Controller
@RequestMapping("/")
public class ViewController {

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @Autowired
    CommonService commonService;

    // git log --pretty=format:'{%n "subject": "%s",%n "name": "%cn",%n "hash": "%h",%n "date": "%ci" %n},'  | sed "$ s/,$//" | sed ':a;N;$!ba;s/\r\n\([^{]\)/\\n\1/g'| awk 'BEGIN { print("[") } { print($0) } END { print("]") }' > log.json

    @GetMapping("/")
    public String index(Model model) {

        JSONParser parser = new JSONParser();

        try {
            ArrayList<String> backend = new ArrayList<String>();
            ArrayList<String> frontend = new ArrayList<String>();
            JSONArray data = (JSONArray) parser.parse(new FileReader("log.json"));

            for (Object input : data)
            {
                JSONObject obj = (JSONObject) input;
                String log = "";
                log = log + (String)obj.get("subject") + ", ";
                log = log + (String)obj.get("name") + " committed ";
                log = log + (String)obj.get("hash") + ", ";
                log = log + ((String)obj.get("date")).substring(0, 10) + "。";

                if( ((String)obj.get("subject")).indexOf("Frontend") != -1 ) {
                    frontend.add(log);
                } else {
                    backend.add(log);
                }
            }

            model.addAttribute("frontendLogs", frontend);
            model.addAttribute("backendLogs", backend);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  "index";
    }

    @GetMapping("/login")
    public String loginForm(MemberLogin memberLogin, HttpSession session) {
        if(session.getAttribute("uid") != null){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(MemberLogin memberLogin, HttpSession session, Model model) {

        Result result = userService.sign_in(memberLogin);

        if(result.getReturnCode() == 0) {
            session.setAttribute("uid", result.getReturnData().get("account"));
            return "redirect:/";
        } else  {
            model.addAttribute("result", result);
            return "login";
        }

    }

    @GetMapping("/register")
    public String registerForm(MemberRegister memberRegister, HttpSession session) {
        if(session.getAttribute("uid") != null){
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(MemberRegister memberRegister, HttpSession session, Model model) {

        Result result = userService.sign_up(memberRegister);
        model.addAttribute("result", result);

        if(result.getReturnCode() == 0) {
            return "login";
        } else  {
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if(session.getAttribute("uid") != null){
            session.removeAttribute("uid");
        }
        return "redirect:/";
    }

    @GetMapping(path = {"/dashboard", "/dashboard/{type}", "/dashboard/{type}/{id}"})
    public String dashboard(@PathVariable("type") Optional<String> type, @PathVariable("id") Optional<Integer> id, HttpSession session, Model model) {
        if(session.getAttribute("uid") == null) {
            return "redirect:/login";
        }
        if(id.isPresent() && type.isPresent()) {
            if( type.get().equals("cars")) {

            } else if(type.get().equals("users")) {

            } else if(type.get().equals("news")) {

            }
        } else if(!id.isPresent() && type.isPresent()) {
            if( type.get().equals("cars")) {
                List<CarList> carResult = (List<CarList>)carService.getCarList().getReturnData().get("cars");
                model.addAttribute("CarList", carResult);
            } else if(type.get().equals("users")) {
                List<User> userResult = (List<User>)userService.getUserList().getReturnData().get("users");
                model.addAttribute("UserList", userResult);
            } else if(type.get().equals("news")) {
                List<News> newsResult = (List<News>)commonService.getNews().getReturnData().get("news");
                model.addAttribute("NewsList", newsResult);
            }
        } else {
            List<CarList> carResult = (List<CarList>)carService.getCarList().getReturnData().get("cars");
            List<User> userResult = (List<User>)userService.getUserList().getReturnData().get("users");
            List<News> newsResult = (List<News>)commonService.getNews().getReturnData().get("news");
            model.addAttribute("CarList", carResult);
            model.addAttribute("UserList", userResult);
            model.addAttribute("NewsList", newsResult);
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
