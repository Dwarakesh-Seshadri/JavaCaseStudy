package be.admin.abis.course.model;

public class Order {
    private Person p;
    private Sandwich sw;
    private String veggie;
    private String breadType;

    public Order() {

    }

    public String getVeggie() {
        return veggie;
    }

    public void setVeggie(String veggie) {
        this.veggie = veggie;
    }

    public Order(Person p, Sandwich sw, String veggie, String breadType) {
        this.p = p;
        this.sw = sw;
        this.breadType = breadType;
        this.veggie = veggie;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }

    public Sandwich getSw() {
        return sw;
    }

    public void setSw(Sandwich sw) {
        this.sw = sw;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "p=" + p +
                ", sw=" + sw +
                ", veggie='" + veggie + '\'' +
                ", breadType='" + breadType + '\'' +
                '}';
    }
}
