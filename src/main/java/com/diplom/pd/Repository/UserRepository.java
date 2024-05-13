package com.diplom.pd.Repository;

import com.diplom.pd.Models.pdUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<pdUser, Long> {

    Optional<pdUser> findByName(String username);
}
