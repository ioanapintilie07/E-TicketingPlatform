package project.locations;

import project.locations.Location;

public class PhysicalLocation extends Location {
    private String city;
    private String address;
    private int capacity;

    public PhysicalLocation(String name, String city, String address, int capacity) {
        super(name);
        this.city = city;
        this.address = address;
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + "City: " + city + ". Address: " + address + ". Capacity: " + capacity;
    }
}
