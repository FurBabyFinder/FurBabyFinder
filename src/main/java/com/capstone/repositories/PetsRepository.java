package com.capstone.repositories;

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

    @Query ("Select p from Pet p inner join p.filtersPets f where f.id = :passID")
    public ArrayList<Pet> findPetsByFilter(@Param("passID") long passID);


}

// SELECT * FROM Hospital WHERE Postcode = 3000 AND Hospital_id IN
//         (SELECT Hospital_id FROM Hospital_Medical hm INNER JOIN Medical_Service m ON hm.Medical_id = m.Medical_id
//         where Medical_name = 'Emergency')  (SELECT Pet FROM Pet.filters WHERE Filter.id = :passID) AND (@Param("passID") long passID)