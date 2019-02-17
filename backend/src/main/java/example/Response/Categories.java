package example.Response;

public class Categories {

    private Integer ID;
    private Integer BrandID;
    private Integer SeriesID;
    private String Name;
    private String DisplayName;
    private String SeriesName;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getSeriesName() {
        return SeriesName;
    }

    public void setSeriesName(String seriesName) {
        SeriesName = seriesName;
    }

    public Categories() {}
}
