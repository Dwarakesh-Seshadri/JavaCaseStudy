package be.admin.abis.course.repository;

import be.admin.abis.course.model.Sandwich;

public interface SandwichRepository {
    public void add(Sandwich sw);
    public void modify(Sandwich sw);
    public void delete(Sandwich sw);
    public void loadAll();
    public Sandwich findByName(String name);
    public Sandwich findById(int id);
    public void showList();
}
