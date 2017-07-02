package com.capstone.repositories;

import com.capstone.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface UsersRepository extends CrudRepository<User, Long> {

    public User findById(Long id);

//    public User findByFirst_nameAndLast_name(String first, String last);

}
