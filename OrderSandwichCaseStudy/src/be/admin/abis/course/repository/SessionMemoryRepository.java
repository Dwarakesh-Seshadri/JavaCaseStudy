package be.admin.abis.course.repository;

import be.admin.abis.course.model.Person;
import be.admin.abis.course.model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionMemoryRepository implements SessionRepository{

    private List<Session> sessionList = new ArrayList<>();

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }

    @Override
    public void add(Session ses) {

    }

    @Override
    public void modify(Session ses) {

    }

    @Override
    public void delete(Session ses) {

    }

    @Override
    public void loadAll() {

    }

    @Override
    public void findByName(String name) {

    }
}
