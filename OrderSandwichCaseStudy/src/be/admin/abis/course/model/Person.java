package be.admin.abis.course.model;

import be.admin.abis.course.enumeration.Language;

import java.util.Objects;

public class Person {
    private int personNumber;
    private String firstName;
    private Language language;
    public static int personCounter = 0;

    public int getPersonNumber() {
        return personNumber;
    }

    public void setPersonNumber(int personNumber) {
        this.personNumber = personNumber;
    }

    public Language getLanguage() {
        return language;
    }



    public void setLanguage(Language language) {
        this.language = language;
    }

    public Person(String firstName,Language language) {
        this.firstName = firstName;
        this.language = language;
        personNumber = ++personCounter;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", language=" + language +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(firstName, person.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName);
    }
}
