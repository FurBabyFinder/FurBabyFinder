package com.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {


    @GetMapping("/register-login")
    public String ShowLogin() {
        return "register-login";
    }
}
