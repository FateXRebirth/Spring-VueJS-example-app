package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private Integer brand;
    private Integer series;
    private Integer category;
    private Integer year;
    private Integer month;
    private Integer transmission;
    private Integer geartype;
    private Integer gastype;
    private Integer enginedisplacement;
    private Integer passenger;
    private Integer color;
    private Integer mileage;
    private Integer price;
    private Integer equipment;
    private Integer safety;
    private String name;
    private String phone;
    private Integer city;
    private Integer area;
    private String address;
    private Integer status;
    private Integer owner;
    private String modifydate;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBrand() {
        return brand;
    }

    public void setBrand(Integer brand) {
        this.brand = brand;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTransmission() {
        return transmission;
    }

    public void setTransmission(Integer transmission) {
        this.transmission = transmission;
    }

    public Integer getGeartype() {
        return geartype;
    }

    public void setGeartype(Integer geartype) {
        this.geartype = geartype;
    }

    public Integer getGastype() {
        return gastype;
    }

    public void setGastype(Integer gastype) {
        this.gastype = gastype;
    }

    public Integer getEnginedisplacement() {
        return enginedisplacement;
    }

    public void setEnginedisplacement(Integer enginedisplacement) {
        this.enginedisplacement = enginedisplacement;
    }

    public Integer getPassenger() {
        return passenger;
    }

    public void setPassenger(Integer passenger) {
        this.passenger = passenger;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    public Integer getSafety() {
        return safety;
    }

    public void setSafety(Integer safety) {
        this.safety = safety;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getModifydate() {
        return modifydate;
    }

    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }

    public Car() {
    }

    public Car(Integer brand, Integer series, Integer category, Integer year, Integer month, Integer transmission, Integer geartype, Integer gastype, Integer enginedisplacement, Integer passenger, Integer color, Integer mileage, Integer price, Integer equipment, Integer safety, String name, String phone, Integer city, Integer area, String address, Integer status, Integer owner, String modifydate) {
        this.brand = brand;
        this.series = series;
        this.category = category;
        this.year = year;
        this.month = month;
        this.transmission = transmission;
        this.geartype = geartype;
        this.gastype = gastype;
        this.enginedisplacement = enginedisplacement;
        this.passenger = passenger;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
        this.equipment = equipment;
        this.safety = safety;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.area = area;
        this.address = address;
        this.status = status;
        this.owner = owner;
        this.modifydate = modifydate;
    }
}
