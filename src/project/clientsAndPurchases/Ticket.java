package project.clientsAndPurchases;

import project.events.Event;

public class Ticket {
    Event event;
    Client client;
    private double price;
    private int ticketId;
    static int noOfTickets = 0;

    {
        ticketId = ++noOfTickets;
    }

    public Ticket(Event event, Client client, double price) {
        this.event = event;
        this.client = client;
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }
}
