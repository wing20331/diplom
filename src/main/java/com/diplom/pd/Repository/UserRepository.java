package com.diplom.pd.Repository;

import com.diplom.pd.Models.pdUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<pdUser, Long> {

    Optional<pdUser> findByName(String name);

}
