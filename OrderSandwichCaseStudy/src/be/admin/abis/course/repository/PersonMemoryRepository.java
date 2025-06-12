package be.admin.abis.course.repository;

import be.admin.abis.course.enumeration.Language;
import be.admin.abis.course.model.Instructor;
import be.admin.abis.course.model.OfficeManager;
import be.admin.abis.course.model.Person;
import be.admin.abis.course.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonMemoryRepository implements PersonRepository{

    private List<Person> personList = new ArrayList<>();

    public PersonMemoryRepository() {
        Person.personCounter = 0;
        Person inst1 = new Instructor("Sandy", Language.Nederlands);
        Person inst2 = new Student("Koen",Language.French);
        Person inst3 = new Student("Gie", Language.Nederlands);
        Person inst4 = new Student("Bart", Language.French);
        Person p1 = new Student("Michel",Language.Nederlands);
        Person p2 = new Instructor("Anne",Language.French);
        Person p3 = new OfficeManager("Bob",Language.Nederlands);

        personList.addAll(Arrays.asList(new Person[]{inst1,inst2,inst3,inst4,p1,p2,p3}));

    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public void add(Person p) {

    }

    @Override
    public void modify(Person p) {

    }

    @Override
    public void delete(Person p) {

    }

    @Override
    public void loadAll() {

    }

    @Override
    public void findByName(String name) {

    }

    public Person findByPersonNumber(int pid){
        for(Person p : personList){
            if (pid == p.getPersonNumber()){
                return p;
            }
        }
        return null;
    }
}
