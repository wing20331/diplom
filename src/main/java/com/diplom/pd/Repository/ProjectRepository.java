package com.diplom.pd.Repository;

import com.diplom.pd.Models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project p WHERE TYPE(p) = Project")
    List<Project> findTop5ByOrderByIdAsc();
    @Query("SELECT p FROM Project p WHERE TYPE(p) = Project")
    Page<Project> findAllProjects(Pageable pageable);

}
