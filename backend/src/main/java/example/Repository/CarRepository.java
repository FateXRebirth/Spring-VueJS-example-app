package example.Repository;
import example.Config.SpringJdbcConfig;
import example.Request.Car;
import example.Response.Cars;
import example.Response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository {

    @Autowired
    FileRepository fileRepository;

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result getCars() {
        Result result = new Result();
        return result;
        /*
        SimpleJdbcCall GetCars = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource())
                .withProcedureName("GetCars")
                .returningResultSet("cars", BeanPropertyRowMapper.newInstance(Cars.class));
        Map out = GetCars.execute(new HashMap<String, Object>(0));
        return (List) out.get("cars");

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
                "\tON A.categoryid = C.id;";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Cars.class));
        */
    }

    public Result getCarByID(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("id", id);
            /*
            SimpleJdbcCall GetCarByID = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource())
                    .withProcedureName("GetCarByID")
                    .returningResultSet("cars", new CarsMapper());
            Map<String, Object> out = GetCarByID.execute(parameters);
            return ((List<Cars>) out.get("cars")).get(0);
            */
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
            namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(Cars.class));
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result editCarByID(int id, Car car) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("transmission", car.getTransmission())
                    .addValue("geartype", car.getGeartype())
                    .addValue("gastype", car.getGastype())
                    .addValue("enginedisplacement", car.getEnginedisplacement())
                    .addValue("passenger", car.getPassenger())
                    .addValue("color", car.getColor())
                    .addValue("mileage", car.getMileage())
                    .addValue("price", car.getPrice())
                    .addValue("equipment", car.getEquipment())
                    .addValue("safety", car.getSafety())
                    .addValue("name", car.getName())
                    .addValue("phone", car.getPhone())
                    .addValue("address", car.getAddress());

            String query = "UPDATE car\n" +
                    "SET transmission = :transmission',\n" +
                    "geartype = :geartype',\n" +
                    "gastype = :gastype',\n" +
                    "enginedisplacement = :enginedisplacement',\n" +
                    "passenger = :passenger',\n" +
                    "color = :color',\n" +
                    "mileage = :mileage',\n" +
                    "price = :price',\n" +
                    "equipment = :equipment',\n" +
                    "safety = :safety',\n" +
                    "name = :name',\n" +
                    "phone = :phone',\n" +
                    "address = :address',\n" +
                    "WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result create(Car car) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("car").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("brand", car.getBrand())
                    .addValue("series", car.getSeries())
                    .addValue("category", car.getCategory())
                    .addValue("year", car.getYear())
                    .addValue("month", car.getMonth())
                    .addValue("transmission", car.getTransmission())
                    .addValue("geartype", car.getGeartype())
                    .addValue("gastype", car.getGastype())
                    .addValue("enginedisplacement", car.getEnginedisplacement())
                    .addValue("passenger", car.getPassenger())
                    .addValue("color", car.getColor())
                    .addValue("mileage", car.getMileage())
                    .addValue("price", car.getPrice())
                    .addValue("equipment", car.getEquipment())
                    .addValue("safety", car.getSafety())
                    .addValue("name", car.getName())
                    .addValue("phone", car.getPhone())
                    .addValue("address", car.getAddress())
                    .addValue("status", 1);
            int CarID = create.executeAndReturnKey(parameters).intValue();
            car.getPhotos().forEach(photos -> fileRepository.create(CarID, photos.get("label").toString(), photos.get("url").toString()));
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result addCar(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id);

            String query = "UPDATE car SET status = 1 WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result removeCar(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id);

            String query = "UPDATE car SET status = -1 WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("OK");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result deleteCar(int id) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("id", id);

            String query = "UPDATE car SET status = 0 WHERE id = :id;";
            namedParameterJdbcTemplate.update(query, parameters);
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