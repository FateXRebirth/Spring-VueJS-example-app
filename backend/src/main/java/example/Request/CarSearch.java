package example.Request;

public class CarSearch {

    private Integer brand;
    private Integer series;
    private Integer category;
    private Integer priceMin;
    private Integer priceMax;
    private Integer yearMin;
    private Integer yearMax;
    private Integer mileageMin;
    private Integer mileageMax;
    private Integer city;
    private Integer transmission;
    private Integer gearType;
    private Integer gasType;
    private Integer engineDisplacement;
    private Integer passenger;
    private Integer color;
    private String keyword;

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

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public Integer getYearMin() {
        return yearMin;
    }

    public void setYearMin(Integer yearMin) {
        this.yearMin = yearMin;
    }

    public Integer getYearMax() {
        return yearMax;
    }

    public void setYearMax(Integer yearMax) {
        this.yearMax = yearMax;
    }

    public Integer getMileageMin() {
        return mileageMin;
    }

    public void setMileageMin(Integer mileageMin) {
        this.mileageMin = mileageMin;
    }

    public Integer getMileageMax() {
        return mileageMax;
    }

    public void setMileageMax(Integer mileageMax) {
        this.mileageMax = mileageMax;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getTransmission() {
        return transmission;
    }

    public void setTransmission(Integer transmission) {
        this.transmission = transmission;
    }

    public Integer getGearType() {
        return gearType;
    }

    public void setGearType(Integer gearType) {
        this.gearType = gearType;
    }

    public Integer getGasType() {
        return gasType;
    }

    public void setGasType(Integer gasType) {
        this.gasType = gasType;
    }

    public Integer getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(Integer engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "CarSearch{" +
                "brand=" + brand +
                ", series=" + series +
                ", category=" + category +
                ", priceMin=" + priceMin +
                ", priceMax=" + priceMax +
                ", yearMin=" + yearMin +
                ", yearMax=" + yearMax +
                ", mileageMin=" + mileageMin +
                ", mileageMax=" + mileageMax +
                ", city=" + city +
                ", transmission=" + transmission +
                ", gearType=" + gearType +
                ", gasType=" + gasType +
                ", engineDisplacement=" + engineDisplacement +
                ", passenger=" + passenger +
                ", color=" + color +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
