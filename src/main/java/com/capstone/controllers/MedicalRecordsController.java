package com.capstone.controllers;

import com.capstone.models.MedicalRecord;
import com.capstone.models.Pet;
import com.capstone.repositories.MedicalRecordImagesRepository;
import com.capstone.repositories.MedicalRecordsRepository;
import com.capstone.repositories.PetsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by melodytempleton on 7/21/17.
 */
@Controller
public class MedicalRecordsController {
    private final MedicalRecordsRepository medDao;
    private final MedicalRecordImagesRepository medImageDao;
    private final PetsRepository petsRepository;

    public MedicalRecordsController(MedicalRecordsRepository medDao, MedicalRecordImagesRepository medImageDao, PetsRepository petsRepository) {
        this.medDao = medDao;
        this.medImageDao = medImageDao;
        this.petsRepository = petsRepository;
    }


    @GetMapping("/pets/{pid}/medical{mid}")
    public String showCreateForm(@PathVariable long pid,
                                 @PathVariable long mid,
                                 Model model) {
        Pet pet = petsRepository.findById(pid);

        MedicalRecord medicalRecord = medDao.findById(mid);

        model.addAttribute("medical", medicalRecord);
        model.addAttribute("pet", pet);
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/medical/viewMedicalRecord";
    }



}
