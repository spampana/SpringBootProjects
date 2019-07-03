package com.onlinetutorialspoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlinetutorialspoint.dto.PersonDTO;
import com.onlinetutorialspoint.entity.Person;
import com.onlinetutorialspoint.repository.PersonRepository;

@Service
@Transactional
public class PersonService {
	
	@Autowired
	PersonRepository personRepo;

	public void savePersonDetails(PersonDTO personDto) {
		try {
			Person person = new Person();
			person.setCity(personDto.getpCity());
			person.setName(personDto.getpName());
			person.setId(personDto.getPid());
			personRepo.save(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Person> getAllPersons() {
		return (List<Person>) personRepo.findAll();
	}

	public Person getPerson(long id) {
		return personRepo.findOne(id);
	}

	public Person savePerson(Person person) {
		return personRepo.save(person);
	}
}