package com.capstone.controllers;

import com.capstone.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by melodytempleton on 7/2/17.
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {

//        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
//            User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//            model.addAttribute("user", user1);
//        }

        return "index";
    }


}
