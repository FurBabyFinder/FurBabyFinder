package com.capstone.controllers;

import com.capstone.Pojos.ImageList;
import com.capstone.models.Filter;
import com.capstone.models.Pet;

import com.capstone.models.PetImage;
import com.capstone.models.User;
import com.capstone.repositories.FilterRepository;
import com.capstone.repositories.PetImageRepository;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import com.capstone.svcs.PetDTO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
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
        User foster = pet.getFoster();
        User adopter = pet.getAdopter();
        model.addAttribute("pet", pet);
        model.addAttribute("foster", foster);
        model.addAttribute("adopter", adopter);
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/individualPet";
    }



//
//    @GetMapping("/pets/add")
//    public String showCreateForm(Model model) {
//        model.addAttribute("list", petsRepository.findSpecies());
//        model.addAttribute("pet", new Pet());
//        return "pets/addPet";
//    }
//
//    @PostMapping("/pets/addPet")
//    public String create(
//            @Valid Pet pet,
//            Errors validation,
//            @RequestParam(name = "filterName") List<String> filterNames,
//            @RequestParam(name = "image") List<MultipartFile> uploadedfiles,
//            @RequestParam(name = "imageDescription[]") List<String> imageDescriptions,
//            @RequestParam(name = "profilePic[]") List<Boolean> profilePicture,
//            Model model) {
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("pet", pet);
//            return "pets/addPet";
//        } else {
//            List<Filter> filters = new ArrayList<>();
//            for (String name : filterNames) {
//                filters.add(filterRepository.findByFilterName(name));
//            }
//            pet.setFiltersPets(filters);
//            petsRepository.save(pet);
//            Long id = pet.getId();
//            Pet addedPet = petsRepository.findById(id);
//            for (int i = 0; i < uploadedfiles.size(); i++) {
//                if (!uploadedfiles.get(i).isEmpty()) {
//                    String filename = uploadedfiles.get(i).getOriginalFilename().replace(" ", "_");
//                    filename = id.toString() + filename;
//                    String filepath = Paths.get(uploadPath, filename).toString();
//                    File destinationFile = new File(filepath);
//                    PetImage petImage = new PetImage(addedPet);
//
//
//                    try {
//                        petImage.setImageUrl(filename);
//                        petImageRepository.save(petImage);
//                        long imageID = petImage.getId();
//                        PetImage imageAdded = petImageRepository.findById(imageID);
//                        uploadedfiles.get(i).transferTo(destinationFile);
//                        imageAdded.setImageDescription(imageDescriptions.get(i));
//                        imageAdded.setProfilePic(profilePicture.get(i));
//                        petImageRepository.save(imageAdded);
//                        model.addAttribute("message", "File successfully uploaded!");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        model.addAttribute("message", "Oops! Something went wrong! " + e);
//                    }
//                }
//            }
//
//            return "redirect:/pets/all";
//        }
//    }

//    @GetMapping("/pets/{id}/edit")
//    public String showEditForm(@PathVariable long id, Model model) {
//        Pet pet = petsRepository.findById(id);
//        List <Filter> petsFilters = pet.getFilters();
//        List<PetImage> petsImages = pet.getImages();
//        int numberImages = petsImages.size();
//        PetDTO petDTO = new PetDTO(pet, petsImages);
//        model.addAttribute("filters", petsFilters);
//        model.addAttribute("petDTO", petDTO);
//        model.addAttribute("imageCount", numberImages);
//        model.addAttribute("list", petsRepository.findSpecies());
//
//        return "pets/edit";
//    }

//    @PostMapping("/pets/{id}/edit")
//    public String editPet(
////            @ModelAttribute Pet pet,
////            @ModelAttribute("imageList") ImageList imageList,
//            @ModelAttribute ("petDTO") PetDTO petDTO,
//            Errors validation,
//            @PathVariable long id,
//            @RequestParam(name = "filterName") List<String> filterNames,
//            @RequestParam(name = "image") List<MultipartFile> uploadedfiles,
//            @RequestParam(name = "imageReplace") List<MultipartFile> replacefiles,
//            @RequestParam(name = "imageDescription[]") List<String> imageDescriptions,
//            @RequestParam(name = "profilePic[]") List<Boolean> profilePicture,
//            @RequestParam(name = "afterAdopt[]") List<Boolean> afterAdopt,
//            Model model) throws IOException {
//        if (validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("pet", petDTO.getPet());
//            return "pets/edit";
//        } else {
//            List<Filter> filters = new ArrayList<>();
//            for (String name : filterNames) {
//                filters.add(filterRepository.findByFilterName(name));
//            }
//            Pet pet = petDTO.getPet();
//            List<PetImage> imageList = petDTO.getImageList();
//            pet.setId(id);
//            pet.setFiltersPets(filters);
//
//            if(imageList != null) {
//                for (int i = 0; i < imageList.size(); i++) {
//                    if (!replacefiles.get(i).getOriginalFilename().equals("")) {
//                        String filename = replacefiles.get(i).getOriginalFilename().replace(" ", "_");
//                        filename = id + filename;
//                        String filepath = Paths.get(uploadPath, filename).toString();
//                        File destinationFile = new File(filepath);
//                        imageList.get(i).setImageUrl(filename);
//                        replacefiles.get(i).transferTo(destinationFile);
//                    }
//                }
//            }
//            if(imageList != null) {
//                for (PetImage image : imageList) {
//                    String url = image.getImageUrl();
//                    int imageId = (int) image.getId();
//                    image.setId(imageId);
//                    String d = image.getImageDescription();
//                    boolean p = image.isProfilePic();
//                    boolean a = image.isAfterAdoption();
//                    image.setImageDescription(d);
//                    image.setProfilePic(p);
//                    image.setAfterAdoption(a);
//                    image.setImageUrl(url);
//                    image.setPet(pet);
//                    petImageRepository.save(image);
//                }
//            }
//
//            for (int i = 0; i < uploadedfiles.size(); i++) {
//                if (!uploadedfiles.get(i).isEmpty()) {
//                    String filename = uploadedfiles.get(i).getOriginalFilename().replace(" ", "_");
//                    filename = id + filename;
//                    String filepath = Paths.get(uploadPath, filename).toString();
//                    File destinationFile = new File(filepath);
//                    PetImage petImage = new PetImage(pet);
//
//                    try {
//                        petImage.setImageUrl(filename);
//                        petImageRepository.save(petImage);
//                        long imageID = petImage.getId();
//                        PetImage imageAdded = petImageRepository.findById(imageID);
//                        uploadedfiles.get(i).transferTo(destinationFile);
//                        imageAdded.setImageDescription(imageDescriptions.get(i));
//                        imageAdded.setProfilePic(profilePicture.get(i));
//                        imageAdded.setAfterAdoption(afterAdopt.get(i));
//
//                        petImageRepository.save(imageAdded);
//                        model.addAttribute("message", "File successfully uploaded!");
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        model.addAttribute("message", "Oops! Something went wrong! " + e);
//                    }
//                }
//            }
//
//            petsRepository.save(pet);
//            return "redirect:/pets/pet" + id;
//        }
//    }

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