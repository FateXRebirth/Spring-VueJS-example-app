package example.Service;

import example.Entity.Person;
import example.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void create(Person person) {
        personRepository.save(person);
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id);
    }

    public Person getPersonByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public List<String> getAllUsername() {
        return personRepository.findAllUsername();
    }

    public List<String> getAllEmail() {
        return personRepository.findAllEmail();
    }

}
