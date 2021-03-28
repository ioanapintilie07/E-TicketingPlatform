package project.events;
import java.util.List;


public class Exhibition {
    private List<String> artists;
    private String theme;

    public Exhibition(List<String> artists, String theme) {
        this.artists = artists;
        this.theme = theme;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return super.toString() + "Featuring works by: " + artists + ". Theme: ";
    }
}
