package com.onlinetutorialspoint.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.onlinetutorialspoint.entity.Person;
@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
}