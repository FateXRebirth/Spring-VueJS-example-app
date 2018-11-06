package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Year {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    protected Year() {}

    public Year(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Year{" +
                "id=" + id +
                ", year='" + year + '\'' +
                '}';
    }
}
