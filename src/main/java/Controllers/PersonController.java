package Controllers;

import Models.Person;
import Repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService p;

    @PostMapping(value = "/people")
    Person save(@Valid @RequestBody Person person){
         return p.savePerson(person);
    }


    @GetMapping(value = "/people/{id}")
    public Person getPerson(@PathVariable("id") Long id){
        return p.findOnePerson(id);
    }


    @GetMapping(value = "/people")
 public List<Person> findAll(){
        return p.getAllPeople();
    }




   @PutMapping(value = "/people/{id}")
    Person updatePerson(@PathVariable("id") Person p){
        return p;
    }


    @DeleteMapping(value = "/people/{id}")
    public String DeletePerson(@PathVariable("id") Long id){
        p.deletePersonById(id);
        return "Successful Deletion";
    }
}
