package com.capstone.repositories;

import com.capstone.models.MedicalRecord;
import com.capstone.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by melodytempleton on 7/21/17.
 */
public interface MedicalRecordsRepository extends CrudRepository <MedicalRecord, Long> {

    public MedicalRecord findById(long id);

    public List<MedicalRecord> findAllByPet(Pet pet);


}
