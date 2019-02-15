package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Entity.Series;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeriesRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public void create(Series series) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("series").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("brandid", series.getBrandID())
                .addValue("name", series.getName())
                .addValue("display_name", series.getDisplayName());
        create.execute(parameters);
    }

    public List<example.Response.Series> getSeries() {
        String query = "SELECT s.id AS ID, s.brandid AS ParentID, s.name AS Name, s.display_name AS DisplayName FROM series AS s";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(example.Response.Series.class));
    }
}
