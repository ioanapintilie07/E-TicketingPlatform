package project.actions;

import project.clientsAndPurchases.Client;
import project.clientsAndPurchases.Ticket;
import project.events.Event;
import project.locations.Location;
import project.locations.OnlineLocation;
import project.locations.PhysicalLocation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Reader {
    private static Reader instance = null;

    private Reader() {

    }

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public ArrayList<Client> readClientsCSV() {
        String path = "./src/project/files/read/clients.csv";
        ArrayList<Client> clients = new ArrayList<>();
        try (BufferedReader fin = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fin.readLine()) != null) {
                String[] clientInfo = line.split(",");
                String firstName = clientInfo[0];
                String lastName = clientInfo[1];
                int age = Integer.parseInt(clientInfo[2]);
                Client client = new Client(firstName, lastName, age);
                clients.add(client);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Input file does not exist");
        }
        catch(IOException e) {
            System.out.println("Failed to read from file");
        }
        return clients;
    }

    public ArrayList<Event> readEventsCSV() {
        String path = "./src/project/files/read/events.csv";
        ArrayList<Event> events = new ArrayList<>();
        try (BufferedReader fin = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fin.readLine()) != null) {
                String[] eventInfo = line.split(",");
                String name  = eventInfo[0];
                String date = eventInfo[1];
                String description = eventInfo[2];
                int duration = Integer.parseInt(eventInfo[3]);
                int location = Integer.parseInt(eventInfo[4]);
                int participantsLimit = Integer.parseInt(eventInfo[5]);
                Event event = new Event(name, date, description, duration, location, participantsLimit);
                events.add(event);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Input file does not exist");
        }
        catch(IOException e) {
            System.out.println("Failed to read from file");
        }
        return events;
    }

    public ArrayList<Location> readPhysicalLocationsCSV() {
        String path = "./src/project/files/read/physicalLocations.csv";;
        ArrayList<Location> locations = new ArrayList<>();
        try (BufferedReader fin = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fin.readLine()) != null) {
                String[] locationInfo = line.split(",");
                String name  = locationInfo[0];
                String city = locationInfo[1];
                String address = locationInfo[2];
                int capacity = Integer.parseInt(locationInfo[3]);
                Location location = new PhysicalLocation(name, city, address, capacity);
                locations.add(location);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Input file does not exist");
        }
        catch(IOException e) {
            System.out.println("Failed to read from file");
        }
        return locations;
    }

    public ArrayList<Location> readOnlineLocationsCSV() {
        String path = "./src/project/files/read/onlineLocations.csv";
        ArrayList<Location> locations = new ArrayList<>();
        try (BufferedReader fin = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = fin.readLine()) != null) {
                String[] locationInfo = line.split(",");
                String name  = locationInfo[0];
                String link = locationInfo[1];
                int maxAttendees = Integer.parseInt(locationInfo[2]);
                Location location = new OnlineLocation(name, link, maxAttendees);
                locations.add(location);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Input file does not exist");
        }
        catch(IOException e) {
            System.out.println("Failed to read from file");
        }
        return locations;
    }

}
