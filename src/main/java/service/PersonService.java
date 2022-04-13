package service;

import Models.Person;
import Repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonService {
    @Autowired
    PersonRepository p;

    //find all
    public List<Person> findAllPeople(){
        return (List<Person>) p.findAll();
    }

    //save person
    public savePerson(Person p){
        return p.save(p);
    }
}
