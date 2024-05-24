package com.diplom.pd.Repository;


import com.diplom.pd.Models.DeletedProject;
import com.diplom.pd.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectDeletedRepo extends JpaRepository<DeletedProject, Long> {
 DeletedProject findFirstBy();
}
