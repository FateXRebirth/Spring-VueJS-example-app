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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public void create(Admin admin) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("admin").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", admin.getUsername())
                .addValue("password", admin.getPassword())
                .addValue("email", admin.getEmail());
        create.execute(parameters);
    }

    public Result login(Login login) {
        Result result = new Result();
        try {
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

        } catch (EmptyResultDataAccessException e) {
            // Account doesn't exist
            result.setReturnCode(1);
            result.setReturnMessage("Account doesn't exist");
            return result;
        }
    }

    public void register(Register register) {

    }



}
