package service;

import Models.Person;
import Repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

public class PersonService {

    @Autowired
    PersonRepository pr;
    //save
    public Person savePerson(Person p){
        return pr.save(p);
    }
    //find all
    public List<Person> getAllPeople() {
        return (List<Person>) pr.findAll();
    }

    public Person findOnePerson(Long id){
        return pr.findOne(id);
    }

    public void deletePersonById(Long id) {
        pr.delete(id);
    }

}
