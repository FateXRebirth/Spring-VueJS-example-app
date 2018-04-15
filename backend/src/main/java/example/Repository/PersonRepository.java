package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Entity.Person;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonRepository {

    public class CustomSQLErrorCodeTranslator extends SQLErrorCodeSQLExceptionTranslator {
        @Override
        protected DataAccessException customTranslate
                (String task, String sql, SQLException sqlException) {
            if (sqlException.getErrorCode() == -104) {
                return new DuplicateKeyException(
                        "Custom Exception translator - Integrity constraint violation.", sqlException);
            }
            return null;
        }
    }

    private static final class PersonMapper implements RowMapper<Person> {

        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person(rs.getString("username"), rs.getString("password"),
                    rs.getString("email"), rs.getString("type"));
            return person;
        }
    }

    private JdbcTemplate jdbcTemplate;
    private CustomSQLErrorCodeTranslator customSQLErrorCodeTranslator;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;
    private SimpleJdbcCall simpleJdbcCall;

    public PersonRepository() {
        jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
        customSQLErrorCodeTranslator = new CustomSQLErrorCodeTranslator();
        jdbcTemplate.setExceptionTranslator(customSQLErrorCodeTranslator);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());
        simpleJdbcInsert = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("person").usingGeneratedKeyColumns("id");
        simpleJdbcCall = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource()).withProcedureName("test");
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

    public Person getPersonByUsername(String username) {
        SqlParameterSource parameters = new MapSqlParameterSource("username", username);
        String query = "select username, password, email, type from person where username = :username";
        try {
            return namedParameterJdbcTemplate.queryForObject(query, parameters, new PersonMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Person> getPersons() {
        String query = "select * from person";
        return jdbcTemplate.query(query, new PersonMapper());
    }

    public List<Map<String, Object>> getPersonsDetail() {
        return jdbcTemplate.queryForList("select * from person");
    }

    public void StoredProcedure() {
        System.out.println(simpleJdbcCall.execute());
    }
}
