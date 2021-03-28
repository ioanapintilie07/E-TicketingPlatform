package project.locations;

import project.locations.Location;

public class OnlineLocation extends Location {
    private String accessLink;
    private int maxAttendees;

    public OnlineLocation(String name, String accessLink, int maxAttendees) {
        super(name);
        this.accessLink = accessLink;
        this.maxAttendees = maxAttendees;
    }

    public String getAccessLink() {
        return accessLink;
    }

    public void setAccessLink(String accessLink) {
        this.accessLink = accessLink;
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    @Override
    public String toString() {
        return super.toString() + "Link: " + accessLink + ". Max attendees: " + maxAttendees + ". ";
    }
}
