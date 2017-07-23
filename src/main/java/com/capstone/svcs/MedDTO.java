package com.capstone.svcs;

import com.capstone.models.MedicalImage;
import com.capstone.models.MedicalRecord;
import com.capstone.models.Pet;

import java.io.Serializable;
import java.util.List;

/**
 * Created by melodytempleton on 7/23/17.
 */
public class MedDTO implements Serializable {


        private MedicalRecord medicalRecord;

        private List<MedicalImage> imageList;

        private Pet pet;

        public MedDTO(MedicalRecord medicalRecord, List<MedicalImage> imageList) {
            this.medicalRecord = medicalRecord;
            this.imageList = imageList;
        }

        public MedDTO() {
        }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Pet getPet() {
            return pet;
        }

        public void setPet(Pet pet) {
            this.pet = pet;
        }

        public List<MedicalImage> getImageList() {
            return this.imageList;
        }

        public void setImageList(List<MedicalImage> imageList) {
            this.imageList = imageList;
        }


}
