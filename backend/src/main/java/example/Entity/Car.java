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

    private Integer BrandID;

    private Integer SeriesID;

    private Integer CategoryID;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBrandid() {
        return BrandID;
    }

    public void setBrandid(Integer brandid) {
        this.BrandID = brandid;
    }

    public Integer getSeriesid() {
        return SeriesID;
    }

    public void setSeriesid(Integer seriesid) {
        this.SeriesID = seriesid;
    }

    public Integer getCategoryid() {
        return CategoryID;
    }

    public void setCategoryid(Integer categoryid) {
        this.CategoryID = categoryid;
    }

    public Car(Integer brandid, Integer seriesid, Integer categoryid) {
        this.BrandID = brandid;
        this.SeriesID = seriesid;
        this.CategoryID = categoryid;
    }
}
