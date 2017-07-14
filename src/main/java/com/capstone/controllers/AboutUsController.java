package com.capstone.controllers;

import com.capstone.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by frenchfryes on 7/13/17.
 */
@Controller
public class AboutUsController {
    @Autowired
    PetsRepository petsRepository;
    @Autowired
    public AboutUsController (PetsRepository petsRepository){
        this.petsRepository = petsRepository;
    }

    @GetMapping("/about-us")
    public String aboutUs(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "about-us";

    }
}
