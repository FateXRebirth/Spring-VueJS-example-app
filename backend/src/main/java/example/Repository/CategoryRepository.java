package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Entity.Category;
import example.Response.Categories;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public void create(Category category) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("category").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("brandid", category.getBrandID())
                .addValue("name", category.getName())
                .addValue("display_name", category.getDisplayName())
                .addValue("seriesid", category.getSeriesID())
                .addValue("series_name", category.getSeriesName());
        create.execute(parameters);
    }

    public List<Categories> getCategories() {
        String query = "SELECT c.id AS ID, c.brandid AS BrandID, c.seriesid AS SeriesID, c.name AS Name, c.display_name AS DisplayName, c.series_name AS SeriesName FROM category AS c";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Categories.class));
    }
}
