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

    private Integer brandid;

    private Integer seriesid;

    private Integer categoryid;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getBrandid() {
        return brandid;
    }

    public void setBrandid(Integer brandid) {
        this.brandid = brandid;
    }

    public Integer getSeriesid() {
        return seriesid;
    }

    public void setSeriesid(Integer seriesid) {
        this.seriesid = seriesid;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Car(Integer brandid, Integer seriesid, Integer categoryid) {
        this.brandid = brandid;
        this.seriesid = seriesid;
        this.categoryid = categoryid;
    }
}
