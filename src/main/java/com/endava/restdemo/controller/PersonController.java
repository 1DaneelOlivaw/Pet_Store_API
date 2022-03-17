package com.endava.restdemo.controller;

import com.endava.restdemo.exception.DataNotFoundException;
import com.endava.restdemo.exception.InvalidDataException;
import com.endava.restdemo.model.Person;
import com.endava.restdemo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Person Rest Controller", tags = "/persons")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> getAllPersons() {
    return personService.findAll();
  }

  @ApiOperation(value = "Get User by id ", response = Person.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success|OK"),
      @ApiResponse(code = 401, message = "Not Authorized!"),
      @ApiResponse(code = 403, message = "Forbidden!"),
      @ApiResponse(code = 404, message = "Not Found!") })
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Person> getPersonById(@PathVariable Long id) {
    if(id < 0)
    {
      throw new DataNotFoundException("person not found for id");
    }

    return personService.findById(id);
  }

  @Validated
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void savePerson(@RequestBody @ApiParam @Valid Person person, BindingResult bindingResult) {
    if(bindingResult.hasErrors())
    {
      throw new InvalidDataException("invalid person");
    }

    personService.save(person);
  }

  @DeleteMapping(path = "/{id}")
  public void deletePerson(@PathVariable() Long id)
  {
    personService.delete(id);
  }
}
