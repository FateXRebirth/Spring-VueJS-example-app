package example.Response;

public class Series {

    private Integer ID;
    private Integer ParentID;
    private String Name;
    private String DisplayName;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getParentID() {
        return ParentID;
    }

    public void setParentID(Integer parentID) {
        ParentID = parentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public Series() {}
}
