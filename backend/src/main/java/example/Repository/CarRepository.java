package example.Repository;

import example.Config.SpringJdbcConfig;
import example.Request.Car;
import example.Request.CarSearch;
import example.Response.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarRepository {

    @Autowired
    FileRepository fileRepository;

    JdbcTemplate jdbcTemplate = new JdbcTemplate(SpringJdbcConfig.mysqlDataSource());
    NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(SpringJdbcConfig.mysqlDataSource());

    public Result getCarList() {
        Result result = new Result();
        String query =
                "SELECT \n" +
                        "Car.id AS CarID,\n" +
                        "Brand.name AS BrandName,\n" +
                        "Series.name AS SeriesName,\n" +
                        "Category.name AS CategoryName,\n" +
                        "Year.label AS Year,\n" +
                        "Car.mileage AS Mileage,\n" +
                        "Car.price AS Price,\n" +
                        "City.label AS City,\n" +
                        "Car.modifyDate AS ModifyDate,\n" +
                        "(SELECT File.url FROM file AS File WHERE File.carid = Car.id LIMIT 1) AS Image\n" +
                        "FROM car AS Car\n" +
                        "LEFT JOIN brand AS Brand\n" +
                        "ON Car.brand = Brand.id\n" +
                        "LEFT JOIN series AS Series\n" +
                        "ON Car.series = Series.id\n" +
                        "LEFT JOIN category AS Category\n" +
                        "ON Car.category = Category.id\n" +
                        "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Year') as Year\n" +
                        "ON Car.year = Year.value\n" +
                        "LEFT JOIN (SELECT label, value FROM region WHERE country = 0) AS City\n" +
                        "ON Car.city = City.value;";
        List<CarList> cars = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(CarList.class));
        JSONObject obj = new JSONObject();
        obj.put("cars", cars);
        result.setReturnCode(0);
        result.setReturnMessage("Fetched Successfully");
        result.setReturnData(obj);
        return result;
    }

    public Result getCars(CarSearch carSearch) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("brand", carSearch.getBrand())
                    .addValue("series", carSearch.getSeries())
                    .addValue("category", carSearch.getCategory())
                    .addValue("priceMin", carSearch.getPriceMin())
                    .addValue("priceMax", carSearch.getPriceMax())
                    .addValue("yearMin", carSearch.getYearMin())
                    .addValue("yearMax", carSearch.getYearMax())
                    .addValue("mileageMin", carSearch.getMileageMin())
                    .addValue("mileageMax", carSearch.getMileageMax())
                    .addValue("city", carSearch.getCity())
                    .addValue("transmission", carSearch.getTransmission())
                    .addValue("gearType", carSearch.getGearType())
                    .addValue("gasType", carSearch.getGasType())
                    .addValue("engineDisplacement", carSearch.getEngineDisplacement())
                    .addValue("passenger", carSearch.getPassenger())
                    .addValue("color", carSearch.getColor())
                    .addValue("keyword", "%" + carSearch.getKeyword() + "%");

            String query =
                    "SELECT \n" +
                    "Car.id AS CarID,\n" +
                    "Brand.name AS BrandName,\n" +
                    "Series.name AS SeriesName,\n" +
                    "Category.name AS CategoryName,\n" +
                    "Year.label AS Year,\n" +
                    "Car.mileage AS Mileage,\n" +
                    "Car.price AS Price,\n" +
                    "City.label AS City,\n" +
                    "Car.modifyDate AS ModifyDate,\n" +
                    "(SELECT File.url FROM file AS File WHERE File.carid = Car.id LIMIT 1) AS Image\n" +
                    "FROM car AS Car\n" +
                    "LEFT JOIN brand AS Brand\n" +
                    "ON Car.brand = Brand.id\n" +
                    "LEFT JOIN series AS Series\n" +
                    "ON Car.series = Series.id\n" +
                    "LEFT JOIN category AS Category\n" +
                    "ON Car.category = Category.id\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Year') as Year\n" +
                    "ON Car.year = Year.value\n" +
                    "LEFT JOIN (SELECT label, value FROM region WHERE country = 0) AS City\n" +
                    "ON Car.city = City.value\n" +
                    "WHERE\n" +
                    "Car.status = 1 AND\n" +
                    "(:keyword IS NULL OR  :keyword = '' OR Brand.name LIKE :keyword OR Series.name LIKE :keyword OR Category.name LIKE :keyword ) AND\n" +
                    "(:brand = 0 OR Brand.id = :brand) AND\n" +
                    "(:series = 0 OR Series.id = :series) AND\n" +
                    "(:category = 0 OR Category.id = :category) AND\n" +
                    "(:city = 0 OR Car.city = :city) AND\n" +
                    "(:transmission = 0 OR Car.transmission = :transmission) AND\n" +
                    "(:gearType = 0 OR Car.gearType = :gearType) AND\n" +
                    "(:gasType = 0 OR Car.gasType = :gasType) AND\n" +
                    "(:engineDisplacement = 0 OR Car.engineDisplacement = :engineDisplacement) AND\n" +
                    "(:passenger = 0 OR Car.passenger = :passenger) AND\n" +
                    "(:color = 0 OR Car.color = :color) AND\n" +
                    "(:priceMin = 0 OR Car.price >= :priceMin) AND\n" +
                    "(:priceMax = 0 OR Car.price <= :priceMax) AND\n" +
                    "(:yearMin = 0 OR Car.year >= :yearMin) AND\n" +
                    "(:yearMax = 0 OR Car.year <= :yearMax) AND\n" +
                    "(:mileageMin = 0 OR Car.mileage >= :mileageMin) AND\n" +
                    "(:mileageMax = 0 OR Car.mileage <= :mileageMax);";
            List<CarList> cars = namedParameterJdbcTemplate.query(query, parameters, BeanPropertyRowMapper.newInstance(CarList.class));
            JSONObject obj = new JSONObject();
            obj.put("cars", cars);
            result.setReturnCode(0);
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getCarDetailByID(int CarID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource("CarID", CarID);
            String query =
                    "SELECT \n" +
                    "Car.id AS CarID,\n" +
                    "Car.owner AS OwnerID,\n" +
                    "Brand.name AS BrandName,\n" +
                    "Series.name AS SeriesName,\n" +
                    "Category.name AS CategoryName,\n" +
                    "Year.label AS Year,\n" +
                    "Month.label AS Month,\n" +
                    "Transmission.label AS Transmission,\n" +
                    "GearType.label AS GearType,\n" +
                    "GasType.label AS GasType,\n" +
                    "EngineDisplacement.label AS EngineDisplacement,\n" +
                    "Passenger.label AS Passenger,\n" +
                    "Color.label AS Color,\n" +
                    "Car.mileage AS Mileage,\n" +
                    "Car.price AS Price,\n" +
                    "Car.equipment AS Equipment,\n" +
                    "Car.safety AS Safety,\n" +
                    "Car.name AS Name,\n" +
                    "Car.phone AS Phone,\n" +
                    "City.label AS City,\n" +
                    "Area.label AS Area,\n" +
                    "Car.address AS Address\n" +
                    "FROM car AS Car\n" +
                    "LEFT JOIN brand AS Brand\n" +
                    "ON Car.brand = Brand.id\n" +
                    "LEFT JOIN series AS Series\n" +
                    "ON Car.series = Series.id\n" +
                    "LEFT JOIN category AS Category\n" +
                    "ON Car.category = Category.id\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Year') as Year\n" +
                    "ON Car.year = Year.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Month') as Month\n" +
                    "ON Car.month = Month.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Transmission') as Transmission\n" +
                    "ON Car.transmission = Transmission.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'GearType') as GearType\n" +
                    "ON Car.geartype = GearType.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'GasType') as GasType\n" +
                    "ON Car.gastype = GasType.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'EngineDisplacement') as EngineDisplacement\n" +
                    "ON Car.enginedisplacement = EngineDisplacement.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Passenger') as Passenger\n" +
                    "ON Car.passenger = Passenger.value\n" +
                    "LEFT JOIN (SELECT label, value FROM specification WHERE category = 'Color') as Color\n" +
                    "ON Car.color = Color.value\n" +
                    "LEFT JOIN (SELECT label, value FROM region WHERE country = 0) AS City\n" +
                    "ON Car.city = City.value\n" +
                    "LEFT JOIN (SELECT label, value FROM region WHERE country != 0) AS Area\n" +
                    "ON Car.area = Area.value\n" +
                    "WHERE Car.id = :CarID";
            CarDetail car = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(CarDetail.class));
            List<File> photos = (List<File>)fileRepository.getPhotos(CarID).getReturnData().get("photos");
            car.setPhotos(photos);
            JSONObject obj = new JSONObject();
            obj.put("car", car);
            result.setReturnCode(0);
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getCarByID(int CarID) {
        Result result = new Result();
        try {
            /*
            SimpleJdbcCall GetCarByID = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource())
                    .withProcedureName("GetCarByID")
                    .returningResultSet("cars", new CarsMapper());
            Map<String, Object> out = GetCarByID.execute(parameters);
            return ((List<Car>) out.get("cars")).get(0);
            */
            SqlParameterSource parameters = new MapSqlParameterSource("CarID", CarID);
            String query =
                    "SELECT \n" +
                    "Car.id AS CarID,\n" +
                    "Car.owner AS OwnerID,\n" +
                    "Car.brand AS Brand,\n" +
                    "Car.series AS Series,\n" +
                    "Car.category AS Category,\n" +
                    "Car.year AS Year,\n" +
                    "Car.month AS Month,\n" +
                    "Car.transmission AS Transmission,\n" +
                    "Car.gearType AS GearType,\n" +
                    "Car.gasType AS GasType,\n" +
                    "Car.engineDisplacement AS EngineDisplacement,\n" +
                    "Car.passenger AS Passenger,\n" +
                    "Car.color AS Color,\n" +
                    "Car.mileage AS Mileage,\n" +
                    "Car.price AS Price,\n" +
                    "Car.equipment AS Equipment,\n" +
                    "Car.safety AS Safety,\n" +
                    "Car.name AS Name,\n" +
                    "Car.phone AS Phone,\n" +
                    "Car.city AS City,\n" +
                    "Car.area AS Area,\n" +
                    "Car.address AS Address,\n" +
                    "Car.modifyDate AS ModifyDate\n" +
                    "FROM car AS Car\n" +
                    "LEFT JOIN brand AS Brand\n" +
                    "ON Car.brand = Brand.id\n" +
                    "LEFT JOIN series AS Series\n" +
                    "ON Car.series = Series.id\n" +
                    "LEFT JOIN category AS Category\n" +
                    "ON Car.category = Category.id\n" +
                    "WHERE Car.id = :CarID";
            example.Response.Car car = namedParameterJdbcTemplate.queryForObject(query, parameters, BeanPropertyRowMapper.newInstance(example.Response.Car.class));
            List<File> photos = (List<File>)fileRepository.getPhotos(CarID).getReturnData().get("photos");
            car.setPhotos(photos);
            JSONObject obj = new JSONObject();
            obj.put("car", car);
            result.setReturnCode(0);
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result editCarByID(int CarID, Car car) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("CarID", CarID)
                    .addValue("transmission", car.getTransmission())
                    .addValue("gearType", car.getGeartype())
                    .addValue("gasType", car.getGastype())
                    .addValue("engineDisplacement", car.getEnginedisplacement())
                    .addValue("passenger", car.getPassenger())
                    .addValue("color", car.getColor())
                    .addValue("mileage", car.getMileage())
                    .addValue("price", car.getPrice())
                    .addValue("equipment", car.getEquipment())
                    .addValue("safety", car.getSafety())
                    .addValue("name", car.getName())
                    .addValue("phone", car.getPhone())
                    .addValue("city", car.getCity())
                    .addValue("area", car.getArea())
                    .addValue("address", car.getAddress())
                    .addValue("modifyDate", car.getModifyDate());

            String query =
                    "UPDATE car\n" +
                    "SET transmission = :transmission,\n" +
                    "geartype = :gearType,\n" +
                    "gastype = :gasType,\n" +
                    "enginedisplacement = :engineDisplacement,\n" +
                    "passenger = :passenger,\n" +
                    "color = :color,\n" +
                    "mileage = :mileage,\n" +
                    "price = :price,\n" +
                    "equipment = :equipment,\n" +
                    "safety = :safety,\n" +
                    "name = :name,\n" +
                    "phone = :phone,\n" +
                    "city = :city,\n" +
                    "area = :area,\n" +
                    "address = :address,\n" +
                    "modifyDate = :modifyDate\n" +
                    "WHERE id = :CarID;";
            namedParameterJdbcTemplate.update(query, parameters);
            fileRepository.updatePhotos(CarID, car.getPhotos());
            result.setReturnCode(0);
            result.setReturnMessage("Updated Successfully");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result deleteCarByID(int CarID) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("CarID", CarID);

            String query = "DELETE FROM car WHERE id =:CarID;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("Deleted Successfully");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result getCarByOwner(int MemberID) {
        Result result = new Result();
        try {
            /*
            SimpleJdbcCall GetCars = new SimpleJdbcCall(SpringJdbcConfig.mysqlDataSource())
                    .withProcedureName("GetCars")
                    .returningResultSet("cars", BeanPropertyRowMapper.newInstance(Car.class));
            Map out = GetCars.execute(new HashMap<String, Object>(0));
            return (List) out.get("cars");
            */
            SqlParameterSource parameters = new MapSqlParameterSource("MemberID", MemberID);
            String query =
                    "SELECT\n" +
                    "Car.id AS CarID,\n" +
                    "Brand.name AS BrandName,\n" +
                    "Series.display_name AS SeriesName,\n" +
                    "Category.display_name AS CategoryName,\n" +
                    "Car.year AS Year,\n" +
                    "Car.price AS Price,\n" +
                    "Car.modifyDate AS ModifyDate,\n" +
                    "Car.status AS Status,\n" +
                    "(SELECT File.url FROM file AS File WHERE File.carid = Car.id LIMIT 1) AS Image\n" +
                    "FROM car AS Car\n" +
                    "LEFT JOIN brand AS Brand\n" +
                    "ON Car.brand = Brand.id\n" +
                    "LEFT JOIN series AS Series\n" +
                    "ON Car.series = Series.id\n" +
                    "LEFT JOIN category AS Category\n" +
                    "ON Car.category = Category.id\n" +
                    "WHERE Car.owner = :MemberID;";
            List<Cars> cars = namedParameterJdbcTemplate.query(query, parameters, BeanPropertyRowMapper.newInstance(Cars.class));
            JSONObject obj = new JSONObject();
            obj.put("cars", cars);
            result.setReturnCode(0);
            result.setReturnMessage("Fetched Successfully");
            result.setReturnData(obj);
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result create(int MemberID, Car car) {
        Result result = new Result();
        try {
            SimpleJdbcInsert create = new SimpleJdbcInsert(SpringJdbcConfig.mysqlDataSource()).withTableName("car").usingGeneratedKeyColumns("id");
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("owner", MemberID)
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
                    .addValue("city", car.getCity())
                    .addValue("area", car.getArea())
                    .addValue("address", car.getAddress())
                    .addValue("modifyDate", car.getModifyDate())
                    .addValue("status", 1);
            int CarID = create.executeAndReturnKey(parameters).intValue();
            fileRepository.create(CarID, car.getPhotos());
            result.setReturnCode(0);
            result.setReturnMessage("Created Successfully");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

    public Result updateStatusByID(int CarID, int Status) {
        Result result = new Result();
        try {
            SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("CarID", CarID)
                    .addValue("Status", Status);

            String query = "UPDATE car SET status = :Status WHERE id = :CarID;";
            namedParameterJdbcTemplate.update(query, parameters);
            result.setReturnCode(0);
            result.setReturnMessage("Updated Successfully");
            return result;
        } catch (DataAccessException e) {
            result.setReturnCode(999);
            result.setReturnMessage(e.toString());
            return result;
        }
    }

}