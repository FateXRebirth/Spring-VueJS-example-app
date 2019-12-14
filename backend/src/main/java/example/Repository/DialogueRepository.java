package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Response.Dialogue;
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
public class DialogueRepository  {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result create(example.Request.Dialogue dialogue) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("dialogue").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("uuid", dialogue.getUuid())
                    .addValue("item", dialogue.getItem())
                    .addValue("title", dialogue.getTitle())
                    .addValue("sender", dialogue.getSender())
                    .addValue("receiver", dialogue.getReceiver());
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

    public Result getDialogueByID(int MemberID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("memberID", MemberID);
            String query = "SELECT uuid, item, title, sender, receiver FROM dialogue WHERE receiver = :memberID OR sender = :memberID;";
            List<Dialogue> dialogue = namedParameterJdbcTemplate.query(query, parameters, BeanPropertyRowMapper.newInstance(Dialogue.class));
            JSONObject obj = new JSONObject();
            obj.put("dialogue", dialogue);
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

