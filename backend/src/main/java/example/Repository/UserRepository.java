package example.Repository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import example.Config.SpringJdbcConfig;
import example.Request.*;
import example.Response.Result;
import example.Response.User;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
            String query = "SELECT id, account, password, email, type, name, phone, address FROM user";
            List<User> users = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(User.class));
            JSONObject obj = new JSONObject();
            obj.put("user", users);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            result.setReturnData(obj);
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
        }
        return result;
    }

    public Result getUserByID(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("id", id);
            String query = "SELECT account, password, email, type, name, phone, address FROM user WHERE id = :id";
            User user = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
            JSONObject obj = new JSONObject();
            obj.put("user", user);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result editUserByID(int id, MemberEdit memberEdit) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("account", memberEdit.getAccount())
                    .addValue("password", memberEdit.getPassword())
                    .addValue("name", memberEdit.getName())
                    .addValue("phone", memberEdit.getPhone())
                    .addValue("address", memberEdit.getAddress());

            String query = "UPDATE user SET account = :account, password = :password, name = :name, phone = :phone, address = :address WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result upgradeUserByID(int id, MemberUpgrade memberUpgrade) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("type", memberUpgrade.getType())
                    .addValue("name", memberUpgrade.getName())
                    .addValue("phone", memberUpgrade.getPhone())
                    .addValue("address", memberUpgrade.getAddress());

            String query = "UPDATE user SET type = :type, name = :name, phone = :phone, address = :address WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result login(MemberLogin memberLogin) {
        Result result = new Result();
        try {
            if(isEmailExist(memberLogin.getEmail())) {
                // Account exist
                SqlParameterSource parameters = new MapSqlParameterSource("email", memberLogin.getEmail());
                String query = "SELECT id, account, password FROM user WHERE email = :email;";
                User user = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
                if (user.getPassword().equals(memberLogin.getPassword())) {
                    // Correct
                    result.setReturnCode(0);
                    result.setReturnMessage("OK");
                    JSONObject obj = new JSONObject();
                    obj.put("account", user.getAccount());
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    String token = JWT.create()
                            .withIssuer(user.getAccount())
                            .sign(algorithm);
                    obj.put("token", token);
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
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result register(MemberRegister memberRegister) {
        Result result = new Result();
        if(isEmailExist(memberRegister.getEmail())) {
            // E-mail exist
            result.setReturnCode(1);
            result.setReturnMessage("E-mail has been registered");
            return result;
        }
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("user").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("account", memberRegister.getUsername())
                    .addValue("password", memberRegister.getPassword())
                    .addValue("email", memberRegister.getEmail())
                    .addValue("type", 0);
            create.executeAndReturnKey(parameters);
            result.setReturnCode(0);
            result.setReturnMessage("You can log in now");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getFavoriteCars(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id);

            String query = "SELECT F.carid, F.memberid FROM favorite AS F INNER JOIN Car AS C ON F.carid = C.id WHERE memberid = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result createFavoriteCars(FavoriteCar favoriteCar) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("favorite").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("memberid", favoriteCar.getMemberid())
                    .addValue("carid", favoriteCar.getCarid());
            create.executeAndReturnKey(parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result DeleteFavoriteCars(FavoriteCar favoriteCar) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", favoriteCar.getMemberid())
                    .addValue("carid", favoriteCar.getCarid());

            String query = "DELETE FROM favorite WHERE memberid = :id AND carid = :carid;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
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
