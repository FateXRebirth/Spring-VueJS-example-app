package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private Integer type;
    private String name;
    private String link;
    private String url;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Banner() {
    }

    public Banner(Integer type, String name, String link, String url) {
        this.type = type;
        this.name = name;
        this.link = link;
        this.url = url;
    }
}
