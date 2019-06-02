package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Response.*;
import org.json.simple.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CommonRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result getNews() {
        Result result = new Result();
        try {
            String query = "SELECT type, date, title, content FROM news";
            List<News> news = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(News.class));
            JSONObject obj = new JSONObject();
            obj.put("news", news);
            result.setReturnData(obj);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getBanner() {
        Result result = new Result();
        try {
            String query = "SELECT type, name, url, link FROM banner";
            List<Banners> banners = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Banners.class));
            JSONObject obj = new JSONObject();
            obj.put("banner", banners);
            result.setReturnData(obj);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getSpecification() {
        Result result = new Result();
        try {
            String query = "SELECT category, label, value FROM specification";
            List<Specifications> specifications = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Specifications.class));
            JSONObject obj = new JSONObject();
            obj.put("specification", specifications);
            result.setReturnData(obj);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

}
