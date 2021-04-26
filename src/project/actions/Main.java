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
        Audit audit = Audit.getInstance();

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
                    audit.add(1);
                    break;
                case 2:
                    service.addEvent();
                    audit.add(2);
                    break;
                case 3:
                    service.generateEvent();
                    audit.add(3);
                    break;
                case 4:
                    service.listLocations();
                    audit.add(4);
                    break;
                case 5:
                    service.addLocation();
                    audit.add(5);
                    break;
                case 6:
                    service.generateLocation();
                    audit.add(6);
                    break;
                case 7:
                    service.listClients();
                    audit.add(7);
                    break;
                case 8:
                    service.addClient();
                    audit.add(8);
                    break;
                case 9:
                    service.generateClient();
                    audit.add(9);
                    break;
                case 10:
                    service.checkEventAvailability();
                    audit.add(10);
                    break;
                case 11:
                    service.buyTicket();
                    audit.add(11);
                    break;
                case 12:
                    service.makeDonation();
                    audit.add(12);
                    break;
                case 13:
                    service.showSum();
                    audit.add(13);
                    break;
                case 14:
                    service.checkReached();
                    audit.add(14);
                    break;
            }
        }
        //write data to csv files
        writer.writeClients(Service.clients);
        writer.writeEvents(Service.events);
        writer.writePhysicalLocations(Service.locations);
        writer.writeOnlineLocations(Service.locations);

        audit.close();
    }

}
