package com.capstone.controllers;

import com.capstone.models.User;
import com.capstone.models.UserRole;
import com.capstone.repositories.UserRolesRepository;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {



    @Autowired
    UsersRepository usersDao;

    @Autowired
    UserRolesRepository userRolesRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @PostMapping("register")
    public String saveUser(@ModelAttribute User user){

//        THIS IS THE ENCODED VERSION WE NEED TO PUT BACK IN LATER
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        usersDao.save(user);
//
        user.setPassword((user.getPassword()));
        usersDao.save(user);

        UserRole userRole = new UserRole(user.getId(), "basic");
        userRolesRepository.save(userRole);
        //Create a default user role for the new user

        return "login";
    }
}