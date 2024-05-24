package com.diplom.pd.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student extends pdUser{

    private boolean hasProject;
//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private ProjectSubm project;

//    private int points;

    public Student(Long id, String firstName, String lastName, String mail, String jobTitle, String name, String password, String roles, boolean hasProject) {
        super(id, firstName, lastName, mail, jobTitle, name, password, roles);
        this.hasProject = hasProject;
    }

    public Student(boolean hasProject) {
        this.hasProject = hasProject;
    }

    public Student() {

    }
}
