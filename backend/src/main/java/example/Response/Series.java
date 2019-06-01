package example.Response;

public class Series {

    private Integer id;
    private Integer brandID;
    private String name;
    private String displayName;

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

    public Series() {
    }
}
