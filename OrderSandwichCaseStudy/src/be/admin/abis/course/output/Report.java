package be.admin.abis.course.output;

import be.admin.abis.course.model.Order;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

public class Report {

    public static void printOrders(List<Order> orderList){

        try (PrintWriter printFile = new PrintWriter(new FileWriter("\\temp\\javacourses\\orderrequest.txt")))
        {

            printFile.println("-".repeat(100));
            printFile.printf("%-42s%15s%43s\n"," ","Order Overview"," ");
            printFile.println("-".repeat(100));
            printFile.printf("%-40s%-20s%-20s%-20s\n","Sandwich Name","Veggie Options","Bread Options","OrderedBy");
            printFile.println("-".repeat(100));
        orderList.stream()
                    .sorted(Comparator.comparing(order -> order.getP().getFirstName()))
                    .forEach(order -> printFile.printf("%-40s%-20s%-20s%-20s\n",
                            order.getSw().getNameNed(),
                            order.getVeggie(),
                            order.getBreadType(),
                            order.getP().getFirstName()));


        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

    }



}
