package com.onlinetutorialspoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.onlinetutorialspoint.entity.Person;
import com.onlinetutorialspoint.repository.PersonRepository;
import com.onlinetutorialspoint.service.PersonService;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Autowired
	PersonService personService;
	
	@Bean
	public CommandLineRunner run(PersonRepository repository) {
		return (args) -> {
			Person person = new Person();
			person.setName("Chandra Shekhar Goka");
			person.setCity("Hyderabad");
			Person p = savePersonDetails(person);
			
			System.out.println("Person Id : "+p.getId() +" Person Name : "+p.getName());
		};
	}
	
	public Person savePersonDetails(Person p){
		return personService.savePerson(p);
	}
	
	public Person getPerson(Person person){
		return personService.getPerson(person.getId());
	}
}
