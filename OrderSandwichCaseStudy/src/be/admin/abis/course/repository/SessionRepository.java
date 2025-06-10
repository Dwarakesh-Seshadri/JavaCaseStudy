package be.admin.abis.course.repository;


import be.admin.abis.course.model.Session;

public interface SessionRepository {
    public void add(Session ses);
    public void modify(Session ses);
    public void delete(Session ses);
    public void loadAll();
    public void findByName(String name);
}
