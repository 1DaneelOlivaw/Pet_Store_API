package com.endava.restdemo.repository;

import com.endava.restdemo.model.Person;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

	private Map<Long, Person> persons = new HashMap<>();

	@PostConstruct
	private void init() {
		createPersons();
	}

	private void createPersons() {
		Person person = new Person();
		person.setId(1L);
		person.setUsername("sherlock.holmes");
		person.setFirstName("Sherlock");
		person.setLastName("Holmes");
		person.setPassword("dudu");
		person.setHiringDate(LocalDateTime.of(2019, Month.AUGUST, 15, 13, 23));
		persons.put(person.getId(), person);

		person = new Person();
		person.setId(2L);
		person.setUsername("jackson.brodie");
		person.setFirstName("Jackson");
		person.setLastName("Brodie");
		person.setPassword("bagy");
		person.setHiringDate(LocalDateTime.of(2020, Month.JUNE, 15, 12, 23));
		persons.put(person.getId(), person);

		person = new Person();
		person.setId(3L);
		person.setUsername("nancy.drew");
		person.setFirstName("Nancy");
		person.setLastName("Drew");
		person.setPassword("dada45");
		person.setHiringDate(LocalDateTime.of(2018, Month.AUGUST, 15, 11, 23));
		persons.put(person.getId(), person);

		person = new Person();
		person.setId(4L);
		person.setUsername("irene.adler");
		person.setFirstName("Irene");
		person.setLastName("Adler");
		person.setPassword("xxxyy");
		person.setHiringDate(LocalDateTime.of(1983, Month.AUGUST, 15, 10, 23));
		persons.put(person.getId(), person);

	}

	public List<Person> findAll() {
		return new ArrayList<>(persons.values());
	}

	public void save(Person person) {
		persons.compute(person.getId(), (key, value) -> person);
	}

	public Optional<Person> findById(Long id) {
		return Optional.ofNullable(persons.getOrDefault(id, null));
	}

	public void delete(Long id) {
		persons.remove(id);
	}
}
