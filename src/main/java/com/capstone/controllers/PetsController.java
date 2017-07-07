package com.capstone.controllers;

import com.capstone.models.Filter;
import com.capstone.models.Pet;

import com.capstone.models.PetImage;
import com.capstone.repositories.FilterRepository;
import com.capstone.repositories.PetImageRepository;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
@Controller
public class PetsController {

    private final PetsRepository petsRepository;
    private final FilterRepository filterRepository;
    private final PetImageRepository petImageRepository;
    private final UsersRepository usersRepository;

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    public PetsController(PetsRepository petsRepository, UsersRepository usersRepository, FilterRepository filterRepository, PetImageRepository petImageRepository) {
        this.petsRepository = petsRepository;
        this.usersRepository = usersRepository;
        this.filterRepository = filterRepository;
        this.petImageRepository = petImageRepository;
    }

    @GetMapping("/pets/pet{id}")
    public String showCreateForm(@PathVariable long id, Model model) {
        Pet pet = petsRepository.findById(id);
        model.addAttribute("pet", pet);
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/individualPet";
    }


    @RequestMapping(path = "/pets/{selection}", method = RequestMethod.GET)
    public String indexPage(Model model,
                            @PathVariable List<String> selection) {

        ArrayList<Pet> pets = (ArrayList<Pet>) petsRepository.findAllByReadyToAdopt(true);
        ArrayList<Pet> filteredPets = new ArrayList<>(pets);

        if (!selection.get(0).equals("all")) {
            for (int i = 0; i < selection.size(); i++) {
                Long filterID = filterRepository.findFilterIDByFilterName(selection.get(i));
                ArrayList tempArray = petsRepository.findPetsByFilter(filterID);
                filteredPets.retainAll(tempArray);
            }
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", filteredPets);
        return "pets/index";
    }

    @GetMapping("/pets/add")
    public String showCreateForm(Model model) {
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pet", new Pet());
        return "pets/addPet";
    }

    @PostMapping("/pets/addPet")
    public String create(
            @Valid Pet pet,
            Errors validation,
            @RequestParam(name = "filterName") List<String> filterNames,
            @RequestParam(name = "image") List<MultipartFile> uploadedfiles,
            @RequestParam(name = "imageDescription[]") List<String> ImageDescriptions,
            @RequestParam(name = "profilePic[]") List<Boolean> profilePicture,
            Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("pet", pet);
            return "pets/addPet";
        } else {
            List<Filter> filters = new ArrayList<>();
            for (String name : filterNames) {
                filters.add(filterRepository.findByFilterName(name));
            }
            pet.setFiltersPets(filters);
            petsRepository.save(pet);
            Long id = pet.getId();
            Pet addedPet = petsRepository.findById(id);
            for (int i = 0; i < uploadedfiles.size(); i++) {
                if (!uploadedfiles.get(i).isEmpty()) {
                    String filename = uploadedfiles.get(i).getOriginalFilename().replace(" ", "_");
                    filename = id.toString() + filename;
                    String filepath = Paths.get(uploadPath, filename).toString();
                    File destinationFile = new File(filepath);
                    PetImage petImage = new PetImage(addedPet);


                    try {
                        petImage.setImageUrl(filename);
                        petImageRepository.save(petImage);
                        long imageID = petImage.getId();
                        PetImage imageAdded = petImageRepository.findById(imageID);
                        uploadedfiles.get(i).transferTo(destinationFile);
                        imageAdded.setImageDescription(ImageDescriptions.get(i));
                        imageAdded.setProfilePic(profilePicture.get(i));
                        petImageRepository.save(imageAdded);
                        model.addAttribute("message", "File successfully uploaded!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("message", "Oops! Something went wrong! " + e);
                    }
                }
            }

            return "redirect:/pets/all";
        }
    }

    @GetMapping("/petTypes.json")
    public @ResponseBody
    Iterable<String> listSpecies() {
        return petsRepository.findSpecies();
    }

//    @GetMapping("/test")
//    public String viewAllPostsInJSONFormat(Model model) {
//        model.addAttribute("list", petsRepository.findSpecies());
//        return "test";
//    }

    @RequestMapping(path = "/pets/{species}/type/{selection}", method = RequestMethod.GET)
    public String indexPage(Model model,
                            @PathVariable List<String> selection,
                            @PathVariable String species) {

        ArrayList<Pet> pets = (ArrayList<Pet>) petsRepository.findAllByReadyToAdoptAndSpecies(true, species);
        ArrayList<Pet> filteredPets = new ArrayList<>(pets);

        if (!selection.get(0).equals("all")) {
            for (int i = 0; i < selection.size(); i++) {
                Long filterID = filterRepository.findFilterIDByFilterName(selection.get(i));
                ArrayList tempArray = petsRepository.findPetsByFilter(filterID);
                filteredPets.retainAll(tempArray);
            }
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("species", species);
        model.addAttribute("pets", filteredPets);
        return "pets/index";
    }

    @GetMapping("/pets/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Pet pet = petsRepository.findById(id);
        List <Filter> petsFilters = pet.getFilters();
        List<PetImage> petsImages = pet.getImages();
        int numberImages = petsImages.size();
        model.addAttribute("pet", pet);
        model.addAttribute("filters", petsFilters);
        model.addAttribute("images", petsImages);
        model.addAttribute("imageCount", numberImages);
        return "pets/edit";
    }



}
