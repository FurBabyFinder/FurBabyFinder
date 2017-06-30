package com.capstone.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.util.List;

/**
 * Created by frenchfryes on 6/30/17.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column()
    private String first_name;

    @Column()
    private String last_name;

    @Column()
    private String street_addr_1;

    @Column()
    private String street_addr_2;

    @Column()
    private String zip;

    @Column()
    private String main_phone;

    @Column()
    private String alt_phone;

    @Column()
    private String FosterAppInfo;

    @Column()
    private String AdoptAppInfo;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "user_favorites", // no model .... virtual table
            joinColumns = {@JoinColumn(name="user")},
            inverseJoinColumns = {@JoinColumn(name="pet_id")}
    )
    private List<Pet> pets;

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(){

    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStreet_addr_1() {
        return street_addr_1;
    }

    public void setStreet_addr_1(String street_addr_1) {
        this.street_addr_1 = street_addr_1;
    }

    public String getStreet_addr_2() {
        return street_addr_2;
    }

    public void setStreet_addr_2(String street_addr_2) {
        this.street_addr_2 = street_addr_2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMain_phone() {
        return main_phone;
    }

    public void setMain_phone(String main_phone) {
        this.main_phone = main_phone;
    }

    public String getAlt_phone() {
        return alt_phone;
    }

    public void setAlt_phone(String alt_phone) {
        this.alt_phone = alt_phone;
    }

    public String getFosterAppInfo() {
        return FosterAppInfo;
    }

    public void setFosterAppInfo(String fosterAppInfo) {
        FosterAppInfo = fosterAppInfo;
    }

    public String getAdoptAppInfo() {
        return AdoptAppInfo;
    }

    public void setAdoptAppInfo(String adoptAppInfo) {
        AdoptAppInfo = adoptAppInfo;
    }
}
