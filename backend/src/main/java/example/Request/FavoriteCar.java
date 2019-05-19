package example.Request;

public class FavoriteCar {

    private Integer memberid;
    private Integer carid;

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getCarid() {
        return carid;
    }

    public void setCarid(Integer carid) {
        this.carid = carid;
    }

    @Override
    public String toString() {
        return "FavoriteCar{" +
                "memberid=" + memberid +
                ", carid=" + carid +
                '}';
    }
}
