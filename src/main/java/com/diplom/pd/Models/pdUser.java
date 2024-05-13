package com.diplom.pd.Models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
@Builder
public class pdUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String FirstName;
    private String LastName;
    private String mail;
    private String JobTitle;
    private String name;
    private String password;
    private String roles;

    public pdUser(Long id, String firstName, String lastName, String mail, String jobTitle, String username, String password, String roles) {
        this.id = id;
        FirstName = firstName;
        LastName = lastName;
        this.mail = mail;
        JobTitle = jobTitle;
        this.name = username;
        this.password = password;
        this.roles = roles;
    }
}
