package com.capstone.controllers;

import com.capstone.models.Filter;
import com.capstone.models.Pet;

import com.capstone.models.PetImage;
import com.capstone.models.User;
import com.capstone.repositories.FilterRepository;
import com.capstone.repositories.PetImageRepository;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import com.capstone.svcs.PetDTO;
import com.capstone.svcs.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
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
        UserDetails userDetails;
        boolean favorited = false;
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = (userDetails.getUsername());
            User user = usersRepository.findByUsername(username);
            List<Pet> favorites = user.getFavorites();
            for (int i = 0; i < favorites.size(); i++){
                if (favorites.get(i).getId() == id){
                    favorited = true;
                }
            }
        }
        User foster = pet.getFoster();
        User adopter = pet.getAdopter();
        model.addAttribute("favorited", favorited);
        model.addAttribute("pet", pet);
        model.addAttribute("foster", foster);
        model.addAttribute("adopter", adopter);
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/individualPet";
    }
  @PostMapping("/pets/pet{id}")
    public String addToFavorites(@PathVariable long id,
                                 Model model) {
        Pet pet = petsRepository.findById(id);
      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String username = (userDetails.getUsername());
      User user = usersRepository.findByUsername(username);
        List<Pet> favorite = user.getFavorites();
        favorite.add(pet);
        user.setFavorites(favorite);
        usersRepository.save(user);
        User foster = pet.getFoster();
        User adopter = pet.getAdopter();
        model.addAttribute("favorited", true);
        model.addAttribute("pet", pet);
        model.addAttribute("foster", foster);
        model.addAttribute("adopter", adopter);
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/individualPet";
    }



    @GetMapping("/pets/addPetFilestack")
    public String showCreateFilestackForm(Model model) {
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pet", new Pet());
        return "pets/addPetFilestack";
    }

    @PostMapping("/pets/addPetFilestack")
    public String createFilstack(
            @Valid Pet pet,
            Errors validation,
            @RequestParam(name = "filterName") List<String> filterNames,
            @RequestParam(name = "imageDescription[]") List<String> imageDescriptions,
            @RequestParam(name = "imageUrl[]") List<String> imageUrls,
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
            for (int i = 0; i < imageUrls.size(); i++) {
                if (!imageUrls.get(i).isEmpty()) {
                    PetImage petImage = new PetImage(addedPet);

                    petImage.setImageUrl(imageUrls.get(i));
                        petImageRepository.save(petImage);
                        long imageID = petImage.getId();
                        PetImage imageAdded = petImageRepository.findById(imageID);
                        imageAdded.setImageDescription(imageDescriptions.get(i));
                        imageAdded.setProfilePic(profilePicture.get(i));
                        petImageRepository.save(imageAdded);
                        model.addAttribute("message", "File successfully uploaded!");

                }
            }

            return "redirect:/pets/pet" + id;
        }
    }


    @GetMapping("/pets/{id}/editFilestack")
    public String showEditFormFilestack(@PathVariable long id, Model model) {
        Pet pet = petsRepository.findById(id);
        List <Filter> petsFilters = pet.getFilters();
        List<PetImage> petsImages = pet.getImages();
        int numberImages = petsImages.size();
        PetDTO petDTO = new PetDTO(pet, petsImages);
        model.addAttribute("filters", petsFilters);
        model.addAttribute("petDTO", petDTO);
        model.addAttribute("imageCount", numberImages);
        model.addAttribute("list", petsRepository.findSpecies());

        return "pets/editPetFilestack";
    }

    @PostMapping("/pets/{id}/editFilestack")
    public String editPetFilestack(

            @ModelAttribute ("petDTO") PetDTO petDTO,
            Errors validation,
            @PathVariable long id,
            @RequestParam(name = "filterName") List<String> filterNames,
            @RequestParam(name = "imageUrl[]") List<String> imageUrls,
            @RequestParam(name = "imageReplace[]") List<String> replacefiles,
            @RequestParam(name = "imageDescription[]") List<String> imageDescriptions,
            @RequestParam(name = "profilePic[]") List<Boolean> profilePicture,
            @RequestParam(name = "afterAdopt[]") List<Boolean> afterAdopt,
            Model model) throws IOException {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("pet", petDTO.getPet());
            return "pets/edit";
        } else {
            List<Filter> filters = new ArrayList<>();
            for (String name : filterNames) {
                filters.add(filterRepository.findByFilterName(name));
            }

            Pet pet = petDTO.getPet();
            List<PetImage> imageList = petDTO.getImageList();
            pet.setId(id);
            pet.setFiltersPets(filters);
            if(imageList != null) {
                for (int i = 0; i < imageList.size(); i++) {
                    if (replacefiles != null) {
                        if (replacefiles.size() > 0) {
                            if (!replacefiles.get(i).equals("")) {
                                imageList.get(i).setImageUrl(replacefiles.get(i));

                            }
                        }
                    }
                }
            }
            if(imageList != null) {
                for (PetImage image : imageList) {
                    String url = image.getImageUrl();
                    int imageId = (int) image.getId();
                    image.setId(imageId);
                    String d = image.getImageDescription();
                    boolean p = image.isProfilePic();
                    boolean a = image.isAfterAdoption();
                    image.setImageDescription(d);
                    image.setProfilePic(p);
                    image.setAfterAdoption(a);
                    image.setImageUrl(url);
                    image.setPet(pet);
                    petImageRepository.save(image);
                }
            }

            for (int i = 0; i < imageUrls.size(); i++) {

                if (!imageUrls.get(i).isEmpty()) {
                    PetImage petImage = new PetImage(pet);

                        petImage.setImageUrl(imageUrls.get(i));
                        petImageRepository.save(petImage);
                        long imageID = petImage.getId();
                        PetImage imageAdded = petImageRepository.findById(imageID);
                        imageAdded.setImageDescription(imageDescriptions.get(i));
                        imageAdded.setProfilePic(profilePicture.get(i));
                        imageAdded.setAfterAdoption(afterAdopt.get(i));
                        petImageRepository.save(imageAdded);
                        model.addAttribute("message", "File successfully uploaded!");

                }
            }

            petsRepository.save(pet);
            return "redirect:/pets/pet" + id;
        }
    }


}