package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    public String getBrand() {
        return name;
    }

    public void setBrand(String name) {
        this.name = name;
    }

    protected Brand() {}

    public Brand(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brand='" + name + '\'' +
                '}';
    }
}
