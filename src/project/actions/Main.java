package project.actions;

import java.util.Scanner;

public class Main {
    public static void ShowMenu() {
        System.out.println("1. Show a list of existing events");
        System.out.println("2. Add an event");
        System.out.println("3. Generate an event");
        System.out.println("4. Show a list of existing locations");
        System.out.println("5. Add a location");
        System.out.println("6. Generate a location");
        System.out.println("7. Show a list of clients, ordered alphabetically");
        System.out.println("8. Add a client/Sign up");
        System.out.println("9. Generate a client/Sign up");
        System.out.println("10. Check the availability of an event");
        System.out.println("11. Buy a ticket to an event");
        System.out.println("12. Make a donation to a fundraiser");
        System.out.println("13. Show how much money a fundraiser raised");
        System.out.println("14. Check if a fundraiser reached their goal.");
        System.out.println("0. Exit menu");
    }

    public static void main(String[] args) {
        int opt = -1;
        Scanner scanner = new Scanner(System.in);

        Service service = new Service();
        Reader reader = Reader.getInstance();
        Writer writer = Writer.getInstance();

        //load data from csv files
        Service.clients.addAll(reader.readClientsCSV());
        Service.locations.addAll(reader.readPhysicalLocationsCSV());
        Service.locations.addAll(reader.readOnlineLocationsCSV());
        Service.events.addAll(reader.readEventsCSV());
        while (opt != 0) {
            ShowMenu();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    service.listEvents();
                    break;
                case 2:
                    service.addEvent();
                    break;
                case 3:
                    service.generateEvent();
                    break;
                case 4:
                    service.listLocations();
                    break;
                case 5:
                    service.addLocation();
                    break;
                case 6:
                    service.generateLocation();
                    break;
                case 7:
                    service.listClients();
                    break;
                case 8:
                    service.addClient();
                    break;
                case 9:
                    service.generateClient();
                    break;
                case 10:
                    service.checkEventAvailability();
                    break;
                case 11:
                    service.buyTicket();
                    break;
                case 12:
                    service.makeDonation();
                    break;
                case 13:
                    service.showSum();
                    break;
                case 14:
                    service.checkReached();
                    break;
            }
        }
        //write data to csv files
        writer.writeClients(Service.clients);
        writer.writeEvents(Service.events);
        writer.writePhysicalLocations(Service.locations);
        writer.writeOnlineLocations(Service.locations);
    }

}
