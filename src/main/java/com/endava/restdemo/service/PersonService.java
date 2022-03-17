package com.endava.restdemo.service;

import com.endava.restdemo.model.Person;
import com.endava.restdemo.model.SearchCriteria;
import com.endava.restdemo.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> findAll(){
		return personRepository.findAll();
	}

	public Optional<Person> findById(Long id) {
		return personRepository.findById(id);
	}

	public void save(Person person) {
		personRepository.save(person);

	}

	public List<Person> filterBySearchCriteria(SearchCriteria criteria) {
		return new ArrayList<>();
	}

  public void delete(Long id) {
		personRepository.delete(id);
  }
}
