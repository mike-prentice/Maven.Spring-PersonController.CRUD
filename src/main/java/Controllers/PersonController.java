package Controllers;

import Models.Person;
import jdk.jpackage.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.PersonService;


import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService p;

    @PostMapping(value = "/people")
    public ResponseEntity<Void> create(@RequestBody Person person, UriComponentsBuilder ucBuilder) {
        Log.info("creating new user:" + person);
        Long id = person.getId();
        if (p.exists(id)) {
            Log.info("a user with name " + person.getFirstName() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        p.savePerson(person);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/users/{id}").buildAndExpand(person.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/people/{id}")
    public ResponseEntity<Person> get(@PathVariable("id") Long id){
        Log.info("getting user with id:" + id);
        Person person = p.findOnePerson(id);
        if (person == null){
            Log.info("user with" + id + "not found");
            return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Person>(person, HttpStatus.OK);
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
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        Log.info("deleting user with id:" + id);
        Person person = p.findOnePerson(id);

        if (person == null){
            Log.info("Unable to delete. User with" + id + "not found" );
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        p.deletePersonById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
