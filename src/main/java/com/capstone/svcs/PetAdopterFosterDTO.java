package com.capstone.svcs;

import com.capstone.models.Pet;
import com.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by melodytempleton on 7/12/17.
 */
public class PetAdopterFosterDTO implements Serializable {

    private List<User> adopters;

    private List<User> fosters;

    private List<Pet> pets;

    public PetAdopterFosterDTO() {
    }

    public PetAdopterFosterDTO(List<User> adopters, List<User> fosters, List<Pet> pets) {
        this.adopters = adopters;
        this.fosters = fosters;
        this.pets = pets;
    }

    public List<User> getAdopters() {
        return adopters;
    }

    public void setAdopters(List<User> adopters) {
        this.adopters = adopters;
    }

    public List<User> getFosters() {
        return fosters;
    }

    public void setFosters(List<User> fosters) {
        this.fosters = fosters;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
