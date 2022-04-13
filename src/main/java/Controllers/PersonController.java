package Controllers;

import Models.Person;
import Repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService ps;

    @PostMapping(value = "/people")
    Person save(Person p){
        ps.save(p);
    }


    @GetMapping(value = "/people/{id}")
    public int getPerson(@PathVariable("id") int id){
        return id;
    }


    @GetMapping(value = "/people")

        return null;



   @PutMapping(value = "/people/{id}")
    Person updatePerson(@PathVariable("id") Person p){
        return p;
    }


    @DeleteMapping(value = "/people/{id}")
    void DeletePerson(@PathVariable("id") int id){}
}
