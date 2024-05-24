package com.diplom.pd.Services;

import com.diplom.pd.Models.ProjectSubm;
import com.diplom.pd.Models.Student;
import com.diplom.pd.Repository.ProjectSubmRepo;
import com.diplom.pd.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

//    @Autowired
//    private ProjectSubmRepo submRepo;
//
//    @Autowired
//    private StudentRepo studentRepository;
//
//    @Transactional
//    public void takeProject(Long projectId, String username) {
//        Student student = studentRepository.findByName(username);
//        if (student != null) {
//            ProjectSubm project = submRepo.findById(projectId).orElse(null);
//            if (project != null) {
//                student.setProject(project);
//                studentRepository.save(student);
//            }
//        }
//    }
}
