package com.diplom.pd.Controller;

import com.diplom.pd.Models.DeletedProject;
import com.diplom.pd.Models.Project;
import com.diplom.pd.Models.ProjectSubm;
import com.diplom.pd.Models.Student;
import com.diplom.pd.Repository.ProjectDeletedRepo;
import com.diplom.pd.Repository.ProjectRepository;
import com.diplom.pd.Repository.ProjectSubmRepo;
import com.diplom.pd.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectDeletedRepo deletedRepo;
    @Autowired
    private ProjectSubmRepo submRepo;

    private StudentRepo studentRepo;

    private LocalDate localDate;

    @GetMapping("/project")
    public String project(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        Page<?> projectsPage;
        if (isAdmin) {
            projectsPage = projectRepository.findAllProjects(PageRequest.of(page, size));
        } else {
            projectsPage = submRepo.findAllProjects(PageRequest.of(page, size));
        }

        model.addAttribute("projectsPage", projectsPage);
        model.addAttribute("isAdmin", isAdmin);
        return "project-main";
    }


    @GetMapping("/project/add")
    public String projectADd (Model model){
        return "project-add";
    }

    @PostMapping("/project/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String direction,
                              @RequestParam String description,
                              @RequestParam String customer,
                              @RequestParam String mailcustomer, Model model){
        Project project = new Project(title,direction,description,customer,mailcustomer);
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
                              @RequestParam String description,
                              @RequestParam String customer,
                              @RequestParam String mailcustomer, Model model){
        Project project = projectRepository.findById(id).orElseThrow();
        project.setTitle(title);
        project.setDirection(direction);
        project.setDescription(description);
        project.setCustomer(customer);
        project.setMailCustomer(mailcustomer);
        projectRepository.save(project);
        return "redirect:/project";
    }

    @PostMapping("/project/{id}/remove")
    public String deleteBlog(@PathVariable(value = "id") Long id, Model model) {
        // Найти проект по идентификатору
        Project project = projectRepository.findById(id).orElseThrow();

        // Создать экземпляр DeletedProject и скопировать данные из Project
        DeletedProject deletedProject = new DeletedProject(
                project.getTitle(),
                project.getDirection(),
                project.getDescription(),
                project.getCustomer(),
                project.getMailCustomer(),
                LocalDate.now()
        );

        // Сохранить deletedProject в репозитории deletedRepo
        deletedRepo.save(deletedProject);

        // Удалить project из репозитория projectRepository
        projectRepository.delete(project);

        // Перенаправить на страницу проектов
        return "redirect:/project";
    }


    @PostMapping("/project/remain")
    public String remain(Model model) {

        DeletedProject deletedProject = deletedRepo.findFirstBy();

        if (deletedProject != null) {
            Project project = new Project(
                    deletedProject.getTitle(),
                    deletedProject.getDirection(),
                    deletedProject.getDescription(),
                    deletedProject.getCustomer(),
                    deletedProject.getMailCustomer()
            );

            projectRepository.save(project);
            deletedRepo.delete(deletedProject);
        }

        return "redirect:/project";
    }


    @PostMapping("/project/{id}/submit")
    public String submitProject(@PathVariable Long id) {
        // Find the project by ID
        Project project = projectRepository.findById(id).orElse(null);

        if (project != null) {
            // Create a ProjectSubm from the Project
            ProjectSubm projectSubm = new ProjectSubm(
                    project.getTitle(),
                    project.getDirection(),
                    project.getDescription(),
                    project.getCustomer(),
                    project.getMailCustomer()

            );

            // Save the ProjectSubm to the ProjectSubmRepo
            submRepo.save(projectSubm);

            // Delete the original Project from the ProjectRepository
            projectRepository.delete(project);
        }

        return "redirect:/project";
    }


//    @PostMapping("/project/{projectId}/take")
//    public String takeProject(@PathVariable Long projectId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        Student student = studentRepo.findByName(username);
//
//        if (student != null) {
//            ProjectSubm project = submRepo.findById(projectId).orElse(null);
//            if (project != null) {
//                student.setProject(project);
//                studentRepo.save(student);
//            }
//        }
//        return "redirect:/lk";
//    }




}
