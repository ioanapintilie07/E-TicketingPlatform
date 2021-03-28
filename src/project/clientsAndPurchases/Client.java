package project.clientsAndPurchases;

import java.util.List;

public class Client implements Comparable<Client>{
    private String firstName;
    private String lastName;
    private int age;
    private int clientId;
    static int noOfClients = 0;
    {
        this.clientId = ++noOfClients;
    }

    public Client(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName + ". Age: " + age + ".";
    }

    @Override
    public int compareTo(Client o) {
        String fullName1 = lastName + firstName;
        String fullName2 = o.getLastName() + o.getFirstName();
        return fullName1.compareTo(fullName2);
    }
}
