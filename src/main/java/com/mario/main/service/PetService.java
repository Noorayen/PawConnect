package com.mario.main.service;

import com.mario.main.entity.Pet;
import com.mario.main.entity.User;
import com.mario.main.repository.PetRepository;
import com.mario.main.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public PetService(PetRepository petRepository,
                      UserRepository userRepository) {

        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }


    // Add a pet to an owner
    public Pet addPet(Pet pet, Long ownerId) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() ->
                        new RuntimeException("Owner not found"));

        pet.setOwner(owner);

        return petRepository.save(pet);
    }


    // Get all pets
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }


    // Get pets belonging to one owner
    public List<Pet> getPetsByOwner(Long ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

}