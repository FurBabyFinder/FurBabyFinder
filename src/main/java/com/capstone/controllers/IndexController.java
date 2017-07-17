package com.capstone.controllers;

import com.capstone.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by melodytempleton on 7/2/17.
 */
@Controller
public class IndexController {
        PetsRepository petsRepository;


    @Autowired
    public IndexController (PetsRepository petsRepository){
        this.petsRepository = petsRepository;
    }

    @GetMapping("/")
    public String index(Model model) {

//        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
//            User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//            model.addAttribute("user", user1);
//        }
        model.addAttribute("list", petsRepository.findSpecies());
        return "index";
    }

    @GetMapping("/adoptionForm")
    public String adoptionForm(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "adoptionForm";

    }

    @GetMapping("/fosterForm")
    public String fosterForm(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "fosterForm";

    }

    @GetMapping("/playDateForm")
    public String playDateForm(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "play-date-form";

    }

    @GetMapping("/foster")
    public String foster(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "foster";

    }


    @GetMapping("how-it-works")
    public String howItWorks(Model model){

        model.addAttribute("list", petsRepository.findSpecies());
        return "how-it-works";

    }


}
