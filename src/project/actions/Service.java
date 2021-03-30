package project.actions;


import jdk.swing.interop.SwingInterOpUtils;
import project.clientsAndPurchases.Client;
import project.clientsAndPurchases.Donation;
import project.clientsAndPurchases.Ticket;
import project.events.*;
import project.locations.Location;
import project.locations.OnlineLocation;
import project.locations.PhysicalLocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

public class Service {
    static ArrayList<Event> events = new ArrayList<Event>();
    static ArrayList<Client> clients = new ArrayList<Client>();
    static ArrayList<Location> locations = new ArrayList<Location>();

    public static void eventMenu() {
        System.out.println("What kind of event would you like to create?");
        System.out.println("1. Doesn't matter");
        System.out.println("2. Concert");
        System.out.println("3. Conference");
        System.out.println("4. Exhibition");
        System.out.println("5. Fundraiser");
        System.out.println("6. Play");
    }

    public static void locationMenu() {
        System.out.println("What kind of location would you like to create?");
        System.out.println("1. Doesn't matter");
        System.out.println("2. Online location");
        System.out.println("3. Physical location");
    }

    public static void listEvents() {
        if (events.size() == 0) {
            System.out.println("No events to show. \n");
        }
        else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    public static void listLocations() {
        if (locations.size() == 0) {
            System.out.println("No locations to show. \n");
        }
        else {
            for (Location location : locations) {
                System.out.println(location);
            }
        }
    }

    public static void listClients() {
        if (clients.size() == 0) {
            System.out.println("No clients to show!");
        }
        else {
            Collections.sort(clients);
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }


    public static Event searchEvent (int id) {
        Event newEvent = null;
        for (Event event:events) {
            if (event.getEventId() == id) {
                newEvent = event;
            }
        }
        return newEvent;
    }

    public static Client searchClient (int id) {
        Client newClient = null;
        for (Client client:clients) {
            if (client.getClientId() == id) {
                newClient = client;
            }
        }
        return newClient;
    }

    public static Event searchFundraiser(int id) {
        Event newEvent = null;
        for (Event event:events) {
            if (event instanceof Fundraiser && event.getEventId() == id) {
                newEvent = event;
            }
        }
        return newEvent;
    }

    public static boolean validateLocation(int lid) {
        return lid >= 1 && lid <= locations.size();
    }

    public static void addEvent() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        if (locations.size() == 0) {
            System.out.println("There are no registered locations. Please add a location before adding an event.");
        }
        else {
            System.out.println("Name: ");
            String name = scanner.next();
            System.out.println("Date: ");
            String date = scanner.next();
            System.out.println("Description: ");
            String description = scanner.next();
            System.out.println("Duration: ");
            int duration = scanner.nextInt();
            System.out.println("Please enter the id of your location. Locations available: 1 -" + locations.size());
            int lId = scanner.nextInt();
            while (!validateLocation(lId)) {
                System.out.println("Sorry, that's not a valid location id.");
                lId = scanner.nextInt();
            }
            System.out.println("Participants limit: ");
            int limit = scanner.nextInt();

            eventMenu();
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    Event event = new Event(name, date, description, duration, lId, limit);
                    events.add(event);
                    break;
                case 2:
                    System.out.println("Musician: ");
                    String musician = scanner.next();
                    System.out.println("Genre: ");
                    String genre = scanner.next();
                    Event concert = new Concert(name, date, description, duration, lId, limit, musician, genre);
                    events.add(concert);
                    break;
                case 3:
                    System.out.println("Speaker: ");
                    String speaker = scanner.next();
                    System.out.println("Topic: ");
                    String topic = scanner.next();
                    Event conference = new Conference(name, date, description, duration, lId, limit, speaker, topic);
                    events.add(conference);
                    break;
                case 4:
                    System.out.println("Main artist: ");
                    String artist = scanner.next();
                    System.out.println("Theme: ");
                    String theme = scanner.next();
                    Event exhibition = new Exhibition(name, date, description, duration, lId, limit, artist, theme);
                    events.add(exhibition);
                    break;
                case 5:
                    System.out.println("Organized by: ");
                    String org = scanner.next();
                    System.out.println("Cause: ");
                    String cause = scanner.next();
                    System.out.println("Target: ");
                    int target = scanner.nextInt();
                    Event fundraiser = new Fundraiser(name, date, description, duration, lId, limit, cause, org, target);
                    events.add(fundraiser);
                    break;
                case 6:
                    System.out.println("Main actor: ");
                    String actor = scanner.next();
                    System.out.println("Genre:");
                    String gen = scanner.next();
                    Event play = new Play(name, date, description, duration, lId, limit, actor, gen);
                    events.add(play);
                    break;
            }
            System.out.println("\nEvent added successfully!\n");
        }
    }

