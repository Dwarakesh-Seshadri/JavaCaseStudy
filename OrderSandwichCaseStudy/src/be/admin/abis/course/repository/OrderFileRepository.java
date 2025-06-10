package be.admin.abis.course.repository;

import be.admin.abis.course.exception.TooManyOrdersException;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.Person;

import java.util.ArrayList;
import java.util.List;

public class OrderFileRepository implements OrderRepository{

    private List<Order> orderList = new ArrayList<>();

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public void add(Order o) throws TooManyOrdersException {
        if (this.calculateNumberOfOrders(o.getP())>1){
            throw new TooManyOrdersException("Cannot order more than 2 sandwiches");
        }
        orderList.add(o);
    }

    @Override
    public void modify(Order o) {

    }

    @Override
    public void delete(Order o) {

    }

    @Override
    public void loadAll() {

    }

    @Override
    public void findByName(String name) {

    }

    @Override
    public int calculateNumberOfOrders(Person p) {
        int numberOfOrders = 0;
        for (Order o : orderList){
            if (o.getP().equals(p)){
                numberOfOrders++;
            }
        }
        return numberOfOrders;
    }
}
