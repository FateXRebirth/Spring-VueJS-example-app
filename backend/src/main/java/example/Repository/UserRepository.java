package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Entity.User;
import example.Request.Login;
import example.Request.Register;
import example.Response.Result;
import org.json.simple.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result login(Login login) {
        Result result = new Result();
        if(isEmailExist(login.getEmail())) {
            // Account exist
            SqlParameterSource parameters = new MapSqlParameterSource("email", login.getEmail());
            String query = "SELECT * FROM user WHERE email = :email;";
            User user =  namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
            if(user.getPassword().equals(login.getPassword())) {
                // Correct
                result.setReturnCode(0);
                JSONObject obj = new JSONObject();
                obj.put("account", user.getAccount());
                result.setReturnData(obj);
                return result;
            } else {
                // Password wrong
                result.setReturnCode(2);
                result.setReturnMessage("Password wrong");
                return result;
            }
        } else {
            // Account doesn't exist
            result.setReturnCode(1);
            result.setReturnMessage("Account doesn't exist");
            return result;
        }
    }

    public Result register(Register register) {
        Result result = new Result();
        if(isEmailExist(register.getEmail())) {
            // E-mail exist
            result.setReturnCode(1);
            result.setReturnMessage("E-mail has been registered");
            return result;
        }
        User user = new User(register.getUsername(), register.getPassword(), register.getEmail());
        create(user);
        result.setReturnCode(0);
        result.setReturnMessage("You can log in now");
        return result;
    }

    private void create(User user) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("user").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("account", user.getAccount())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        create.executeAndReturnKey(parameters);
    }

    private boolean isEmailExist(String email) {
        SqlParameterSource parameters = new MapSqlParameterSource("email", email);
        String query = "SELECT COUNT(email) FROM user WHERE email = :email;";
        int count = namedParameterJdbcTemplate.queryForObject(query, parameters, Integer.class);
        if(count >= 1) {
            // Exist
            return true;
        } else {
            // Doesn't exist
            return false;
        }
    }



}
