package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Entity.Category;
import example.Response.Categories;
import example.Response.News;
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
public class CommonRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result getNews() {
        Result result = new Result();
        try {
            String query = "SELECT * FROM news";
            List<News> news = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(News.class));
            JSONObject obj = new JSONObject();
            obj.put("news", news);
            result.setReturnData(obj);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage("SQL Access Exception");
            return result;
        }
    }
}
