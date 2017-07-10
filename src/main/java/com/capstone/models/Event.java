package com.capstone.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by frenchfryes on 7/9/17.
 */

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    @NotBlank(message = "Event must have a title")
    @Size(min = 5, message = "Event name must have at least 5 characters")
    private String title;

    @Column()
    private String imageLoc;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "Event must have a description")
    @Size(min = 10, message = "Event description must be at least 10 characters")
    private String eventDesc;

    @Column()
    private String link;
    @Column()
    private String date;

    @Column()
    private String street1;

    @Column()
    private String street2;

    @Column()
    private String city1;

    @Column()
    private  String state1;

    @Column()
    private String zip1;

    @ManyToOne
    @JsonManagedReference
    private User owner;

    public Event(String title, String imageLoc, String eventDesc, String link, String date, String street1, String street2, String city1, String state1, String zip1){
        this.title = title;
        this.imageLoc = imageLoc;
        this.eventDesc = eventDesc;
        this.link = link;
        this.date = date;
        this.street1 = street1;
        this.street2 = street2;
        this.city1 = city1;
        this.state1 = state1;
        this.zip1 = zip1;
    }

    public Event(){}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public void setState1(String state1) {
        this.state1 = state1;
    }

    public void setZip1(String zip1) {
        this.zip1 = zip1;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void getLink(String link){
        this.link = link;
    }

    public String getStreet1() {
        return street1;
    }

    public String getStreet2() {
        return street2;
    }

    public String getCity1() {
        return city1;
    }

    public String getState1() {
        return state1;
    }

    public String getZip1() {
        return zip1;
    }

    public String getLink() {
        return link;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public String getImageLoc() {
        return imageLoc;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
