package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Response.File;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class FileRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result create(int CarID, ArrayList<JSONObject> photos) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("file").usingGeneratedKeyColumns("id");
            for(int i = 0; i < photos.size(); i++) {
                SqlParameterSource parameters = new MapSqlParameterSource()
                        .addValue("carID", CarID)
                        .addValue("label", photos.get(i).get("label").toString())
                        .addValue("url", photos.get(i).get("url").toString());
                create.execute(parameters);
            }
            result.setReturnCode(0);
            result.setReturnMessage("Success");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getPhotos(int CarID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("CarID", CarID);
            String query = "SELECT label, url FROM file where carid = :CarID";
            List<File> photos = namedParameterJdbcTemplate.query(query, parameters, BeanPropertyRowMapper.newInstance(File.class));
            JSONObject obj = new JSONObject();
            obj.put("photos", photos);
            result.setReturnData(obj);
            result.setReturnCode(0);
            result.setReturnMessage("Success");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result updatePhotos(int CarID, ArrayList<JSONObject> photos) {
        Result result = new Result();
        try {
            SqlParameterSource parameter = new MapSqlParameterSource()
                    .addValue("CarID", CarID);
            String query = "DELETE FROM file WHERE carid = :CarID;";
            namedParameterJdbcTemplate.update(query, parameter);
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("file").usingGeneratedKeyColumns("id");
            for(int i = 0; i < photos.size(); i++) {
                SqlParameterSource parameters = new MapSqlParameterSource()
                        .addValue("carID", CarID)
                        .addValue("label", photos.get(i).get("label").toString())
                        .addValue("url", photos.get(i).get("url").toString());
                create.execute(parameters);
            }
            result.setReturnCode(0);
            result.setReturnMessage("Success");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

}
