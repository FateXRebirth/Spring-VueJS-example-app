package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Entity.Car;
import example.Response.Cars;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CarRepository {

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    private static final class CarMapper implements RowMapper<Car> {
        public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
            Car car = new Car(rs.getInt("brandid"),
                    rs.getInt("seriesid"), rs.getInt("categoryid"));
            return car;
        }
    }

    private static final class CarsMapper implements RowMapper<Cars> {
        public Cars mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cars cars = new Cars(
                    rs.getInt("CarID"),
                    rs.getInt("BrandID"),
                    rs.getInt("SeriesID"),
                    rs.getInt("CategoryID"),
                    rs.getString("BrandName"),
                    rs.getString("SeriesName"),
                    rs.getString("CategoryName"));
            return cars;
        }
    }

    public List<Cars> getCarsWithQuery() {
        String query = "SELECT \n" +
                "A.id AS CarID,\n" +
                "A.brandid AS BrandID,\n" +
                "A.seriesid AS SeriesID,\n" +
                "A.categoryid AS CategoryID,\n" +
                "B.`name` AS BrandName,\n" +
                "S.`name` AS SeriesName,\n" +
                "C.`name` AS CategoryName\n" +
                "FROM\n" +
                "db.car AS A\n" +
                "LEFT\n" +
                "JOIN\n" +
                "db.brand AS B\n" +
                "ON A.brandid = B.brandid\n" +
                "INNER\n" +
                "JOIN\n" +
                "db.series AS S\n" +
                "ON S.id = A.seriesid\n" +
                "INNER\n" +
                "JOIN\n" +
                "db.category AS C\n" +
                "ON A.categoryid = C.id;";
        return jdbcTemplate.query(query, new CarsMapper());
    }

    public void create(Car car) {
        SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("car").usingGeneratedKeyColumns("id");
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("brandid", car.getBrandid())
                .addValue("seriesid", car.getSeriesid())
                .addValue("categoryid", car.getCategoryid());
        create.execute(parameters);
    }

    public List<Cars> getCars() {
        SimpleJdbcCall GetCars = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource())
                .withProcedureName("GetCars")
                .returningResultSet("cars", BeanPropertyRowMapper.newInstance(Cars.class));
        Map out = GetCars.execute(new HashMap<String, Object>(0));
        return (List) out.get("cars");
    }

    public void editCarByID(int id, Car car) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("brandid", car.getBrandid())
                .addValue("seriesid", car.getSeriesid())
                .addValue("categoryid", car.getCategoryid());
        String query = "UPDATE car\n" +
                "SET brandid = :brandid', seriesid = :seriesid, categoryid = :categoryid'\n" +
                "WHERE id = :id;";
        namedParameterJdbcTemplate.update(query, parameters);
    }

    public Cars getCarByID(int id) {
        SqlParameterSource parameters = new MapSqlParameterSource("id", id);
//        SimpleJdbcCall GetCarByID = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource())
//                .withProcedureName("GetCarsByID")
//                .returningResultSet("cars", new CarsMapper());
//        Map<String, Object> out = GetCarByID.execute(parameters);
//        return ((List<Cars>) out.get("cars")).get(0);
        String query = "SELECT \n" +
                "\tA.id AS CarID,\n" +
                "\tA.brandid AS BrandID,\n" +
                "\tA.seriesid AS SeriesID,\n" +
                "\tA.categoryid AS CategoryID,\n" +
                "\tB.`name` AS BrandName,\n" +
                "\tS.`name` AS SeriesName,\n" +
                "\tC.`name` AS CategoryName\n" +
                "\tFROM\n" +
                "\tdb.car AS A\n" +
                "\tLEFT\n" +
                "\tJOIN\n" +
                "\tdb.brand AS B\n" +
                "\tON A.brandid = B.brandid\n" +
                "\tINNER\n" +
                "\tJOIN\n" +
                "\tdb.series AS S\n" +
                "\tON S.id = A.seriesid\n" +
                "\tINNER\n" +
                "\tJOIN\n" +
                "\tdb.category AS C\n" +
                "\tON A.categoryid = C.id\n" +
                "\tWHERE A.id = :id;";
        return namedParameterJdbcTemplate.queryForObject(query, parameters, new CarsMapper());
    }

}