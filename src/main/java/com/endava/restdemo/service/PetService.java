package com.endava.restdemo.service;

import com.endava.restdemo.model.Person;
import com.endava.restdemo.model.Pet;
import com.endava.restdemo.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {this.petRepository = petRepository;}

    public Optional<Pet> findById(Long id) {
        return petRepository.findById(id);
    }

    public Optional<Pet> findByStatus(String status) {
        return petRepository.findByStatus(status);
    }

    public void save(Pet pet) {
        petRepository.save(pet);

    }
    public void delete(Long id) {
        petRepository.delete(id);}

}
