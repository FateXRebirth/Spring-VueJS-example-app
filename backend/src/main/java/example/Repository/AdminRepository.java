package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Entity.Admin;
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
public class AdminRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result login(Login login) {
        Result result = new Result();
        if(isEmailExist(login.getEmail())) {
            // Account exist
            SqlParameterSource parameters = new MapSqlParameterSource("email", login.getEmail());
            String query = "SELECT * FROM manager WHERE email = :email;";
            Admin admin =  namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(Admin.class));
            if(admin.getPassword().equals(login.getPassword())) {
                // Correct
                result.setReturnCode(0);
                JSONObject obj = new JSONObject();
                obj.put("username", admin.getUsername());
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
        Admin admin = new Admin(register.getUsername(), register.getPassword(), register.getEmail());
        create(admin);
        result.setReturnCode(0);
        result.setReturnMessage("You can log in now");
        return result;
    }

    private void create(Admin admin) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("manager").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", admin.getUsername())
                .addValue("password", admin.getPassword())
                .addValue("email", admin.getEmail());
        create.executeAndReturnKey(parameters);
    }

    private boolean isEmailExist(String email) {
        SqlParameterSource parameters = new MapSqlParameterSource("email", email);
        String query = "SELECT COUNT(email) FROM manager WHERE email = :email;";
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
