package be.admin.abis.course.repository;

import be.admin.abis.course.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMemoryRepository implements PersonRepository{

    private List<Person> personList = new ArrayList<>();

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
}
