package com.capstone.repositories;

import com.capstone.models.MedicalRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by melodytempleton on 7/21/17.
 */
public interface MedicalRecordsRepository extends CrudRepository <MedicalRecord, Long> {

    public MedicalRecord findById(long id);


}
