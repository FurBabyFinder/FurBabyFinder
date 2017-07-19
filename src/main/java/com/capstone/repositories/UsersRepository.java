package com.capstone.repositories;

import com.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface UsersRepository extends CrudRepository<User, Long> {

    public User findById(Long id);

    public List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    public List<User> findAllByFirstNameStartingWithAndLastName(String firstName, String lastName);

    public List<User> findAllByFirstName(String firstName);

    public List<User> findAllByLastNameStartingWith(String lastName);

    public List<User> findAllByFirstNameStartingWith(String firstName);

    public List<User> findAllByLastName(String lastNAme);



    @Query("SELECT u FROM User u WHERE u.id IN (SELECT ur.userId FROM UserRole ur WHERE ur.role = :role) group by u.id")
    List<User> getAdminList(@Param("role") String userRole);


    public User findByUsername(String username);

    public List<User> findAllByEmailStartingWith(String email);

    public List<User> findAllByUsernameStartingWith(String username);

//    public User findByFirst_nameAndLast_name(String first, String last);

}
