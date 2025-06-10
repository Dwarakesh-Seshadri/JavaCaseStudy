package be.admin.abis.course.GUI;

import be.admin.abis.course.enumeration.Language;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.Person;
import be.admin.abis.course.model.Sandwich;
import be.admin.abis.course.model.Session;
import be.admin.abis.course.repository.SandwichFileRepository;
import be.admin.abis.course.repository.SandwichRepository;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static Order placeOrder(SandwichRepository swr){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter the Session Name: ");
        String sessionName = scanner.nextLine();
        Session ses = new Session(sessionName);
        System.out.println("Please Enter the Person Name: ");
        String personName = scanner.nextLine();
        Person person = new Person(personName, Language.Nederlands);
        System.out.println("List of Sandwiches: ");
        for (Sandwich sw : ((SandwichFileRepository)swr).getSandwichList()){
            System.out.println(sw.formatSandwich(person.getLanguage()));
        }
        System.out.println("Please Enter an Option: ");
        String sandwichId = scanner.nextLine();
        Sandwich sw = swr.findById(Integer.parseInt(sandwichId));
        String veggieOption = "",breadOption = "";
        if (sw.isVeggie()){
            System.out.println("Please mention if you need veggies or not: ");
            veggieOption = scanner.nextLine();
        }
        if (sw.isBreadType()){
            System.out.println("Please mention the type of Bread you need: ");
            breadOption = scanner.nextLine();
        }
        Order newOrder = new Order(person,sw,veggieOption,breadOption);
        return newOrder;
    }


}
