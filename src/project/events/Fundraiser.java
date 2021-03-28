package project.events;

import project.locations.Location;

public class Fundraiser extends Event{
    private String cause;
    private String organizer;
    private int targetSum;

    public Fundraiser(String name, String date, String description, int durationInHours, Location location, int participantsLimit, String cause, String organizer, int targetSum) {
        super(name, date, description, durationInHours, location, participantsLimit);
        this.cause = cause;
        this.organizer = organizer;
        this.targetSum = targetSum;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    @Override
    public String toString() {
        return super.toString() + "Fundraiser organized by " + organizer + ". "  + "Raising for: " + cause  + ". Target: " + targetSum;
    }
}
