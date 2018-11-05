package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer brandID;

    private String name;

    protected Model() {}

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

    public Model(Integer brandID, String name) {
        this.brandID = brandID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", brandID=" + brandID +
                ", name='" + name + '\'' +
                '}';
    }
}
