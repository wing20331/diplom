package com.diplom.pd.Controller;
import com.diplom.pd.Models.pdUser;
import com.diplom.pd.Services.MyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class MainController {

    private MyService service;

    @GetMapping("/")
    public String log(Model model){
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model){
        model.addAttribute("title", "главная страница");
        return "index";
    }


    @PostMapping("/new-user")
    public String addUser(@RequestBody pdUser user){
       service.addUser(user);
       return "User is saved";
    }


}