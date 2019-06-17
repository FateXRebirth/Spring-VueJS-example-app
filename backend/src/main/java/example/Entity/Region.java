package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Region {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private Integer country;
    private String label;
    private Integer value;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Region() {
    }

    public Region(Integer country, String label, Integer value) {
        this.country = country;
        this.label = label;
        this.value = value;
    }
}
