package com.diplom.pd.Repository;

import com.diplom.pd.Models.Project;
import com.diplom.pd.Models.ProjectSubm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectSubmRepo extends CrudRepository<ProjectSubm, Long> {

    @Query("SELECT p FROM Project p WHERE TYPE(p) = ProjectSubm")
    Page<ProjectSubm> findAllProjects(Pageable pageable);

    @Query("SELECT p FROM Project p WHERE TYPE(p) = ProjectSubm")
    List<Project> findTop5ByOrderByIdAsc();
}
