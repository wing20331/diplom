package com.diplom.pd.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class pdUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String jobTitle;
    private String name;
    private String password;
    private String roles;

    
}
