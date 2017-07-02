package com.capstone.controllers;

import com.capstone.models.Pet;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

/**
 * Created by melodytempleton on 6/30/17.
 */
@Controller
public class PetsController {

    private final PetsRepository petsRepository;
    private final UsersRepository usersRepository;

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    public PetsController(PetsRepository petsRepository, UsersRepository usersRepository) {
        this.petsRepository = petsRepository;
        this.usersRepository = usersRepository;
    }

    @RequestMapping(path = "/pets", method = RequestMethod.GET)
    public String indexPage(Model model) {
        ArrayList<Pet> pets = (ArrayList<Pet>) petsRepository.findAll();
        model.addAttribute("pets", pets);
        return "pets/index";
    }

}
