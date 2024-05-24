package com.diplom.pd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class ProjectSubm extends Project{
    public ProjectSubm() {
    }

    public ProjectSubm(String title, String direction, String description, String customer, String mailCustomer) {
        super(title, direction, description, customer, mailCustomer);
    }
}
