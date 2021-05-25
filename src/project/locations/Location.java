package project.locations;

public class Location {
    protected int locationId;
    protected  String name;
    static int noOfLocations = 0;

    /*{
        locationId = ++noOfLocations;
    }*/

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationId() {
        return locationId;
    }

    @Override
    public String toString() {
        return "Location " + locationId + ". Name: " + name + ". ";
    }
}
