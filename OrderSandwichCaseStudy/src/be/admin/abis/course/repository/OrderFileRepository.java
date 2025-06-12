package be.admin.abis.course.repository;

import be.admin.abis.course.enumeration.Language;
import be.admin.abis.course.exception.TooManyOrdersException;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.Person;
import be.admin.abis.course.model.Sandwich;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OrderFileRepository implements OrderRepository{

    private List<Order> orderList = new ArrayList<>();

    public OrderFileRepository() {
        loadAll();
    }

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
        this.save(o);
    }

    @Override
    public void modify(Order o) {

    }

    @Override
    public void delete(Order o) {

    }

    @Override
    public void loadAll() {
        Path path = Paths.get("\\temp\\javacourses\\savedorders.csv");
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){
            String line = null;
            while ((line=reader.readLine())!=null){
                orderList.add(parseOrder(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
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

    public void save(Order o){
        StringBuilder order = new StringBuilder();
        order.append(o.getP().getFirstName())
                .append(";")
                .append(o.getP().getLanguage())
                .append(";")
                .append(o.getSw().getItemNumber())
                .append(";")
                .append(o.getVeggie())
                .append(";")
                .append(o.getBreadType())
                .append("\n");

        Path path = Paths.get("\\temp\\javacourses\\savedorders.csv");
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, Charset.forName("UTF-8")
                             , StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
            writer.write(String.valueOf(order));
        } catch (IOException e) {
// Handle file I/O exception...
        }

    }

    public void remove(){
        orderList.removeAll(orderList);
        File savedOrders = new File("\\temp\\javacourses\\savedorders.csv");
        savedOrders.delete();
    }


    public Order parseOrder(String s){
        String[] orderWithFormat = s.split(";");
        Order ord = new Order();
        SandwichRepository swr = new SandwichFileRepository();
        swr.showList();
        ord.setP(new Person(orderWithFormat[0], Language.valueOf(orderWithFormat[1])));
        ord.setSw(swr.findById(Integer.parseInt(orderWithFormat[2])));
        System.out.println("Sandwich" + ord.getSw());
        ord.setVeggie(orderWithFormat[3]);
        ord.setBreadType(orderWithFormat[4]);
        return ord;
    }

}
