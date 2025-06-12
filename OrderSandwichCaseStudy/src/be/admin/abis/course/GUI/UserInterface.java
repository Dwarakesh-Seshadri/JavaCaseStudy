package be.admin.abis.course.GUI;

import be.admin.abis.course.enumeration.Language;
import be.admin.abis.course.exception.ExitTheProgramException;
import be.admin.abis.course.exception.OptionNotFoundException;
import be.admin.abis.course.exception.PersonNotFoundException;
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

    public static int displayMainMenu(Person p) throws OptionNotFoundException {

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
        switch (p.getClass().getSimpleName()){
            case "Student":
            case "Instructor":
                switch (Integer.parseInt(option)){
                    case 1:
                    case 9:
                        break;
                    default:
                        throw new OptionNotFoundException("Please enter a valid option");

                }
                break;
            case "OfficeManager":
                switch (Integer.parseInt(option))
                {
                    case 2:
                    case 3:
                    case 4:
                    case 9:
                        break;
                    default:
                        throw new OptionNotFoundException("Please enter a valid option");

                }
                break;
        }
        int result = 0 ;
        try {
            result = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            throw new OptionNotFoundException("Please enter a Valid Option");
        }
        return Integer.parseInt(option);
    }

    public static LocalDate getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Please enter the date for which you want the total:");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        return LocalDate.parse(option,dtf);
    }

    public static Person selectUser(PersonRepository pr) throws OptionNotFoundException, PersonNotFoundException,ExitTheProgramException {
        Scanner scanner = new Scanner(System.in);
        for (Person p : ((PersonMemoryRepository)pr).getPersonList()){
            System.out.println(p.getPersonNumber() + ". " + p.getFirstName());
        }
        System.out.println("Press 0 to exit");
        System.out.println("Please select the user id: ");
        String userId = scanner.nextLine();

        int result = 0;
        try {
            result = Integer.parseInt(userId);
            if ((Integer.parseInt(userId)) == 0){
                throw new ExitTheProgramException("Exiting the application");
            }else {
                if (((PersonMemoryRepository) pr).findByPersonNumber(Integer.parseInt(userId)) == null) {
                    throw new PersonNotFoundException("Please select a userid from the list above");
                } else {
                    return ((PersonMemoryRepository) pr).findByPersonNumber(Integer.parseInt(userId));
                }
            }
        } catch (NumberFormatException e) {
            throw new OptionNotFoundException("Please enter a Valid Option");
        }
    }

public static void selectModifyOption(SandwichRepository swr,Person person){
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
            break;
        case "D":
            System.out.println("List of Sandwiches: ");
            for (Sandwich s : ((SandwichFileRepository)swr).getSandwichList()){
                System.out.println(s.formatSandwich(person.getLanguage()));
            }
            System.out.println("Please Enter an Option or 0 to skip");
            String sandwichId = scanner.nextLine();
            sw = swr.findById(Integer.parseInt(sandwichId));
            swr.delete(sw);
            break;

        }
    }
}
