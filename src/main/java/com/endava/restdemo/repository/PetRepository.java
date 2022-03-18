package com.endava.restdemo.repository;

import com.endava.restdemo.model.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class PetRepository {

    private Map<Long, Pet> pets = new HashMap<>();

    @PostConstruct
    private void init() {createPets();}

    private void createPets() {
        Pet pet = new Pet();
        Category category = new Category();
        category.setId(1L);
        category.setName("categoryName1");
        pet.setId(1L);
        pet.setCategory(category);
        pet.setPhotoUrls(new ArrayList<>(Arrays.asList("abc", "dac")));
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("tagName1");
        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("tagName2");
        pet.setTags(new ArrayList<>(Arrays.asList(tag1, tag2)));
        pet.setStatus(StatusPet.available.name());
        pets.put(pet.getId(), pet);

    }

    public void delete(Long id) {
        pets.remove(id);
    }
    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable(pets.getOrDefault(id, null));
    }
    public Optional<Pet> findByStatus(String status) {
        return Optional.ofNullable(pets.getOrDefault(status, null));
    }
    public void save(Pet pet) {
        pets.compute(pet.getId(), (key, value) -> pet);
    }
}
