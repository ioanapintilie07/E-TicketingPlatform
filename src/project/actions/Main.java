package project.actions;

import project.DB.SetUpData;

import java.util.Scanner;

public class Main {
    public static void ShowMenu() {
        System.out.println("\n1. Show a list of existing events");
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
        System.out.println("14. Check if a fundraiser reached their goal");
        System.out.println("15. Update your first name");
        System.out.println("16. Delete a client");
        System.out.println("17. Update a location's name");
        System.out.println("18. Delete a location");
        System.out.println("19. Search for a location");
        System.out.println("20. Show a list of concerts in our database");
        System.out.println("21. Search concerts in database by performer");
        System.out.println("22. Update a concert's number of available tickets");
        System.out.println("23. Delete a concert from database");
        System.out.println("24. Add a concert to the database (randomly generated");
        System.out.println("25. Show audit");
        System.out.println("26. Reset audit");
        System.out.println("0. Exit menu\n");
    }

    public static void main(String[] args) {
        int opt = -1;
        Scanner scanner = new Scanner(System.in);
        SetUpData setupData = new SetUpData();
        setupData.setup();

        Service service = new Service();
        Reader reader = Reader.getInstance();
        Writer writer = Writer.getInstance();
        AuditService auditService = AuditService.getInstance();

        Service.events.addAll(reader.readEventsCSV());

        /*
        //load data from csv files
        Service.clients.addAll(reader.readClientsCSV());
        Service.locations.addAll(reader.readPhysicalLocationsCSV());
        Service.locations.addAll(reader.readOnlineLocationsCSV());
        */

        while (opt != 0) {
            ShowMenu();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    service.listEvents();
                    auditService.add(1);
                    break;
                case 2:
                    service.addEvent();
                    auditService.add(2);
                    break;
                case 3:
                    service.generateEvent();
                    auditService.add(3);
                    break;
                case 4:
                    service.listLocations();
                    auditService.add(4);
                    break;
                case 5:
                    service.addLocation();
                    auditService.add(5);
                    break;
                case 6:
                    service.generateLocation();
                    auditService.add(6);
                    break;
                case 7:
                    service.listClients();
                    auditService.add(7);
                    break;
                case 8:
                    service.addClient();
                    auditService.add(8);
                    break;
                case 9:
                    service.generateClient();
                    auditService.add(9);
                    break;
                case 10:
                    service.checkEventAvailability();
                    auditService.add(10);
                    break;
                case 11:
                    service.buyTicket();
                    auditService.add(11);
                    break;
                case 12:
                    service.makeDonation();
                    auditService.add(12);
                    break;
                case 13:
                    service.showSum();
                    auditService.add(13);
                    break;
                case 14:
                    service.checkReached();
                    auditService.add(14);
                    break;
                case 15:
                    service.updateFirstName();
                    auditService.add(15);
                    break;
                case 16:
                    service.deleteClient();
                    auditService.add(16);
                    break;
                case 17:
                    service.updateLocation();
                    auditService.add(17);
                    break;
                case 18:
                    service.deleteLocation();
                    auditService.add(18);
                    break;
                case 19:
                    service.searchLocation();
                    auditService.add(19);
                    break;
                case 20:
                    service.listConcerts();
                    auditService.add(20);
                    break;
                case 21:
                    service.lookForConcert();
                    auditService.add(21);
                    break;
                case 22:
                    service.updateConcertTickets();
                    auditService.add(22);
                    break;
                case 23:
                    service.deleteConcert();
                    auditService.add(23);
                    break;
                case 24:
                    service.addConcertDB();
                    auditService.add(24);
                    break;
                case 25:
                    auditService.show();
                    break;
                case 26:
                    auditService.clear();
                    break;
            }
        }

        writer.writeEvents(Service.events);

        /*
        //write data to csv files
        writer.writeClients(Service.clients);
        writer.writePhysicalLocations(Service.locations);
        writer.writeOnlineLocations(Service.locations);*/
    }

}
