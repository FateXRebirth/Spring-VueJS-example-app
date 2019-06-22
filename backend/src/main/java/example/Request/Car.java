package example.Request;

import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Car {

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
    private String modifyDate;
    private ArrayList<JSONObject> photos;

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

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public ArrayList<JSONObject> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<JSONObject> photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + brand +
                ", series=" + series +
                ", category=" + category +
                ", year=" + year +
                ", month=" + month +
                ", transmission=" + transmission +
                ", geartype=" + geartype +
                ", gastype=" + gastype +
                ", enginedisplacement=" + enginedisplacement +
                ", passenger=" + passenger +
                ", color=" + color +
                ", mileage=" + mileage +
                ", price=" + price +
                ", equipment=" + equipment +
                ", safety=" + safety +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", city=" + city +
                ", area=" + area +
                ", address='" + address + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", photos=" + photos +
                '}';
    }
}
