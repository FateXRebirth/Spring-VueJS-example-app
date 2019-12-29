package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Response.Result;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class LogRepository  {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result login(int MemberID, String time) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("log").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user", MemberID)
                    .addValue("start", time)
                    .addValue("online", 1);
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

    public Result logout(int MemberID, String time) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("user", MemberID)
                    .addValue("end", time);
            String query = "UPDATE log SET end = :end, online = 0 WHERE user = :user AND online = 1 AND start IS NOT NULL AND end IS NULL;";
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

    public Result isOnline(int MemberID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("user", MemberID);
            String query = "SELECT EXISTS(SELECT online FROM log WHERE user = :user AND online = 1);";
            int count = namedParameterJdbcTemplate.queryForObject(query, parameters, Integer.class);
            result.setReturnCode(0);
            result.setReturnMessage("Success");
            JSONObject obj = new JSONObject();
            if(count == 1) {
                obj.put("isOnline", true);
                result.setReturnData(obj);
            } else {
                obj.put("isOnline", false);
                result.setReturnData(obj);
            }
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
        }
        return result;
    }
}
