package com.diplom.pd.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@NoArgsConstructor
public class pdUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String name;
    private String login;
    private String password;

    public pdUser(Long id, String userName, String name, String login, String password) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.login = login;
        this.password = password;
    }

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;


}
