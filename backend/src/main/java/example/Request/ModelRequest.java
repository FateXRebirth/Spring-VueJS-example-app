package example.Request;

public class ModelRequest {

    private int brandID;

    private String name;

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ModelRequest{" +
                "brandID=" + brandID +
                ", name='" + name + '\'' +
                '}';
    }
}
