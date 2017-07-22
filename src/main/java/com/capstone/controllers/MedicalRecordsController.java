package com.capstone.controllers;

import com.capstone.models.*;
import com.capstone.repositories.MedicalRecordImagesRepository;
import com.capstone.repositories.MedicalRecordsRepository;
import com.capstone.repositories.PetsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

        @GetMapping("/pets/{pid}/viewAllMed")
    public String showCreateForm(@PathVariable long pid,
                                 Model model) {
        Pet pet = petsRepository.findById(pid);
        List<MedicalRecord> medicalRecords = medDao.findAllByPet(pet);

        model.addAttribute("medical", medicalRecords);
        model.addAttribute("pet", pet);
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/medical/viewAllMedicalRecords";
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

    @GetMapping("/pets/addMedicalRecord/{pid}")
    public String showCreateFilestackForm(
            @PathVariable long pid,
            Model model) {
        Pet pet = petsRepository.findById(pid);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("medicalRecord", new MedicalRecord());
        model.addAttribute("pet", pet);
        return "pets/medical/addMedicalRecord";
    }

    @PostMapping("/pets/addMedicalRecord/{pid}")
    public String createFilstack(
            @PathVariable long pid,
            @Valid MedicalRecord medicalRecord,
            Errors validation,
            @RequestParam(name = "imageDescription[]") List<String> imageDescriptions,
            @RequestParam(name = "imageUrl[]") List<String> imageUrls,
            Model model) {
        Pet pet = petsRepository.findById(pid);
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("pet", pet);
            model.addAttribute("medical", medicalRecord);
            return "pets/addMedicalRecord";
        } else {

            medDao.save(medicalRecord);
            long id = medicalRecord.getId();
            MedicalRecord addedMedRecord = medDao.findById(id);
            for (int i = 0; i < imageUrls.size(); i++) {
                if (!imageUrls.get(i).isEmpty()) {
                    MedicalImage medicalImage = new MedicalImage(addedMedRecord);
                    medicalImage.setImageUrl(imageUrls.get(i));
                    medImageDao.save(medicalImage);
                    long imageID = medicalImage.getId();
                    MedicalImage imageAdded = medImageDao.findById(imageID);
                    imageAdded.setImageDescription(imageDescriptions.get(i));
                    medImageDao.save(imageAdded);
                    model.addAttribute("message", "File successfully uploaded!");

                }
            }

            return "redirect:/pets/"+ pid +"/medical" + id;
        }
    }




}
