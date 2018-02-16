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
        personRepository.create(person);
    }

    public Person getPersonById(int id) {
        return personRepository.getPersonById(id);
    }

    public String getPersonPasswordById(int id) {
        return  personRepository.getPersonPasswordById(id);
    }

    public List<Person> getPersons() {
        return personRepository.getPersons();
    }

    public List<Map<String, Object>> getPersonsDetail() {
        return personRepository.getPersonsDetail();
    }

    public void StoredProcedure() {
        personRepository.StoredProcedure();
    }
}
