package com.endava.restdemo.controller;

import com.endava.restdemo.exception.DataNotFoundException;
import com.endava.restdemo.exception.InvalidDataException;
import com.endava.restdemo.model.Pet;
import com.endava.restdemo.model.StatusPet;
import com.endava.restdemo.service.PetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "Everything about your Pets", tags = "/pet")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/pet")
public class PetController {

    private final PetService petService;

    @ApiOperation(value = "Find pet by ID", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found!") })
    @GetMapping(path = "/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Pet> getPetById(@PathVariable Long id){
        if(id < 0)
        {
            throw new DataNotFoundException("pet not found");
        }
        return petService.findById(id);
    }

    @ApiOperation(value = "Find pet by Status", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation"),
            @ApiResponse(code = 400, message = "Invalid status value")})
    @GetMapping(path = "/findByStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Pet> getPetByStatus(@PathVariable String status){
        if(!Arrays.stream(StatusPet.values()).anyMatch(x -> Objects.equals(x, status)))
        {
            throw new DataNotFoundException("pet not found");
        }
        return petService.findByStatus(status);
    }

    @ApiOperation(value = "Delete pet by Id", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Pet not found")})
    @DeleteMapping(path = "/{petId}")
    public void deletePet(@PathVariable() Long id)
    {
        if(id < 0)
        {
            throw new DataNotFoundException("pet not found");
        }
        petService.delete(id);
    }

    @Validated
    @ApiOperation(value = "Add new pet to the store", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input")})
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void savePet(@RequestBody @ApiParam @Valid Pet pet, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            throw new InvalidDataException("invalid pet");
        }

        petService.save(pet);
    }

    @Validated
    @ApiOperation(value = "Updates a pet in the store with form data", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 405, message = "Invalid input")})
    @PostMapping(path = "/{petId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void savePetWithPetId(@RequestBody @ApiParam @Valid Pet pet, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            throw new InvalidDataException("invalid pet");
        }

        petService.save(pet);
    }

    @Validated
    @ApiOperation(value = "uploads an image", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation")})
    @PostMapping(path = "/{petId}/uploadImage",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImage(@RequestBody @ApiParam @Valid Pet pet, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            throw new InvalidDataException("invalid pet");
        }

        petService.save(pet);
        // returns ApiResponse?
    }

    @ApiOperation(value = "Update an existing pet", response = Pet.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "invalid id supplied"),
            @ApiResponse(code = 404, message = "pet not found"),
            @ApiResponse(code = 404, message = "validation exception")})
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePet(@RequestBody @ApiParam @Valid Pet pet, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            throw new InvalidDataException("invalid pet");
        }

        petService.save(pet);
    }


}
