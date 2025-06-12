package be.admin.abis.course.GUI;

import be.admin.abis.course.enumeration.Language;
import be.admin.abis.course.model.*;
import be.admin.abis.course.repository.PersonMemoryRepository;
import be.admin.abis.course.repository.PersonRepository;
import be.admin.abis.course.repository.SandwichFileRepository;
import be.admin.abis.course.repository.SandwichRepository;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static Order placeOrder(SandwichRepository swr,Person person){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Enter the Session Name: ");
        String sessionName = scanner.nextLine();
        Session ses = new Session(sessionName);
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

    public static int displayMainMenu(Person p){

        StringBuilder mainMenu = new StringBuilder();

        switch (p.getClass().getSimpleName()){

            case "Student":
            case "Instructor":
                mainMenu.append("1. Place Order")
                        .append("\n")
                        .append("9. Exit")
                        .append("\n")
                        .append("Please Enter an Option: ")
                        .append("\n");
                        break;
            case "OfficeManager":
                mainMenu.append("2. Print Order")
                        .append("\n")
                        .append("3. Modify Sandwich List")
                        .append("\n")
                        .append("4. Calculate Total Price of the orders")
                        .append("\n")
                        .append("9. Exit")
                        .append("\n")
                        .append("Please Enter an Option: ")
                        .append("\n");
                        break;


        }

        System.out.println(String.valueOf(mainMenu));
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        return Integer.parseInt(option);
    }

    public static LocalDate getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Please enter the date for which you want the total:");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        return LocalDate.parse(option,dtf);
    }

    public static Person selectUser(PersonRepository pr){
        Scanner scanner = new Scanner(System.in);
        for (Person p : ((PersonMemoryRepository)pr).getPersonList()){
            System.out.println(p.getPersonNumber() + ". " + p.getFirstName());
        }
        System.out.println("Press 0 to exit");
        System.out.println("Please select the user id: ");
        String userId = scanner.nextLine();
        return ((PersonMemoryRepository) pr).findByPersonNumber(Integer.parseInt(userId));

    }

    public static Person selectModifyOption(SandwichRepository swr){
        Sandwich sw = new Sandwich();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select A to Add, D to delete:");
        String option = scanner.nextLine();
        switch (option){
            case "A":
                System.out.println("Please enter the type of Sandwich in NL:");
                option = scanner.nextLine();
                sw.setTypeNed(option);
                System.out.println("Please enter the type of Sandwich in FR:");
                option = scanner.nextLine();
                sw.setTypeFr(option);
                System.out.println("Please enter the name of Sandwich in NL:");
                option = scanner.nextLine();
                sw.setNameNed(option);
                System.out.println("Please enter the name of Sandwich in FR:");
                option = scanner.nextLine();
                sw.setNamefr(option);
                System.out.println("Please mention whether veggies can be added or not :");
                option = scanner.nextLine();
                sw.setVeggie(option.toUpperCase().equals("YES"));
                System.out.println("Please mention whether a choice for type of bread could be made or not: ");
                option = scanner.nextLine();
                sw.setBreadType(option.toUpperCase().equals("YES"));
                System.out.println("Please mention enter the price of the sandwich: ");
                option = scanner.nextLine();
                sw.setPrice(Double.parseDouble(option));
                swr.add(sw);
            case "D":
                System.out.println("List of Sandwiches: ");
                for (sw : ((SandwichFileRepository)swr).getSandwichList()
                    System.out.println(sw.formatSandwich(person.getLanguage()));
                }
                System.out.println("Please Enter an Option: ");
                String sandwichId = scanner.nextLine();
                Sandwich sw = swr.findById(Integer.parseInt(sandwichId));


        }


    }
}
