package com.diplom.pd.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, direction,customer,mailCustomer;
    @Column(length = 1000)
    private String description;

    public Project(String title, String direction, String description, String customer, String mailCustomer) {
        this.title = title;
        this.direction = direction;
        this.description = description;
        this.customer = customer;
        this.mailCustomer = mailCustomer;
    }
}
