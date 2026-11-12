package com.mario.main.controller;

import com.mario.main.entity.Pet;
import com.mario.main.service.PetService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }


    // Add a new pet
    @PostMapping
    public ResponseEntity<Pet> addPet(
            @RequestBody Pet pet,
            @RequestParam Long ownerId) {

        Pet savedPet = petService.addPet(pet, ownerId);

        return new ResponseEntity<>(
                savedPet,
                HttpStatus.CREATED
        );
    }


    // Get all pets
    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {

        return ResponseEntity.ok(
                petService.getAllPets()
        );
    }


    // Get pets by owner ID
    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Pet>> getPetsByOwner(
            @PathVariable Long ownerId) {

        return ResponseEntity.ok(
                petService.getPetsByOwner(ownerId)
        );
    }

}