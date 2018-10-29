package example.Request;

public class BrandRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
