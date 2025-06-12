package be.admin.abis.course.model;

import java.time.LocalDate;

public class OrderHistory {
    private LocalDate date;
    private Order o;
    private double price;

    public OrderHistory(LocalDate date, Order o, double price) {
        this.date = date;
        this.o = o;
        this.price = price;
    }

    public OrderHistory() {

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Order getO() {
        return o;
    }

    public void setO(Order o) {
        this.o = o;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
