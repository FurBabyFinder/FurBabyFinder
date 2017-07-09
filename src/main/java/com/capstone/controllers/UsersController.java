package com.capstone.controllers;

import com.capstone.models.Pet;
import com.capstone.models.User;
import com.capstone.models.UserRole;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UserRolesRepository;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class UsersController {
PetsRepository petsRepository;

    @Autowired
    public UsersController(PetsRepository petsRepository){
        this.petsRepository = petsRepository;
    }


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

    @GetMapping("/searchUser")
    public String searchUser(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "users/searchUsers";
    }

    @GetMapping("/users/searchID/{id}")
    public String SearchById (Model model,
                              @PathVariable long id ){
        List<User> users = new ArrayList<>();
        users.add(usersDao.findOne(id));
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("users", users);
        return "users/searchUsers";
    }

    @GetMapping("/users/{firstName}/searchName/{lastName}")
    public String SearchUsersByName (Model model,
                                       @PathVariable String firstName,
                                       @PathVariable String lastName){
        List <User> users = new ArrayList<>();
        if (firstName.equals("none")){
            users = usersDao.findAllByLastName(lastName);
        }
        else if (lastName.equals("none")){
            users = usersDao.findAllByFirstName(firstName);
        }
        else {
            users = usersDao.findAllByFirstNameAndLastName(firstName, lastName);
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("users", users);
        return "users/searchUsers";
    }




}