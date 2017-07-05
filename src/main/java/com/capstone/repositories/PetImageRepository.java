package com.capstone.repositories;

import com.capstone.models.Pet;
import com.capstone.models.PetImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 7/5/17.
 */
public interface PetImageRepository extends CrudRepository <PetImage, Long>{

    public PetImage findById(Long id);

    public List<PetImage> findAllByPet(Pet pet);

}
