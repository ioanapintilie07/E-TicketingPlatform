package project.actions;

import project.DB.OnlineLocationRepository;
import project.DB.PhysicalLocationRepository;
import project.locations.Location;
import project.locations.OnlineLocation;
import project.locations.PhysicalLocation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LocationService {
    static OnlineLocationRepository onlineLocationRepository = new OnlineLocationRepository();
    static PhysicalLocationRepository physicalLocationRepository = new PhysicalLocationRepository();
    public void locationMenu() {
        System.out.println("What kind of location are you interested in?");
        System.out.println("1. Online location");
        System.out.println("2. Physical location");
    }

    public void listLocations() {
        ArrayList<Location> locations = new ArrayList<Location>(physicalLocationRepository.findAllPhysicalLocations());
        locations.addAll(onlineLocationRepository.findAllOnlineLocations());
        if (locations.size() == 0) {
            System.out.println("No locations to show. \n");
        } else {
            for (Location location : locations) {
                System.out.println(location);
            }
        }
    }

    public void generateLocation() {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String[] names = {"Garden of Eve", "Top the Rooftop Tops", "Lantern Club", "Rose Petal", "Pretentious Theatre"};
        String[] platforms = {"Zoom", "Google Meets", "Microsoft Teams", "Webex"};
        String[] cities = {"New York", "Bucharest", "Berlin", "Munich", "Paris"};
        String[] addresses = {"Independence Street 4", "Mountain Blvd 3", "Sunset Lane", "Corner of Park and Lake"};
        String[] links = {"https://www.example.com/", "https://www.legitzoommeetingpleaseclick.com/"};
        int capacity = rand.nextInt(10001);
        int attendees = rand.nextInt(1500);
        String name = names[rand.nextInt(names.length)];
        String platform = platforms[rand.nextInt(platforms.length)];
        String city = cities[rand.nextInt(cities.length)];
        String address = addresses[rand.nextInt(addresses.length)];
        String link = links[rand.nextInt(links.length)];
        locationMenu();
        int opt;
        opt = scanner.nextInt();
        switch (opt) {
            case 1:
                OnlineLocation online = new OnlineLocation(platform, link, attendees);
                onlineLocationRepository.createOnlineLocation(online);
                break;
            case 2:
                PhysicalLocation phys = new PhysicalLocation(name, city, address, capacity);
                physicalLocationRepository.createPhysicalLocation(phys);
                break;
        }
        System.out.println("\nLocation added succcessfully!\n");
    }

    public void addLocation() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter location name: ");
        String name = scanner.next();
        locationMenu();
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Access link: ");
                String link = scanner.next();
                System.out.println("Max number of attendees: ");
                int maxi = scanner.nextInt();
                OnlineLocation onlineLocation = new OnlineLocation(name, link, maxi);
                onlineLocationRepository.createOnlineLocation(onlineLocation);
                break;
            case 2:
                System.out.println("City: ");
                String city = scanner.next();
                System.out.println("Address: ");
                String add = scanner.next();
                System.out.println("Capacity: ");
                int capacity = scanner.nextInt();
                PhysicalLocation physLocation = new PhysicalLocation(name, city, add, capacity);
                physicalLocationRepository.createPhysicalLocation(physLocation);
                break;
        }
        System.out.println("\nLocation added successfully!\n");
    }

    public void deleteLocation() {
        locationMenu();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int option = scanner.nextInt();
        System.out.println("Please enter the location id:");
        int locationId = scanner.nextInt();
        int deleted = 0;
        switch (option) {
            case 1:
                deleted = onlineLocationRepository.deleteOnlineLocation(locationId);
                break;
            case 2:
                deleted = physicalLocationRepository.deletePhysicalLocation(locationId);
                break;
        }

        if (deleted > 0) {
            System.out.println("\nLocation deleted succcessfully!");
        } else {
            System.out.println("\nSorry, couldn't find the location you were looking for");
        }
    }

    public void updateLocation() {
        locationMenu();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int option = scanner.nextInt();
        System.out.println("Please enter the location id:");
        int locationId = scanner.nextInt();
        System.out.println("What name should the location have now?");
        String name = scanner.next();
        int updated = 0;
        switch (option) {
            case 1:
                updated = onlineLocationRepository.updateOnlineLocation(locationId, name);
                break;
            case 2:
                updated = physicalLocationRepository.updatePhysicalLocation(locationId, name);
                break;
        }
        if (updated > 0) {
            System.out.println("\nLocation updated succcessfully!");
        } else {
            System.out.println("\nSorry, couldn't find the location you were looking for");
        }
    }

    public void searchLocation() {
        locationMenu();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        int option = scanner.nextInt();
        System.out.println("Please enter the location id:");
        int locationId = scanner.nextInt();
        switch (option) {
            case 1:
                OnlineLocation online = onlineLocationRepository.findOnlineLocation(locationId);
                if (online == null) {
                    System.out.println("\nSorry, couldn't find the location you were looking for");
                }
                else {
                    System.out.println("Found it.\n" + online);
                }
                break;
            case 2:
                PhysicalLocation phys = physicalLocationRepository.findPhysicalLocation(locationId);
                if (phys == null) {
                    System.out.println("\nSorry, couldn't find the location you were looking for");
                }
                else {
                    System.out.println("Found it.\n" + phys);
                }
                break;
        }
    }

}
