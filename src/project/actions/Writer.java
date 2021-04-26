package project.actions;

import project.clientsAndPurchases.Client;
import project.events.Event;
import project.locations.Location;
import project.locations.OnlineLocation;
import project.locations.PhysicalLocation;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Writer {
    private static Writer instance = null;

    private Writer() {

    }

    public static Writer getInstance() {
        if (instance == null) {
            instance = new Writer();
        }
        return instance;
    }

    public void writeClients(ArrayList<Client> clients) {
        if (clients != null && clients.size() > 0) {
            String path = "./src/project/files/write/clients.csv";
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(path))) {
                for (Client client : clients) {
                    fout.write(client.getFirstName() + "," + client.getLastName() + "," + client.getAge() + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found");
            } catch (IOException e) {
                System.out.println("Failed to write in file");
            }
        }
    }

    public void writeEvents(ArrayList<Event> events) {
        if (events != null && events.size() > 0) {
            String path = "./src/project/files/write/events.csv";
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(path))) {
                for (Event event : events) {
                    fout.write(event.getEventId() + "," + event.getName() + "," + event.getDate() + "," + event.getDescription()
                            + "," + event.getDurationInHours() + "," + event.getLocationId() + "," + event.getParticipantsLimit()
                            + "," + event.getParticipantsLimit() + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found");
            } catch (IOException e) {
                System.out.println("Failed to write in file");
            }
        }

    }

    public void writePhysicalLocations(ArrayList<Location> locations) {
        if (locations != null && locations.size() > 0) {
            String path = "./src/project/files/write/physicalLocations.csv";
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(path))) {
                for (Location location : locations) {
                    if (location instanceof PhysicalLocation) {
                        fout.write(location.getLocationId() + "," + location.getName() + "," +
                                ((PhysicalLocation) location).getCity() + "," + ((PhysicalLocation) location).getAddress()
                                + "," + ((PhysicalLocation) location).getCapacity() + "\n");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found");
            } catch (IOException e) {
                System.out.println("Failed to write in file");
            }
        }
    }

    public void writeOnlineLocations(ArrayList<Location> locations) {
        if (locations != null && locations.size() > 0) {
            String path = "./src/project/files/write/onlineLocations.csv";
            try (BufferedWriter fout = new BufferedWriter(new FileWriter(path))) {
                for (Location location : locations) {
                    if (location instanceof OnlineLocation) {
                        fout.write(location.getLocationId() + "," + location.getName() + "," +
                                ((OnlineLocation) location).getAccessLink() + "," + ((OnlineLocation) location).getMaxAttendees()
                                + "\n");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found");
            } catch (IOException e) {
                System.out.println("Failed to write in file");
            }
        }
    }
}
