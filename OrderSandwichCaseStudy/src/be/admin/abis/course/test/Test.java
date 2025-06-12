package be.admin.abis.course.test;

import be.admin.abis.course.GUI.UserInterface;
import be.admin.abis.course.exception.TooManyOrdersException;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.OrderHistory;
import be.admin.abis.course.model.Person;
import be.admin.abis.course.model.Sandwich;
import be.admin.abis.course.output.Report;
import be.admin.abis.course.repository.*;

import java.time.LocalDate;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        PersonRepository pr = new PersonMemoryRepository();
        List<Person> personList = ((PersonMemoryRepository)pr).getPersonList();
        SandwichRepository swr = new SandwichFileRepository();
        List<Sandwich> swList = ((SandwichFileRepository)swr).getSandwichList();
        OrderRepository ordRepo = new OrderFileRepository();
        List<Order> orderList = ((OrderFileRepository)ordRepo).getOrderList();
        Person p = UserInterface.selectUser(pr);
        int i = 0;
        do {
            i = UserInterface.displayMainMenu(p);
            switch(i){
                case 1:
                    Order newOrder = UserInterface.placeOrder(swr,p);
                    System.out.println("Order is: " + newOrder);
                    try {
                        ordRepo.add(newOrder);
                    } catch (TooManyOrdersException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    Report.printOrders(((OrderFileRepository)ordRepo).getOrderList());
                    OrderHistoryFileRepository.addIntoHistory(orderList);
                    ((OrderFileRepository) ordRepo).remove();
                    break;
                case 4:
                    LocalDate date = UserInterface.getDate();
                    System.out.println("The total price of the orders made on " + date + " is: " +
                                        OrderHistoryFileRepository.calculateTotalByDate(date));
                    break;
                case 9:
                    p = UserInterface.selectUser(pr);
                    break;

            }
        } while(p!= null);

/*
        for (int i = 0; i < 1 ;i++){
            Order newOrder = UserInterface.placeOrder(swr);
            System.out.println("Order is: " + newOrder);
            try {
                ordRepo.add(newOrder);
            } catch (TooManyOrdersException e) {
                System.out.println(e.getMessage());
            }
        }
*/


    }
}
