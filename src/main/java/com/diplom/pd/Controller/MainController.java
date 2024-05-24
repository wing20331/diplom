package com.diplom.pd.Controller;
import com.diplom.pd.Models.Project;
import com.diplom.pd.Models.ProjectSubm;
import com.diplom.pd.Models.Student;
import com.diplom.pd.Models.pdUser;
import com.diplom.pd.Repository.ProjectRepository;
import com.diplom.pd.Repository.ProjectSubmRepo;
import com.diplom.pd.Repository.StudentRepo;
import com.diplom.pd.Repository.UserRepository;
import com.diplom.pd.Services.MyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MainController {
    private ProjectRepository projectRepository;
    private MyService service;
    private UserRepository userRepository;
    private ProjectSubmRepo submRepo;
    @Autowired
    private StudentRepo studentRepo;



    @PostMapping("/new-user")
    public String addUser(@RequestBody pdUser user){
       service.addUser(user);
       return "index";
    }

    @PostMapping("/new-student")
    public String addStudent(@RequestBody Student student){
        service.addUser(student);
        return "index";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("title", "главная страница");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        List<?> projects;
        if (isAdmin) {
            projects = projectRepository.findTop5ByOrderByIdAsc();
        } else {
            projects = submRepo.findTop5ByOrderByIdAsc();
        }

        model.addAttribute("projects", projects);
        return "index";
    }


    @GetMapping("/lk")
    public String lk(Model model, Authentication authentication) {
        String username = authentication.getName();
        Optional<pdUser> currentUser = userRepository.findByName(username);

        if (currentUser.isPresent()) {
            model.addAttribute("user", currentUser.get());
        } else {
            model.addAttribute("error", "User not found");
        }



        return "lk";
    }

}