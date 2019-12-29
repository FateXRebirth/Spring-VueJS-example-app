package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Response.Result;
import example.Response.Token;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepository  {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result create(int MemberID, String Token) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("token").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user", MemberID)
                    .addValue("token", Token);
            create.execute(parameters);
            result.setReturnCode(0);
            result.setReturnMessage("Success");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getTokenByID(int MemberID) {
        Result result = new Result();
        try {
            if(isTokenExist(MemberID)) {
                SqlParameterSource parameters = new MapSqlParameterSource()
                        .addValue("user", MemberID);
                String query = "SELECT user, token FROM token WHERE user = :user;";
                Token token = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(Token.class));
                JSONObject obj = new JSONObject();
                obj.put("tokens", token);
                result.setReturnData(obj);
                result.setReturnCode(0);
                result.setReturnMessage("OK");
                return result;
            } else {
                result.setReturnData(null);
                result.setReturnCode(0);
                result.setReturnMessage("OK");
                return result;
            }
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result updateTokenByID(int MemberID, String Token) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user", MemberID)
                    .addValue("token", Token);
            String query = "UPDATE token SET token = :token WHERE user = :user;";
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

    private boolean isTokenExist(int MemberID) {
        SqlParameterSource parameters = new MapSqlParameterSource("user", MemberID);
        String query = "SELECT EXISTS(SELECT token FROM token WHERE user = :user);";
        int count = namedParameterJdbcTemplate.queryForObject(query, parameters, Integer.class);
        if(count == 1) {
            // Exist
            return true;
        } else {
            // Doesn't exist
            return false;
        }
    }
}
