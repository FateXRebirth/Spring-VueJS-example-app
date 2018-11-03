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

    private Integer parentID;

    private String name;

    protected Model() {}

    public Model(Integer parentID, String name) {
        this.parentID = parentID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", parentID=" + parentID +
                ", name='" + name + '\'' +
                '}';
    }
}
