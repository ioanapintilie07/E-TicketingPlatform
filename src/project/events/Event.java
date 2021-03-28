package project.events;

import project.locations.Location;
import java.util.List;

public class Event {
    protected int eventId;
    protected String name;
    protected String date;
    protected String description;
    protected int durationInHours;
    protected Location location;
    protected int participantsLimit;
    protected int confirmedParticipations = 0;
    protected Boolean soldOut = false;
    static int noOfEvents = 0;

    {
        this.eventId = ++noOfEvents;
    }

    public Event(String name, String date, String description, int durationInHours, Location location, int participantsLimit) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.durationInHours = durationInHours;
        this.location = location;
        this.participantsLimit = participantsLimit;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Boolean checkAvailability () {
        if (soldOut) {
            return false;
        }
        else {
            if (confirmedParticipations + 1 == participantsLimit) {
                soldOut = true;
            }
            confirmedParticipations++;
            return true;
        }
    }
}
