package com.capstone.controllers;

import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by melodytempleton on 6/30/17.
 */
@Controller
public class PetsController {

    private final PetsRepository petsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public PetsController(PetsRepository petsRepository, UsersRepository usersRepository) {
        this.petsRepository = petsRepository;
        this.usersRepository = usersRepository;
    }



}
