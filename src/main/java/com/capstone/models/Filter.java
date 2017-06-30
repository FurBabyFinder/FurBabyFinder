package com.capstone.models;


import javax.persistence.*;

@Entity
@Table (name = "filters")
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String filterName;

    public Filter(){}

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
}
