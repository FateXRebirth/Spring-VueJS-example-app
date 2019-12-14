package example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dialogue {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer ID;
    private String uuid;
    private String item;
    private String title;
    private String sender;
    private String receiver;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Dialogue() {
    }

    public Dialogue(String uuid, String item, String title, String sender, String receiver) {
        this.uuid = uuid;
        this.item = item;
        this.title = title;
        this.sender = sender;
        this.receiver = receiver;
    }
}
