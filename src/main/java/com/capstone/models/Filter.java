package com.capstone.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table (name = "filters")
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String filterName;

    @ManyToMany(mappedBy="filtersPets")
    private List<Pet> petCollection;


    public Filter(){
        petCollection = new ArrayList<Pet>();
    }

    public Filter(String filterName) {
        this.filterName = filterName;
    }

    public long getId() {
        return id;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }


    public List<Pet> getPetCollection() {
        return petCollection;
    }
}
