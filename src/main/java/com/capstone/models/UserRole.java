package com.capstone.models;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinTable (name = "user_id")
    private User user;

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public UserRole() {
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
