package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Request.Message;
import example.Response.Result;
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
public class MessageRepository  {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result create(Message message) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("message").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("sender", message.getSender())
                    .addValue("receiver", message.getReceiver())
                    .addValue("content", message.getContent())
                    .addValue("time", message.getTime())
                    .addValue("unread", 1);
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

    public Result hasUnReadMessagesByID(int MemberID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("receiver", MemberID);
            String query = "SELECT EXISTS(SELECT unread FROM message WHERE receiver = :receiver AND unread = 1);";
            int count = namedParameterJdbcTemplate.queryForObject(query, parameters, Integer.class);
            result.setReturnCode(0);
            result.setReturnMessage("Success");
            JSONObject obj = new JSONObject();
            if(count == 1) {
                obj.put("hasUnReadMessages", true);
                result.setReturnData(obj);
            } else {
                obj.put("hasUnReadMessages", false);
                result.setReturnData(obj);
            }
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
        }
        return result;
    }

    public Result getMessagesByID(int MemberID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("receiver", MemberID);
            String query = "SELECT sender, receiver, content, time, unread FROM message WHERE receiver = :receiver;";
            List<example.Response.Message> message = namedParameterJdbcTemplate.query(query, parameters, BeanPropertyRowMapper.newInstance(example.Response.Message.class));
            JSONObject obj = new JSONObject();
            obj.put("message", message);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            result.setReturnData(obj);
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
        }
        return result;
    }

}
