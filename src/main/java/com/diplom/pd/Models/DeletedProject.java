package com.diplom.pd.Models;

import com.diplom.pd.Models.Project;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
public class DeletedProject extends Project {

    private LocalDate localDate;

    public DeletedProject(String title, String direction, String description, String customer, String mailCustomer, LocalDate localDate) {
        super(title, direction, description, customer, mailCustomer);
        this.localDate = localDate;
    }

    public DeletedProject() {

    }
}