    public static void addLocation() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter location name: ");
        String name = scanner.next();
        locationMenu();
        int option = scanner.nextInt();
        switch(option) {
            case 1:
                Location location = new Location(name);
                locations.add(location);
                break;
            case 2:
                System.out.println("Access link: ");
                String link = scanner.next();
                System.out.println("Max number of attendees: ");
                int maxi = scanner.nextInt();
                Location onlineLocation = new OnlineLocation(name, link, maxi);
                locations.add(onlineLocation);
                break;
            case 3:
                System.out.println("City: ");
                String city = scanner.next();
                System.out.println("Address: ");
                String add = scanner.next();
                System.out.println("Capacity: ");
                int capacity = scanner.nextInt();
                Location physLocation = new PhysicalLocation(name, city, add, capacity);
                locations.add(physLocation);
                break;
        }
        System.out.println("\nLocation added successfully!\n");
    }

    public static void addClient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("First name: ");
        String first = scanner.next();
        System.out.println("Last name: ");
        String last = scanner.next();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        Client client = new Client(first, last, age);
        clients.add(client);
        System.out.println("Client added successfully!");
    }

    public static void generateEvent() {
        if (locations.size() == 0) {
            System.out.println("There are no registered locations. Please add a location before adding an event.");
        }
        else {
            String[] names = {"Night Out", "The Colors Show", "Let's talk about things!", "Amazing Skies", "Look Beyond"};
            String[] dates = {"1/04/2021", "5/04/2021", "9/04/2021", "1/07/2021", "10/07/2021"};
            String[] descriptions = {"Check out this cool event", "Come have a great time", "We're waiting for you",
                    "Buy a ticket, buy an experience", "Makes you want to come back every time"};
            String[] musicians = {"Halsey", "The New York Philharmonic", "Gorillaz", "Nothing But Thieves", "Beyonce"};
            String[] musicGenres = {"pop", "rock", "classical", "indie", "alternative"};
            String[] speakers = {"John Johnson", "The Guy Next Door", "Mara Wilson", "Interesting Man In Bowtie"};
            String[] topics = {"climate change", "the rise of cryptocurrency", "academia in the 21st century", "the importance of a public image",
                         "the addictiveness of social media"};
            String[] artists = {"Vincent van Gogh", "Claude Monet", "Pablo Picasso", "Rembrant", "Vermeer"};
            String[] themes = {"Renaissance pieces", "notable portraits", "mannerism", "Italian art through the centuries", "Baroque"};
            String[] causes = {"plant trees near our town", "help homeless families seek shelter", "pay for animal shelter medical bills",
                               "help highschool graduates go to college", "renovate the memorial in the town center"};
            String[] organizers = {"DARE NGO", "ASPIRE NGO", "DREAM NGO", "AMAZING NGO"};
            String[] actors = {"Talented Guy", "Actor Name", "Java John", "Talented Woman"};
            String[] playGenres = {"tragedy", "comedy", "drama", "musical theatre", "historical"};
            Random rand = new Random();
            String name = names[rand.nextInt(names.length)];
            String date = dates[rand.nextInt(dates.length)];
            String description = descriptions[rand.nextInt(descriptions.length)];
            String musician = musicians[rand.nextInt(musicians.length)];
            String musicGenre = musicGenres[rand.nextInt(musicGenres.length)];
            String speaker = speakers[rand.nextInt(speakers.length)];
            String topic = topics[rand.nextInt(topics.length)];
            String artist = artists[rand.nextInt(artists.length)];
            String theme = themes[rand.nextInt(themes.length)];
            String cause = causes[rand.nextInt(causes.length)];
            String organizer = organizers[rand.nextInt(organizers.length)];
            String actor = actors[rand.nextInt(actors.length)];
            String playGenre = playGenres[rand.nextInt(playGenres.length)];
            int durationInHours = rand.nextInt(25);
            int locationId = rand.nextInt(locations.size()) + 1;
            int participantsLimit = rand.nextInt(1000);
            int target = rand.nextInt(5000000);
            Scanner scanner = new Scanner(System.in).useDelimiter("\n");
            eventMenu();
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    Event event = new Event(name, date, description, durationInHours, locationId, participantsLimit);
                    events.add(event);
                    break;
                case 2:
                    Event concert = new Concert(name, date, description, durationInHours, locationId, participantsLimit, musician, musicGenre);
                    events.add(concert);
                    break;
                case 3:
                    Event conference = new Conference(name, date, description, durationInHours, locationId, participantsLimit, speaker, topic);
                    events.add(conference);
                    break;
                case 4:
                    Event exhibit = new Exhibition(name, date, description, durationInHours, locationId, participantsLimit, artist, theme);
                    events.add(exhibit);
                    break;
                case 5:
                    Event fundraiser = new Fundraiser(name, date, description, durationInHours, locationId, participantsLimit, cause, organizer, target);
                    events.add(fundraiser);
                    break;
                case 6:
                    Event play = new Play(name, date, description, durationInHours, locationId, participantsLimit, actor, playGenre);
                    events.add(play);
                    break;
            }
            System.out.println("\nEvent added successfully!\n");
        }

    }

    public static void generateLocation() {
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
        switch(opt) {
            case 1:
                Location location = new Location(name);
                locations.add(location);
                break;
            case 2:
                Location online = new OnlineLocation(platform, link, attendees);
                locations.add(online);
                break;
            case 3:
                Location phys = new PhysicalLocation(name, city, address, capacity);
                locations.add(phys);
                break;
        }
        System.out.println("\nLocation added succcessfully!\n");
    }

    public static void generateClient() {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String[] firstNames = {"Anna", "Petunia", "Harold", "George", "Sam"};
        String[] lastNames = {"Peterson", "McLeroy", "Twig", "Freshers", "Gardner"};
        String firstName = firstNames[rand.nextInt(firstNames.length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        int age = rand.nextInt(60) + 10;
        Client client = new Client(firstName, lastName, age);
        clients.add(client);
        System.out.println("\nClient added successfully!\n");
    }

    public static void checkEventAvailability() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter the id of the event you want to check out: ");
        int id = scanner.nextInt();
        Event event = searchEvent(id);
        if (event == null) {
            System.out.println("Sorry, couldn't find the event you're looking for.");
        }
        else {
            event.checkAvailability();
        }
    }

    public static void buyTicket() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter the id of the event you want to attend: ");
        int eventId= scanner.nextInt();
        System.out.println("Enter your client id: ");
        int clientId = scanner.nextInt();
        System.out.println("How much are you paying for this ticket?");
        double price = scanner.nextDouble();
        Event event = searchEvent(eventId);
        Client client = searchClient(clientId);

        if (event == null) {
            System.out.println("Sorry, couldn't find the event you're looking for.");
        }
        else if (client == null) {
            System.out.println("Sorry, there is no client with this id.");
        }
        else {
            Ticket ticket = new Ticket(event, client, price);
            boolean value = event.addTicket(ticket);
            if (value) {System.out.println("Purchase successful!");};
        }

    }

    public static void makeDonation() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        }
        else {
            System.out.println("Enter your client id:");
            int cid = scanner.nextInt();
            Client client = searchClient(cid);
            if (client == null) {
                System.out.println("Sorry, there is no client with this id.");
            }
            else {
                System.out.println("How much would you like to donate?");
                int don = scanner.nextInt();
                Donation donation = new Donation((Fundraiser)fund, client, don);
                ((Fundraiser) fund).addDonation(donation);
                System.out.println("Donation successful!");
            }
        }
    }

    public static void showSum() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        }
        else {
            int sum = ((Fundraiser)fund).totalDonations();
            System.out.println("This fundraiser raised: " + sum);
        }
    }

    public static void checkReached() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        }
        else{
            boolean reached = ((Fundraiser)fund).reachedTarget();
            if (reached) {
                System.out.println("Looking good! This fundraiser reached their goal.");
            }
            else {
                System.out.println("This fundraiser didn't reach their goal, but maybe you can help :)");
            }
        }
    }
}
