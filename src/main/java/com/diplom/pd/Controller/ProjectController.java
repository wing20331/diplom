package com.diplom.pd.Controller;

import com.diplom.pd.Models.Project;
import com.diplom.pd.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/project")
    public String project (Model model){
    Iterable<Project> projects = projectRepository.findAll();
    model.addAttribute("project", projects);
    return "project-main";
    }

    @GetMapping("/project/add")
    public String projectADd (Model model){
        return "project-add";
    }

    @PostMapping("/project/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String direction,
                              @RequestParam String description, Model model){
        Project project = new Project(title,direction,description);
        projectRepository.save(project);
        return "redirect:/project";
    }

    @GetMapping("/project/{id}")
    public String details(@PathVariable(value = "id") Long id, Model model){
        if (!projectRepository.existsById(id)){
            return "redirect:/project";
        }
        Optional<Project> project = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        project.ifPresent(res::add);
        model.addAttribute("project", res);
        return "project-details";
    }

    @GetMapping("/project/{id}/edit")
    public String edit (@PathVariable(value = "id") Long id,  Model model){
        if (!projectRepository.existsById(id)){
            return "redirect:/project";
        }
        Optional<Project> project = projectRepository.findById(id);
        ArrayList<Project> res = new ArrayList<>();
        project.ifPresent(res::add);
        model.addAttribute("project", res);
        return "project-edit";
    }

    @PostMapping("/project/{id}/edit")
    public String updateBlog (@PathVariable(value = "id") Long id,
                              @RequestParam String title,
                              @RequestParam String direction,
                              @RequestParam String description, Model model){
        Project project = projectRepository.findById(id).orElseThrow();
        project.setTitle(title);
        project.setDirection(direction);
        project.setDescription(description);
        projectRepository.save(project);
        return "redirect:/project";
    }

    @PostMapping("/project/{id}/remove")
    public String deleteBlog(@PathVariable(value = "id") Long id, Model model){
        Project project = projectRepository.findById(id).orElseThrow();
        projectRepository.delete(project);
        return "redirect:/project";
    }


}
