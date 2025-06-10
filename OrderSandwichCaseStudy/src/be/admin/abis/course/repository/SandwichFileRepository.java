package be.admin.abis.course.repository;

import be.admin.abis.course.model.Sandwich;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SandwichFileRepository implements  SandwichRepository{

    public List<Sandwich> sandwichList = new ArrayList<>();

    public List<Sandwich> getSandwichList() {
        return sandwichList;
    }

    public void setSandwichList(List<Sandwich> sandwichList) {
        this.sandwichList = sandwichList;
    }

    public SandwichFileRepository() {
        loadAll();
    }

    @Override
    public void add(Sandwich sw) {

    }

    @Override
    public void modify(Sandwich sw) {

    }

    @Override
    public void delete(Sandwich sw) {

    }

    @Override
    public void loadAll() {
        Path path = Paths.get("\\temp\\javacourses\\Sandwiches.csv");
        try {
            BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"));
            String line = null;
            while ((line=reader.readLine())!=null){
                sandwichList.add(parseSandwich(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    public Sandwich parseSandwich(String s){
        String[] sandwichWithFormat = s.split(";");
        Sandwich sw = new Sandwich();
        sw.setTypeNed(sandwichWithFormat[0]);
        sw.setNameNed(sandwichWithFormat[1]);
        sw.setTypeFr(sandwichWithFormat[2]);
        sw.setNamefr(sandwichWithFormat[3]);
        sw.setVeggie(sandwichWithFormat[4].toUpperCase().equals("YES"));
        sw.setBreadType(sandwichWithFormat[5].toUpperCase().equals("YES"));
        sw.setPrice(Double.parseDouble(sandwichWithFormat[6]));
        return sw;
    }


    @Override
    public Sandwich findByName(String name) {
        return null;
    }

    @Override
    public Sandwich findById(int id) {

        for (Sandwich sw : sandwichList){
            if (sw.getItemNumber() == id){
                return sw;
            }
        }
        return null;
    }


}
