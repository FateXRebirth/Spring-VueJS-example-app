package example.Response;

public class CarDetail {

    private Integer ID;
    private Integer BrandID;
    private Integer SeriesID;
    private Integer CategoryID;
    private String BrandName;
    private String SeriesName;
    private String CategoryName;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBrandID() {
        return BrandID;
    }

    public void setBrandID(Integer brandID) {
        BrandID = brandID;
    }

    public Integer getSeriesID() {
        return SeriesID;
    }

    public void setSeriesID(Integer seriesID) {
        SeriesID = seriesID;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID = categoryID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public void setSeriesName(String seriesName) {
        SeriesName = seriesName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public CarDetail(Integer ID, Integer brandID, Integer seriesID, Integer categoryID, String brandName, String seriesName, String categoryName) {
        this.ID = ID;
        BrandID = brandID;
        SeriesID = seriesID;
        CategoryID = categoryID;
        BrandName = brandName;
        SeriesName = seriesName;
        CategoryName = categoryName;
    }
}
