package com.capstone.repositories;

import com.capstone.models.User;
import com.capstone.models.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface UserRolesRepository extends CrudRepository<UserRole, Long>{

    public List<User> findAllByRole(String role);
}
