package example.Response;

public class Categories {

    private Integer id;
    private Integer brandID;
    private Integer seriesID;
    private String name;
    private String displayName;
    private String seriesName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
    }

    public Integer getSeriesID() {
        return seriesID;
    }

    public void setSeriesID(Integer seriesID) {
        this.seriesID = seriesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Categories() {
    }
}
