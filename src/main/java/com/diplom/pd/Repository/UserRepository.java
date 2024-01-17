package com.diplom.pd.Repository;

import com.diplom.pd.Models.pdUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<pdUser, Long> {

}
