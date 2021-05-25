package project.events;

import project.clientsAndPurchases.Ticket;
import project.locations.Location;
import java.util.ArrayList;

public class Event {
    protected int eventId;
    protected String name;
    protected String date;
    protected String description;
    protected int durationInHours;
    protected int locationId;
    protected int participantsLimit;
    protected int confirmedParticipations = 0;
    protected Boolean soldOut = false;
    protected ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    static int noOfEvents = 0;

    {
        this.eventId = ++noOfEvents;
    }

    public Event(String name, String date, String description, int durationInHours, int locationId, int participantsLimit) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.durationInHours = durationInHours;
        this.locationId = locationId;
        this.participantsLimit = participantsLimit;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocation(int locationId) {
        this.locationId = locationId;
    }

    public int getParticipantsLimit() {
        return participantsLimit;
    }

    public void setParticipantsLimit(int participantsLimit) {
        this.participantsLimit = participantsLimit;
    }

    public int getConfirmedParticipations() {
        return confirmedParticipations;
    }

    public void setConfirmedParticipations(int confirmedParticipations) {
        this.confirmedParticipations = confirmedParticipations;
    }

    public Boolean getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getEventId() {
        return eventId;
    }

    @Override
    public String toString() {
        return "Event ID: " + eventId + ". " + "LocationId: " + locationId + ". " + name + " will take place on " + date + " and will last " + durationInHours + " hours. "
                + description + ". Participants limit: " + participantsLimit + ". ";
    }
}
