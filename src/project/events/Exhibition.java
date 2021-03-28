package project.events;
import java.util.List;


public class Exhibition extends Event {
    private String mainArtist;
    private String theme;

    public Exhibition(String name, String date, String description, int durationInHours, int locationId, int participantsLimit, String mainArtist, String theme) {
        super(name, date, description, durationInHours, locationId, participantsLimit);
        this.mainArtist = mainArtist;
        this.theme = theme;
    }

    public String getMainArtist() {
        return mainArtist;
    }

    public void setMainArtist(String mainArtist) {
        this.mainArtist = mainArtist;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return super.toString() + "Featuring works by: " + mainArtist + ". Theme: " + theme;
    }
}
