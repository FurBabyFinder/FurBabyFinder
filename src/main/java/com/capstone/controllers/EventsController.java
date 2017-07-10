package com.capstone.controllers;

import com.capstone.models.Event;
import com.capstone.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by frenchfryes on 7/7/17.
 */
@Controller
public class EventsController {
    @Autowired
    private final EventsRepository eventsRepository;

    @Autowired
    public EventsController(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Autowired
    EventsRepository eventsDao;

    @GetMapping("/events")
    public String showEvents(Model model){
        Iterable<Event> events = eventsRepository.findAll();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/events/create")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        return "eventscreate";
    }


    @PostMapping("/events/create")
    public String saveEvent(
            @Valid Event event,
            Errors validation,
            Model model
    ) {

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("event", event);
            return "redirect:/events";
        }
        eventsRepository.save(event);
        return "redirect:/events";
    }
    @GetMapping("/events/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        model.addAttribute("event", eventsDao.findById(id));
        return "events";

    }


}
