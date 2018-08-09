package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String model;

    public Model(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}
