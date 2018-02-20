package example;

import example.Entity.Person;
import example.Repository.PersonRepository;
import example.Service.PersonService;
import javafx.beans.binding.ObjectExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * Created by FateXRebirth on 25/09/2017.
 */

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Bean
    public CommandLineRunner demo(PersonService personService) {
        return (args) -> {

            log.info("-------You Can Test Here-------");
            personService.create(new Person("admin", "admin", "admin@example.com", "manager"));
        };
    }

}
