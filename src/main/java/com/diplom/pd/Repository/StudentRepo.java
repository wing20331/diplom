package com.diplom.pd.Repository;

import com.diplom.pd.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {

    Student findByName(String name);
}
