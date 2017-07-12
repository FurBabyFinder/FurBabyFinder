package com.capstone.controllers;

import com.capstone.models.Pet;
import com.capstone.models.User;
import com.capstone.repositories.FilterRepository;
import com.capstone.repositories.PetImageRepository;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodytempleton on 7/11/17.
 */
@Controller
public class PetSearchController {

    private final PetsRepository petsRepository;
    private final FilterRepository filterRepository;
    private final PetImageRepository petImageRepository;
    private final UsersRepository usersRepository;


    @Autowired
    public PetSearchController(PetsRepository petsRepository, UsersRepository usersRepository, FilterRepository filterRepository, PetImageRepository petImageRepository) {
        this.petsRepository = petsRepository;
        this.usersRepository = usersRepository;
        this.filterRepository = filterRepository;
        this.petImageRepository = petImageRepository;
    }

    private void filterThePets(List<String> selection, ArrayList<Pet> filteredPets){
        if (!selection.get(0).equals("all")) {
            for (int i = 0; i < selection.size(); i++) {
                Long filterID = filterRepository.findFilterIDByFilterName(selection.get(i));
                ArrayList tempArray = petsRepository.findPetsByFilter(filterID);
                filteredPets.retainAll(tempArray);
            }
        }
    }

    private List<User> getUserList(String firstName, String lastName){
        List <User> users = new ArrayList<>();
        if (firstName.equals("none")){
            users = usersRepository.findAllByLastName(lastName);
        }
        else if (lastName.equals("none")){
            users = usersRepository.findAllByFirstName(firstName);
        }
        else {
            users = usersRepository.findAllByFirstNameAndLastName(firstName, lastName);
        }
        return users;
    }

    private List<Pet> getPetList (Boolean ready, Boolean exclude, User foster) {
        List<Pet> pets = new ArrayList<>();
        if ((ready == true) & (exclude == true)) {
            pets = petsRepository.findAllByAdopterAndFosterAndReadyToAdopt(null, foster, true);
        } else if (exclude == true) {
            pets = petsRepository.findAllByAdopterAndFoster(null, foster);
        } else if (ready == true) {
            pets = petsRepository.findAllByReadyToAdoptAndFoster(true, foster);
        } else {
            pets = petsRepository.findAllByFoster(foster);
        }
        return pets;
    }


        @GetMapping("/petTypes.json")
    public @ResponseBody
    Iterable<String> listSpecies() {
        return petsRepository.findSpecies();
    }

    @GetMapping("/petBreeds.json")
    public @ResponseBody
    Iterable<String> listBreeds(@RequestParam String species) {
        return petsRepository.findBreedBySpecies(species);
    }



    @RequestMapping(path = "/pets/{selection}", method = RequestMethod.GET)
    public String indexPage(Model model,
                            @PathVariable List<String> selection) {

        ArrayList<Pet> pets = (ArrayList<Pet>) petsRepository.findAllByReadyToAdopt(true);
        ArrayList<Pet> filteredPets = new ArrayList<>(pets);
        filterThePets(selection, filteredPets);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", filteredPets);
        return "pets/index";
    }

    @RequestMapping(path = "/pets/{species}/type/{selection}", method = RequestMethod.GET)
    public String indexPage(Model model,
                            @PathVariable List<String> selection,
                            @PathVariable String species) {

        ArrayList<Pet> pets = (ArrayList<Pet>) petsRepository.findAllByReadyToAdoptAndSpecies(true, species);
        ArrayList<Pet> filteredPets = new ArrayList<>(pets);
        filterThePets(selection, filteredPets);

        model.addAttribute("breeds", petsRepository.findBreedBySpecies(species));
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("species", species);
        model.addAttribute("pets", filteredPets);
        return "pets/index";
    }

    @RequestMapping(path = "/pets/{species}/breed/{breed}/{selection}", method = RequestMethod.GET)
    public String indexPage(Model model,
                            @PathVariable List<String> selection,
                            @PathVariable String breed,
                            @PathVariable String species) {

        ArrayList<Pet> pets = (ArrayList<Pet>) petsRepository.findAllByReadyToAdoptAndSpeciesAndBreed(true, species, breed);
        ArrayList<Pet> filteredPets = new ArrayList<>(pets);
        filterThePets(selection, filteredPets);

        model.addAttribute("breeds", petsRepository.findBreedBySpecies(species));
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("species", species);
        model.addAttribute("pets", filteredPets);
        return "pets/index";
    }


    @GetMapping("/pets/findOne")
    public String SearchById (Model model){
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/searchIndividual";
    }

    @GetMapping("/pets/searchID/{id}")
    public String SearchById (Model model,
                              @PathVariable long id ){
        List<Pet> pets = new ArrayList<>();
        pets.add(petsRepository.findById(id));
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/searchIndividual";
    }


    @GetMapping("/pets/searchName/{name}")
    public String SearchByName (Model model,
                                @PathVariable String name ){
        List<Pet> pets = petsRepository.findAllByName(name);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/searchIndividual";
    }

    @GetMapping("/pets/searchByUser")
    public String SearchByUser (Model model){
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/findPetsByUser";
    }

    @GetMapping("/pets/searchAdopterID/{id}")
    public String SearchByAdopterId (Model model,
                                     @PathVariable long id ){
        User adopter = usersRepository.findOne(id);
        List<Pet> pets = petsRepository.findAllByAdopter(adopter);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }

    @GetMapping("/pets/{firstName}/searchAdopterName/{lastName}")
    public String SearchByAdopterName (Model model,
                                       @PathVariable String firstName,
                                       @PathVariable String lastName){
        List<User> adopters = getUserList(firstName, lastName);
        List <Pet> pets = new ArrayList<>();
        for (User adopter : adopters) {
            List<Pet> tempPets = petsRepository.findAllByAdopter(adopter);
            pets.addAll(tempPets);
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }


    @GetMapping("/pets/searchFosterID/{id}/{ready}/{exclude}")
    public String SearchByFosterId (Model model,
                                    @PathVariable long id,
                                    @PathVariable boolean ready,
                                    @PathVariable boolean exclude){
        User foster = usersRepository.findOne(id);

        List<Pet> pets = getPetList(ready,exclude,foster);

        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }


    @GetMapping("/pets/{firstName}/searchFosterName/{lastName}/{ready}/{exclude}")
    public String SearchByFosterName (Model model,
                                      @PathVariable String firstName,
                                      @PathVariable String lastName,
                                      @PathVariable boolean ready,
                                      @PathVariable boolean exclude){
        List<User> fosters = getUserList(firstName, lastName);
        List <Pet> pets = new ArrayList<>();
        for (User foster : fosters) {
            List<Pet> tempPets = getPetList(ready,exclude,foster);
            pets.addAll(tempPets);
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }

}
