package com.diplom.pd.Services;

import com.diplom.pd.Models.pdUser;
import com.diplom.pd.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;


    public void addStudent(pdUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}