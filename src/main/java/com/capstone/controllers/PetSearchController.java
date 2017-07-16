package com.capstone.controllers;

import com.capstone.models.Pet;
import com.capstone.models.User;
import com.capstone.repositories.FilterRepository;
import com.capstone.repositories.PetImageRepository;
import com.capstone.repositories.PetsRepository;
import com.capstone.repositories.UsersRepository;
import com.capstone.svcs.PetAdopterFosterDTO;
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
            users = usersRepository.findAllByLastNameStartingWith(lastName);
        }
        else if (lastName.equals("none")){
            users = usersRepository.findAllByFirstNameStartingWith(firstName);
        }
        else {
            users = usersRepository.findAllByFirstNameStartingWithAndLastName(firstName, lastName);
        }
        return users;
    }

    private List<Pet> getPetList (String ready, String excludeAdopted, User foster) {
        List<Pet> pets = new ArrayList<>();
        if ((ready.equals("true")) & (excludeAdopted.equals("true"))) {
            pets = petsRepository.findAllByAdopterAndFosterAndReadyToAdopt(null, foster, true);
        } else if ((ready.equals("exclude")) & (excludeAdopted.equals("true"))) {
            pets = petsRepository.findAllByAdopterAndFosterAndReadyToAdopt(null, foster, false);
        } else if (excludeAdopted.equals("only")) {
            pets = petsRepository.findAllByFosterAndAdopterIsNotNull(foster);
        } else if (excludeAdopted.equals("true")) {
            pets = petsRepository.findAllByAdopterAndFoster(null, foster);
        } else if (ready.equals("true")) {
            pets = petsRepository.findAllByReadyToAdoptAndFoster(true, foster);
        } else if (ready.equals("exclude")) {
            pets = petsRepository.findAllByReadyToAdoptAndFoster(false, foster);
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


    @GetMapping("/pets/searchAllPets")
    public String SearchAllPets (Model model){
        Iterable<Pet> pets = petsRepository.findAll();
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/searchIndividual";
    }



    @GetMapping("/pets/searchName/{name}")
    public String SearchByName (Model model,
                                @PathVariable String name ){
        List<Pet> pets = petsRepository.findAllByNameStartingWith(name);
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/searchIndividual";
    }

    @GetMapping("/pets/searchByUser")
    public String SearchByUser (Model model){
        model.addAttribute("list", petsRepository.findSpecies());
        return "pets/findPetsByUser";
    }


   @GetMapping("/pets/searchAll/{ready}/{excludeAdopted}")
    public String SearchByUser (Model model,
                                @PathVariable String ready,
                                @PathVariable String excludeAdopted){
        List<Pet> pets = new ArrayList<>();
        if(ready.equals("exclude") & excludeAdopted.equals("true")){
         pets = petsRepository.findAllByReadyToAdoptAndAdopterIsNull(false);
        }
        else if (ready.equals("true")){
            pets = petsRepository.findAllByReadyToAdopt(true);
        }
        else if (ready.equals("exclude")){
            pets = petsRepository.findAllByReadyToAdopt(false);
        }
        else if (excludeAdopted.equals("true")){
            pets = petsRepository.findAllByAdopter(null);
        }

        else if (excludeAdopted.equals("only")){
            pets = petsRepository.findAllByAdopterIsNotNull();
        }

        model.addAttribute("list", petsRepository.findSpecies());
       model.addAttribute("pets", pets);
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


     @GetMapping("/pets/searchAllAdopter")
    public String SearchAllAdopter (Model model){
        List<Pet> pets = petsRepository.findAllPetsWithAdopter();

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


    @GetMapping("/pets/searchFosterID/{id}/{ready}/{excludeAdopted}")
    public String SearchByFosterId (Model model,
                                    @PathVariable long id,
                                    @PathVariable String ready,
                                    @PathVariable String excludeAdopted){
        User foster = usersRepository.findOne(id);

        List<Pet> pets = getPetList(ready,excludeAdopted,foster);

        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }


    @GetMapping("/pets/{firstName}/searchFosterName/{lastName}/{ready}/{excludeAdopted}")
    public String SearchByFosterName (Model model,
                                      @PathVariable String firstName,
                                      @PathVariable String lastName,
                                      @PathVariable String ready,
                                      @PathVariable String excludeAdopted){
        List<User> fosters = getUserList(firstName, lastName);
        List <Pet> pets = new ArrayList<>();
        for (User foster : fosters) {
            List<Pet> tempPets = getPetList(ready,excludeAdopted,foster);
            pets.addAll(tempPets);
        }
        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }

    @GetMapping("/pets/searchAllFoster/{ready}/{excludeAdopted}")
    public String SearchAllFoster (Model model,
                                   @PathVariable String ready,
                                   @PathVariable String excludeAdopted){
        List<Pet> pets= new ArrayList<>();

            if ((ready.equals("true")) & (excludeAdopted.equals("true"))) {
                pets = petsRepository.findAllByReadyToAdoptAndAdopterAndFosterIsNotNull(true,null);
            }
            else if((ready.equals("exclude")) & (excludeAdopted.equals("true"))){
                pets = petsRepository.findAllByReadyToAdoptAndAdopterAndFosterIsNotNull(false, null);
            }
            else if (excludeAdopted.equals("true")) {
                pets = petsRepository.findAllByFosterIsNotNullAndAdopter(null);
            }
            else if (excludeAdopted.equals("only")) {
                pets = petsRepository.findAllByFosterIsNotNullAndAdopterIsNotNull();
            }
            else if (ready.equals("true")) {
                pets = petsRepository.findAllByReadyToAdoptAndFosterIsNotNull(true);
            }
            else if (ready.equals("exclude")) {
                pets = petsRepository.findAllByReadyToAdoptAndFosterIsNotNull(false);
            }
            else {
                pets = petsRepository.findAllByFosterIsNotNull();
            }


        model.addAttribute("list", petsRepository.findSpecies());
        model.addAttribute("pets", pets);
        return "pets/findPetsByUser";
    }




}
