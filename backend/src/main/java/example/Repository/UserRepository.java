package example.Repository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import example.Config.SpringJdbcConfig;
import example.Request.*;
import example.Response.CarList;
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
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
        }
        return result;
    }

    public Result getUserByID(int MemberID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("MemberID", MemberID);
            String query = "SELECT account, password, email, type, name, phone, address FROM user WHERE id = :MemberID";
            User user = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
            JSONObject obj = new JSONObject();
            obj.put("user", user);
            result.setReturnCode(0);
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result editUserByID(int MemberID, MemberEdit memberEdit) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("MemberID", MemberID)
                    .addValue("account", memberEdit.getAccount())
                    .addValue("password", memberEdit.getPassword())
                    .addValue("name", memberEdit.getName())
                    .addValue("phone", memberEdit.getPhone())
                    .addValue("address", memberEdit.getAddress());

            String query = "UPDATE user SET account = :account, password = :password, name = :name, phone = :phone, address = :address WHERE id = :MemberID;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("Updated Successfully");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result upgradeUserByID(int MemberID, MemberUpgrade memberUpgrade) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("MemberID", MemberID)
                    .addValue("type", memberUpgrade.getType())
                    .addValue("name", memberUpgrade.getName())
                    .addValue("phone", memberUpgrade.getPhone())
                    .addValue("address", memberUpgrade.getAddress());

            String query = "UPDATE user SET type = :type, name = :name, phone = :phone, address = :address WHERE id = :MemberID;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("Updated Successfully");
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
                String query = "SELECT id, account, password, type FROM user WHERE email = :email;";
                User user = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(User.class));
                if (user.getPassword().equals(memberLogin.getPassword())) {
                    // Correct
                    result.setReturnCode(0);
                    result.setReturnMessage("OK");
                    Algorithm algorithm = Algorithm.HMAC256("secret");
                    String token = JWT.create()
                            .withIssuer(user.getAccount())
                            .withJWTId(user.getId().toString())
                            .sign(algorithm);
                    parameters = new MapSqlParameterSource("MemberID", user.getId());
                    query = "SELECT carid FROM favorite WHERE memberid = :MemberID;";
                    List<Integer> favoriteCars = namedParameterJdbcTemplate.queryForList(query, parameters, Integer.class);
                    JSONObject obj = new JSONObject();
                    obj.put("id", user.getId());
                    obj.put("type", user.getType());
                    obj.put("account", user.getAccount());
                    obj.put("token", token);
                    obj.put("favoriteCars", favoriteCars);
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

    public Result getFavoriteCars(int MemberID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("MemberID", MemberID);

            String query =
                    "SELECT \n" +
                    "Car.id AS CarID,\n" +
                    "Brand.name AS BrandName,\n" +
                    "Series.name AS SeriesName,\n" +
                    "Category.name AS CategoryName,\n" +
                    "Car.mileage AS Mileage,\n" +
                    "Car.price AS Price,\n" +
                    "City.label AS City,\n" +
                    "Year.label AS Year,\n" +
                    "Car.modifyDate AS ModifyDate,\n" +
                    "(SELECT File.url FROM file AS File WHERE File.carid = Car.id LIMIT 1) AS Image\n" +
                    "FROM favorite AS Favorite \n" +
                    "LEFT JOIN car AS Car \n" +
                    "ON Favorite.carid = Car.id\n" +
                    "LEFT JOIN brand AS Brand\n" +
                    "ON Car.brand = Brand.id\n" +
                    "LEFT JOIN series AS Series\n" +
                    "ON Car.series = Series.id\n" +
                    "LEFT JOIN category AS Category\n" +
                    "ON Car.category = Category.id\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Year') as Year\n" +
                    "ON Car.year = Year.value\n" +
                    "LEFT JOIN (SELECT label, value FROM region WHERE country = 0) AS City\n" +
                    "ON Car.city = City.value\n" +
                    "WHERE Car.status = 1 AND memberid = :MemberID;";
            List<CarList> cars = namedParameterJdbcTemplate.query(query, parameters, BeanPropertyRowMapper.newInstance(CarList.class));
            JSONObject obj = new JSONObject();
            obj.put("cars", cars);
            result.setReturnCode(0);
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result addFavoriteCars(int MemberID, int CarID) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("favorite").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("memberid", MemberID)
                    .addValue("carid", CarID);
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

    public Result removeFavoriteCars(int MemberID, int CarID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("MemberID", MemberID)
                    .addValue("CarID", CarID);

            String query = "DELETE FROM favorite WHERE memberid = :MemberID AND carid = :CarID;";
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

    private boolean isMyCar(int MemberID, int CarID) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("MemberID", MemberID)
                .addValue("CarID", CarID);
        String query = "SELECT COUNT(carid) FROM favorite WHERE memberid = :MemberID AND carid = :CarID;";
        int count = namedParameterJdbcTemplate.queryForObject(query, parameters, Integer.class);
        if(count >= 1) {
            // Is Mine
            return true;
        } else {
            // Is Not Mine
            return false;
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
