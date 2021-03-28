package project.events;

import project.locations.Location;

public class Concert extends Event {
    private String musician;
    private String genre;

    public Concert(String name, String date, String description, int durationInHours, int locationId, int participantsLimit, String musician, String genre) {
        super(name, date, description, durationInHours, locationId, participantsLimit);
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

    @Override
    public String toString() {
        return super.toString() + "Performer: " + musician + ". Enjoy a night of " + genre + " music.";
    }
}
