package be.admin.abis.course.repository;

import be.admin.abis.course.model.Person;

public interface PersonRepository {
    public void add(Person p);
    public void modify(Person p);
    public void delete(Person p);
    public void loadAll();
    public void findByName(String name);
}
