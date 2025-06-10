package be.admin.abis.course.test;

import be.admin.abis.course.GUI.UserInterface;
import be.admin.abis.course.exception.TooManyOrdersException;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.Sandwich;
import be.admin.abis.course.output.Report;
import be.admin.abis.course.repository.OrderFileRepository;
import be.admin.abis.course.repository.OrderRepository;
import be.admin.abis.course.repository.SandwichFileRepository;
import be.admin.abis.course.repository.SandwichRepository;

import java.util.List;

public class Test {
    public static void main(String[] args) {

        SandwichRepository swr = new SandwichFileRepository();
        List<Sandwich> swlist = ((SandwichFileRepository)swr).getSandwichList();
        swlist.forEach(System.out::println);
        OrderRepository ordRepo = new OrderFileRepository();

        for (int i = 0; i < 1 ;i++){
            Order newOrder = UserInterface.placeOrder(swr);
            System.out.println("Order is: " + newOrder);
            try {
                ordRepo.add(newOrder);
            } catch (TooManyOrdersException e) {
                System.out.println(e.getMessage());
            }
        }

        Report.printOrders(((OrderFileRepository)ordRepo).getOrderList());

    }
}
