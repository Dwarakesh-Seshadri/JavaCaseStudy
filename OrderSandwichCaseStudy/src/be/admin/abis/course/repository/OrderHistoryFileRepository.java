package be.admin.abis.course.repository;

import be.admin.abis.course.enumeration.Language;
import be.admin.abis.course.exception.TooManyOrdersException;
import be.admin.abis.course.model.Order;
import be.admin.abis.course.model.OrderHistory;
import be.admin.abis.course.model.Person;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrderHistoryFileRepository implements OrderRepository{
    private static LocalDate date = LocalDate.now();
    private List<Order> orderList = new ArrayList<>();

    public static void addIntoHistory(List<Order> orderList){
        Path path = Paths.get("\\temp\\javacourses\\orderhistory.csv");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder order = new StringBuilder();
        for (Order o : orderList){
            order.append(date.format(dtf))
                    .append(";")
                    .append(o.getP().getFirstName())
                    .append(";")
                    .append(o.getP().getLanguage())
                    .append(";")
                    .append(o.getSw().getItemNumber())
                    .append(";")
                    .append(o.getSw().getTypeNed())
                    .append(";")
                    .append(o.getSw().getNameNed())
                    .append(";")
                    .append(o.getVeggie())
                    .append(";")
                    .append(o.getBreadType())
                    .append(";")
                    .append(o.getSw().getPrice())
                    .append("\n")
            ;

            try (BufferedWriter writer =
                         Files.newBufferedWriter(path, Charset.forName("UTF-8")
                                 , StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
                writer.write(String.valueOf(order));
            } catch (IOException e) {
// Handle file I/O exception...
            }

        }
    }

    public static double calculateTotalByDate(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dtf);
        Double result = 0.0;
        try {
            Path path = Paths.get("\\temp\\javacourses\\orderhistory.csv");
            result = Files.lines(path).filter(x->x.startsWith(formattedDate))
                             .mapToDouble(s -> parse(s).getPrice()).sum();
        } catch (IOException e) {
// Handle file I/O exception...
        }
    return result;
    }

    @Override
    public void add(Order o) throws TooManyOrdersException {

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
        return 0;
    }

    public static OrderHistory parse(String s){
        String[] orderWithFormat = s.split(";");
        OrderHistory ord = new OrderHistory();
        ord.setPrice(Double.parseDouble(orderWithFormat[8]));
        return ord;
    }
}
