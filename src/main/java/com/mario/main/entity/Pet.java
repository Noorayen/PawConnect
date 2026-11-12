package com.mario.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String species;

    private String breed;

    private Integer age;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;


    // Default constructor
    public Pet() {
    }


    // Constructor
    public Pet(String name, String species,
               String breed, Integer age, User owner) {

        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.owner = owner;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }


    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}