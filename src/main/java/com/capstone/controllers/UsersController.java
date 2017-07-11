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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
    public String saveUser(@ModelAttribute User user,
                           Model model){

//        THIS IS THE ENCODED VERSION WE NEED TO PUT BACK IN LATER
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        usersDao.save(user);
//
        user.setPassword((user.getPassword()));
        usersDao.save(user);

        UserRole userRole = new UserRole(user.getId(), "basic");
        model.addAttribute("list", petsRepository.findSpecies());
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
            users = usersDao.findAllByLastNameStartingWith(lastName);
        }
        else if (lastName.equals("none")){
            users = usersDao.findAllByFirstNameStartingWith(firstName);
        }
        else {
            users = usersDao.findAllByFirstNameAndLastName(firstName, lastName);
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("users", users);
        return "users/searchUsers";
    }

    @GetMapping("/users/user/{id}")
    public String viewUser (Model model,
                              @PathVariable long id ){
        User user = usersDao.findOne(id);
        List<Pet> fosteredPets = petsRepository.findAllByFoster(user);
        List<Pet> adoptedPets  = petsRepository.findAllByAdopter(user);
        List<UserRole> roles = userRolesRepository.findAllByUserId(id);
        model.addAttribute("roles", roles);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("fosteredPets", fosteredPets);
        model.addAttribute("adoptedPets", adoptedPets);
        model.addAttribute("user", user);
        return "users/viewIndividualUser";
    }

    @GetMapping("/users/update/{id}")
    public String viewUpdateUser (Model model,
                            @PathVariable long id ){
        User user = usersDao.findOne(id);
        List<UserRole> roles = userRolesRepository.findAllByUserId(id);
        List<String> allRoles = userRolesRepository.findAllRoles();

        model.addAttribute("roles", roles);
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("user", user);
        return "users/updateUser";
    }

    @PostMapping("/users/update/{id}")
    public String postUpdateUser (Model model,
                                  @PathVariable long id,
                                  @ModelAttribute User user,
                                  @ModelAttribute("deleteRolesIds") String deleteRolesIds,
                                  Errors validation,
                                  @RequestParam(name = "addRolesNames") String addRolesNames
                                  ){
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("user", user);
            return "users/updateUser";
        } else {

            if(!deleteRolesIds.equals("")) {
                String[] stringArray = deleteRolesIds.split(",");
                int[] intIdsArray = new int[stringArray.length];
                for (int i = 0; i < stringArray.length; i++) {
                    String numberAsString = stringArray[i];
                    intIdsArray[i] = Integer.parseInt(numberAsString);
                }
                ;
                for (long removeID : intIdsArray) {
                    UserRole deleteRole = userRolesRepository.findOne(removeID);
                    userRolesRepository.delete(deleteRole);
                }
            }
            if(!addRolesNames.equals("")) {
                String[] stringArray = addRolesNames.split(",");
                for (String add : stringArray) {
                    UserRole addRole = new UserRole(id,add);
                    userRolesRepository.save(addRole);
                }
            }
            User userPassword = usersDao.findOne(id);
            String password = userPassword.getPassword();
            user.setId(id);
            user.setPassword(password);
            usersDao.save(user);
            model.addAttribute("list", petsRepository.findSpecies());
            model.addAttribute("user", user);
            return "redirect:/users/user/" + id;
        }
    }


}