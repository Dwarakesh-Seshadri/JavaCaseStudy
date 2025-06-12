package be.admin.abis.course.test;

import be.admin.abis.course.GUI.UserInterface;
import be.admin.abis.course.exception.ExitTheProgramException;
import be.admin.abis.course.exception.OptionNotFoundException;
import be.admin.abis.course.exception.PersonNotFoundException;
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
        int result = 0;
        Person p = null;
        do {
            try {
                p = UserInterface.selectUser(pr);
            } catch (OptionNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (PersonNotFoundException e) {
                System.out.println(e.getMessage());
            }
            catch (ExitTheProgramException e) {
                System.out.println(e.getMessage());
                result = 2;
            }
            int i = 0;
            while (p != null) {
                try {
                    i = UserInterface.displayMainMenu(p);
                } catch (OptionNotFoundException e) {
                    i = 0;
                    System.out.println(e.getMessage());
                }
                switch (i) {
                    case 1:
                        Order newOrder = UserInterface.placeOrder(swr, p);
                        System.out.println("Order is: " + newOrder);
                        try {
                            ordRepo.add(newOrder);
                        } catch (TooManyOrdersException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        Report.printOrders(((OrderFileRepository) ordRepo).getOrderList());
                        OrderHistoryFileRepository.addIntoHistory(orderList);
                        ((OrderFileRepository) ordRepo).remove();
                        break;
                    case 3:
                        UserInterface.selectModifyOption(swr, p);
                        break;
                    case 4:
                        LocalDate date = UserInterface.getDate();
                        System.out.println("The total price of the orders made on " + date + " is: " +
                                OrderHistoryFileRepository.calculateTotalByDate(date));
                        break;
                    case 9:
                        try {
                            p = UserInterface.selectUser(pr);
                        } catch (OptionNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        catch (PersonNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        catch (ExitTheProgramException e) {
                            System.out.println(e.getMessage());
                            p = null;
                            result = 2;
                        }

                        break;
                }
            }
        }while(result==0);

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
