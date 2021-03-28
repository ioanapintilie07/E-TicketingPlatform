package project.actions;

import java.util.Scanner;

public class Main {
    public static void ShowMenu() {
        System.out.println("1. Show a list of existing events");
        System.out.println("2. Add an event");
        System.out.println("3. Show a list of existing locations");
        System.out.println("4. Add a location");
        System.out.println("5. Show a list of clients, ordered alphabetically");
        System.out.println("6. Add a client/Sign up");
        System.out.println("7. Check the availability of an event");
        System.out.println("8. Buy a ticket to an event");
        System.out.println("9. Make a donation to a fundraiser");
        System.out.println("10. Show how much money a fundraiser raised");
        System.out.println("11. Check if a fundraiser reached their goal.");
        System.out.println("0. Exit menu");
    }

    public static void main(String[] args) {
        int opt = -1;
        Scanner scanner = new Scanner(System.in);
        while (opt != 0) {
            ShowMenu();
            opt = scanner.nextInt();
            switch (opt) {
                case 1:
                    Service.listEvents();
                    break;
                case 2:
                    Service.addEvent();
                    break;
                case 3:
                    Service.listLocations();
                    break;
                case 4:
                    Service.addLocation();
                    break;
                case 5:
                    Service.listClients();
                    break;
                case 6:
                    Service.addClient();
                    break;
                case 7:
                    Service.checkEventAvailability();
                    break;
                case 8:
                    Service.buyTicket();
                    break;
                case 9:
                    Service.makeDonation();
                    break;
                case 10:
                    Service.showSum();
                    break;
                case 11:
                    Service.checkReached();
                    break;
            }
        }
    }

}
