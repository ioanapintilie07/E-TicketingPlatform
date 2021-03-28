package project.events;
import project.locations.Location;

import java.util.List;

public class Play extends Event{
    String mainActor;
    private String genre;

    public Play(String name, String date, String description, int durationInHours, int locationId, int participantsLimit, String mainActor, String genre) {
        super(name, date, description, durationInHours, locationId, participantsLimit);
        this.mainActor = mainActor;
        this.genre = genre;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return super.toString() + "Play starring: " + mainActor + ". Genre: " + genre + ".";
    }
}
