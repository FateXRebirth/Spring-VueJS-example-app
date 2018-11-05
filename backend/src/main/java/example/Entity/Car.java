package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Integer brandID;

    private Integer modelID;

    private Integer yearID;

    private String creator;

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

    public Integer getYearID() {
        return yearID;
    }

    public void setYearID(Integer yearID) {
        this.yearID = yearID;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    protected Car() {}

    public Car(Integer brandID, Integer modelID, Integer yearID, String creator) {
        this.brandID = brandID;
        this.modelID = modelID;
        this.yearID = yearID;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brandID='" + brandID + '\'' +
                ", modelID='" + modelID + '\'' +
                ", yearID='" + yearID + '\'' +
                ", creator='" + creator + '\'' +
                '}';
    }
}
