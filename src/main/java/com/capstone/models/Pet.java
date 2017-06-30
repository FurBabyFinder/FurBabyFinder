package com.capstone.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 45)
    @NotBlank(message = "Pet must have a name")
    @Size(min = 2, message = "Pet name must be at least 2 characters")
    private String name;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "Pet must have a story")
    @Size(min = 50, message = "Pet name must be at least 50 characters")
    private String story;

    @Column(nullable = true, length = 2)
    private int age;

    @Column(nullable = true, length = 1000)
    private String privateNotes;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User foster;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User adopter;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "filter_pets", // no model .... virtual table
            joinColumns = {@JoinColumn(name="pet_id")},
            inverseJoinColumns = {@JoinColumn(name="filter_id")}
    )
    private List<Filter> filters;

    public Pet(String name, String story, int age, String privateNotes) {
        this.name = name;
        this.story = story;
        this.age = age;
        this.privateNotes = privateNotes;
    }

    public  Pet (){}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPrivateNotes() {
        return privateNotes;
    }

    public void setPrivateNotes(String privateNotes) {
        this.privateNotes = privateNotes;
    }

    public User getFoster() {
        return foster;
    }

    public void setFoster(User foster) {
        this.foster = foster;
    }

    public User getAdopter() {
        return adopter;
    }

    public void setAdopter(User adopter) {
        this.adopter = adopter;
    }
}
