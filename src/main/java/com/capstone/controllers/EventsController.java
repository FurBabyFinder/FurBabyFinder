package com.capstone.controllers;

import com.capstone.models.Event;
import com.capstone.repositories.EventsRepository;
import com.capstone.repositories.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by frenchfryes on 7/7/17.
 */
@Controller
public class EventsController {

    @Autowired
    PetsRepository petsRepository;

//    @Autowired
//    public EventsController(PetsRepository petsRepository, EventsRepository eventsRepository){
//        this.petsRepository = petsRepository;
//        this.eventsRepository = eventsRepository;
//    }
    @Autowired
    EventsRepository eventsRepository;


    @GetMapping("/events")
    public String showEvents(Model model){
        Iterable<Event> events = eventsRepository.findAll();
        model.addAttribute("events", events);
        model.addAttribute("list", petsRepository.findSpecies());
        return "events";
    }

    @GetMapping("/events/create")
    public String showEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("list", petsRepository.findSpecies());
        return "eventscreate";
    }

//event delete
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
        model.addAttribute("event", eventsRepository.findOne(id));
        model.addAttribute("list", petsRepository.findSpecies());
        return "editevent";

    }

    @PostMapping("/events/edit")
    public String editEvent(@Valid Event event,
                            Errors validation,
            Model model
    ) {

        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("event", event);
            return "redirect:/events/{id}/edit";
        }
        System.out.println(event.getId());
        Event eventToSave = eventsRepository.findOne(event.getId());
        eventsRepository.save(event);
        return "redirect:/events";
    }

    @PostMapping("/event/delete")
    public String deletePost(@ModelAttribute Event event, Model model) {
        eventsRepository.deleteEvent(event.getId());
        model.addAttribute("msg", "Your post was deleted correctly");
        return "return the view with a success message";
    }


}
