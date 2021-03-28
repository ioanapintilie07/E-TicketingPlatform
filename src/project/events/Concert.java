package project.events;

import project.locations.Location;

public class Concert extends Event {
    private String musician;
    private String genre;

    public Concert(String name, String date, String description, int durationInHours, Location location, int participantsLimit, String musician, String genre) {
        super(name, date, description, durationInHours, location, participantsLimit);
        this.musician = musician;
        this.genre = genre;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
