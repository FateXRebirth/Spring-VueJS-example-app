package example.Request;

public class CarRequest {

    private int brandID;

    private int modelID;

    private int yearID;

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public int getYearID() {
        return yearID;
    }

    public void setYearID(int yearID) {
        this.yearID = yearID;
    }

    @Override
    public String toString() {
        return "CarRequest{" +
                "brandID=" + brandID +
                ", modelID=" + modelID +
                ", yearID=" + yearID +
                '}';
    }
}
