package example.Repository;

import example.Config.SpringJdbcConfig;

import example.Entity.Brand;
import example.Response.Brands;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandRepository  {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public void create(Brand brand) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("brand").usingGeneratedKeyColumns("brandid");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("brandid", brand.getName())
                .addValue("seriesid", brand.getDisplayName());
        create.execute(parameters);
    }

    public List<Brands> getBrands() {
        String query = "SELECT b.brandid AS ID, b.name AS Name, b.display_name AS DisplayName FROM brand AS b";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Brands.class));
    }

}
