package com.capstone.models;

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
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private String streetAdd1;

    @Column()
    private String streetAdd2;

    @Column()
    private String city;

    @Column()
    private  String state;

    @Column()
    private String zip;

    @Column()
    private String mainPhone;

    @Column()
    private String altPhone;

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

    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.password = user.password;
        this.email = user.email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAdd1() {
        return streetAdd1;
    }

    public void setStreetAdd1(String streetAdd1) {
        this.streetAdd1 = streetAdd1;
    }

    public String getStreetAdd2() {
        return streetAdd2;
    }

    public void setStreetAdd2(String streetAdd2) {
        this.streetAdd2 = streetAdd2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
