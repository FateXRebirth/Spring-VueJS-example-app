package example.Entity;

public class Brand {

    private Integer BrandID;
    private String Name;
    private String DisplayName;

    public Integer getBrandID() {
        return BrandID;
    }

    public void setBrandID(Integer brandID) {
        BrandID = brandID;
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

    public Brand(String name, String displayName) {
        Name = name;
        DisplayName = displayName;
    }
}
