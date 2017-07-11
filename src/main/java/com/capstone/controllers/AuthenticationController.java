package com.capstone.controllers;

import com.capstone.models.User;
import com.capstone.repositories.PetsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthenticationController {
PetsRepository petsRepository;
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("list", petsRepository.findSpecies());
        return "register";
    }
}