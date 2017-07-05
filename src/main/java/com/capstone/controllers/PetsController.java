package com.capstone.controllers;

import com.capstone.models.Filter;
import com.capstone.models.Pet;

import com.capstone.repositories.FilterRepository;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
@Controller
public class PetsController {

    private final PetsRepository petsRepository;
    private final FilterRepository filterRepository;
    private final UsersRepository usersRepository;

    @Value("${file-upload-path}")
    private String uploadPath;

    @Autowired
    public PetsController(PetsRepository petsRepository, UsersRepository usersRepository, FilterRepository filterRepository) {
        this.petsRepository = petsRepository;
        this.usersRepository = usersRepository;
        this.filterRepository = filterRepository;
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
        model.addAttribute("pets", filteredPets);
        return "pets/index";
    }

    @GetMapping ("/pets/add")
    public String showCreateForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "pets/addPet";
    }

    @PostMapping("/pets/addPet")
    public String create(
            @Valid Pet pet,
            Errors validation,
            @RequestParam (name ="filterName") List<String> filterNames,
//            @RequestParam(name = "great_with_kids") String kidsyes,
//            @RequestParam(name = "neuterd_spayed") String nuetyes,
//            @RequestParam(name = "potty_trained") String pottyYes,
            Model model){
        if (validation.hasErrors()){
            model.addAttribute("errors", validation);
            model.addAttribute("pet", pet);
            return "pets/addPet";
        }
        else {
//            String filename =  uploadedfile.getOriginalFilename().replace(" ", "_");
//            String filepath = Paths.get(uploadPath, filename).toString();
//            File destinationFile = new File(filepath);


//            try {
//                pet.setImageUrl(filename);
//                uploadedfile.transferTo(destinationFile);
//                model.addAttribute("message", "File successfully uploaded!");
//            } catch (IOException e) {
//                e.printStackTrace();
//                model.addAttribute("message", "Oops! Something went wrong! " + e);
//            }
            List<Filter> filters = new ArrayList<>();
            for(String name : filterNames){
                filters.add(filterRepository.findByFilterName(name));
            }
            pet.setFiltersPets(filters);
            petsRepository.save(pet);
            Long id = pet.getId();
//            List<Filter> petFilterList = new ArrayList<>();
//            System.out.println(catCompatibility);
//            if(catCompatibility != null){
//                Filter catFilter  =  new Filter();
//                 catFilter = filterRepository.findByFilterName(catCompatibility);
//                petFilterList.add(catFilter);
//                System.out.println(petFilterList.size());
//            }

//            pet.setFiltersPets(petFilterList);
            return "redirect:/pets/all";
        }
    }




}
