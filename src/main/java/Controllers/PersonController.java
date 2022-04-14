package Controllers;

import Models.Person;
import jdk.jpackage.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 public ResponseEntity<List<Person>> findAll(){
        Log.info("getting all users");
        List<Person> people = p.getAllPeople();
        if (people == null || people.isEmpty()){
            Log.info("no people found");
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(people, HttpStatus.OK);
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
