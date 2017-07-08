package com.capstone.repositories;

import com.capstone.models.Filter;
import com.capstone.models.Pet;
import com.capstone.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface PetsRepository extends CrudRepository<Pet, Long> {

    public Pet findById(Long id);

    public List<Pet> findAllByName(String name);

    public List<Pet> findAllByAdopter(User adopter);

    public List<Pet> findAllByFoster(User foster);

    public List<Pet> findAllByReadyToAdopt(Boolean trueOrFalse);

    public List<Pet> findAllByReadyToAdoptAndSpecies(Boolean trueOrFalse, String species);

    public List<Pet> findAllByReadyToAdoptAndSpeciesAndBreed(Boolean trueOrFalse, String species, String breed);

    @Query ("Select p from Pet p inner join p.filtersPets f where f.id = :passID")
    public ArrayList<Pet> findPetsByFilter(@Param("passID") long passID);

    @Query(value = "select p.species from Pet p where p.readyToAdopt = true group by p.species")
    public List<String> findSpecies();

    @Query(value = "select p.breed from Pet p where p.readyToAdopt = true and p.species = :passSpecies group by p.breed")
    public List<String> findBreedBySpecies(@Param("passSpecies") String passSpecies);


}
