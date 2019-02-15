package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Series {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private Integer BrandID;
    private String Name;
    private String DisplayName;

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

    public Series(Integer brandID, String name, String displayName) {
        BrandID = brandID;
        Name = name;
        DisplayName = displayName;
    }
}
