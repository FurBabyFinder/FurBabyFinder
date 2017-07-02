package com.capstone.repositories;

import com.capstone.models.Pet;
import com.capstone.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 6/30/17.
 */
public interface PetsRepository extends CrudRepository<Pet, Long> {

    public Pet findById(Long id);
    
    public List<Pet> findAllByName(String name);

    public List<Pet> findAllByAdopter(User adopter);

    public List<Pet> findAllByFoster(User foster);

}
