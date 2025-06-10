package be.admin.abis.course.repository;


import be.admin.abis.course.exception.TooManyOrdersException;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.Person;


public interface OrderRepository {
    public void add(Order o) throws TooManyOrdersException;
    public void modify(Order o);
    public void delete(Order o);
    public void loadAll();
    public void findByName(String name);
    public int calculateNumberOfOrders(Person p);
}
