package com.capstone.repositories;

import com.capstone.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface UsersRepository extends CrudRepository<User, Long> {

    public User findById(Long id);

    public List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    public List<User> findAllByFirstName(String firstName);

    public List<User> findAllByLastName(String lastNAme);

    public User findByUsername(String username);

//    public User findByFirst_nameAndLast_name(String first, String last);

}
