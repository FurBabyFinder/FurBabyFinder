package com.capstone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by frenchfryes on 7/7/17.
 */
@Controller
public class EventsController {
    @GetMapping("/events")
    public String showEvents(){
        return "events";
    }
}
