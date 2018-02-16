package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Entity.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("person").usingGeneratedKeyColumns("id");;

    SimpleJdbcCall test = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource()).withProcedureName("test");

    private static final class PersonMapper implements RowMapper<Person> {

        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person(rs.getString("username"), rs.getString("password"),
                    rs.getString("email"), rs.getString("type"));
            return person;
        }
    }

    public void create(Person person) {
        // String query = "insert into person(username, password, email, type) value(?, ?, ?, ?)";
        // jdbcTemplate.update(query, new Object[] { person.getUsername(), person.getPassword(), person.getEmail(), person.getType()});

        // Map<String, Object> parameters = new HashMap<String, Object>();
        // parameters.put("username", person.getUsername());
        // parameters.put("password", person.getPassword());
        // parameters.put("email", person.getEmail());
        // parameters.put("type", person.getType());

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", person.getUsername())
                .addValue("password", person.getPassword())
                .addValue("email", person.getEmail())
                .addValue("type", person.getType());

        // SqlParameterSource parameters = new BeanPropertySqlParameterSource(person);

        simpleJdbcInsert.execute(parameters);
        // Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        // System.out.println("Generated id - " + id.longValue());
    }

    public Person getPersonById(int id) {
        String query = "select * from person where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[] { id }, new PersonMapper());
    }

    public String getPersonPasswordById(int id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
        String query = "select password from person where id = :id";
        return namedParameterJdbcTemplate.queryForObject(query, parameters, String.class);
    }

    public List<Person> getPersons() {
        String query = "select * from person";
        return jdbcTemplate.query(query, new PersonMapper());
    }

    public List<Map<String, Object>> getPersonsDetail() {
        return jdbcTemplate.queryForList("select * from person");
    }

    public void StoredProcedure() {
        System.out.println(test.execute());
    }
}
