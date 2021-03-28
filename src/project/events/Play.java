package project.events;
import project.locations.Location;

import java.util.List;

public class Play extends Event{
    List<String> actors;
    private String genre;

    public Play(String name, String date, String description, int durationInHours, Location location, int participantsLimit, List<String> actors, String genre) {
        super(name, date, description, durationInHours, location, participantsLimit);
        this.actors = actors;
        this.genre = genre;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
