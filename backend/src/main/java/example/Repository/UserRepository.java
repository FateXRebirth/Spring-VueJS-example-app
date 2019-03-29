package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Entity.User;
import example.Request.Login;
import example.Request.Register;
import example.Response.Result;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result getUsers() {
        Result result = new Result();
        try {
            String query = "SELECT * FROM user";
            List<User> users = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class));
            JSONObject obj = new JSONObject();
            obj.put("users", users);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            result.setReturnData(obj);
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage("SQL Access Exception");
        }
        return result;
    }

    public Result getUserByID(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("id", id);
            String query = "SELECT * FROM user WHERE id = :id";
            User user = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
            JSONObject obj = new JSONObject();
            obj.put("user", user);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage("SQL Access Exception");
            return result;
        }
    }

    public Result editUserByID(int id, User user) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("account", user.getAccount())
                    .addValue("password", user.getPassword())
                    .addValue("email", user.getEmail())
                    .addValue("type", user.getType())
                    .addValue("name", user.getName())
                    .addValue("phone", user.getPhone())
                    .addValue("address", user.getAddress());

            String query = "UPDATE user SET account = :account, password = :password, email = :email, type = :type, name = :name, phone = :phone, address = :address WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage("SQL Access Exception");
            return result;
        }
    }

    public Result login(Login login) {
        Result result = new Result();
        if(isEmailExist(login.getEmail())) {
            // Account exist
            try {
                SqlParameterSource parameters = new MapSqlParameterSource("email", login.getEmail());
                String query = "SELECT * FROM user WHERE email = :email;";
                User user = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
                if (user.getPassword().equals(login.getPassword())) {
                    // Correct
                    result.setReturnCode(0);
                    result.setReturnMessage("OK");
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
            } catch (DataAccessException e) {
                result.setReturnCode(999);
                result.setReturnMessage("SQL Access Exception");
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
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("user").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("account", register.getUsername())
                    .addValue("password", register.getPassword())
                    .addValue("email", register.getEmail())
                    .addValue("type", 0);
            create.executeAndReturnKey(parameters);
            result.setReturnCode(0);
            result.setReturnMessage("You can log in now");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage("SQL Access Exception");
            return result;
        }
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
