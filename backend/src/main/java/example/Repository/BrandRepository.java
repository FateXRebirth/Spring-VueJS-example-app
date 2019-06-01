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
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("brand").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", brand.getName())
                .addValue("display_name", brand.getDisplayName());
        create.execute(parameters);
    }

    public List<Brands> getBrands() {
        String query = "SELECT id, name, display_name FROM brand";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Brands.class));
    }

}
