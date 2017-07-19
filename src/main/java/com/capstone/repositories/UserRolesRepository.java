package com.capstone.repositories;

import com.capstone.models.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface UserRolesRepository extends CrudRepository<UserRole, Long>{
;

    @Query("SELECT r.role From UserRole r group by r.role")
    public List<String> findAllRoles();

    public List<UserRole> findAllByUserId(long id);

    public List<UserRole> findAllByRole(String role);


}
