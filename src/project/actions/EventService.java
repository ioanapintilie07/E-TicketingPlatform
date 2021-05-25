package project.actions;

import project.DB.ClientRepository;
import project.clientsAndPurchases.Client;
import project.clientsAndPurchases.Donation;
import project.clientsAndPurchases.Ticket;
import project.events.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EventService {
    static ArrayList<Event> events = new ArrayList<Event>();

    public void eventMenu() {
        System.out.println("What kind of event would you like to create?");
        System.out.println("1. Doesn't matter");
        System.out.println("2. Concert");
        System.out.println("3. Conference");
        System.out.println("4. Exhibition");
        System.out.println("5. Fundraiser");
        System.out.println("6. Play");
    }

    public void listEvents() {
        if (events.size() == 0) {
            System.out.println("No events to show. \n");
        } else {
            for (Event event : events) {
                System.out.println(event);
            }
        }
    }

    public Event searchEvent(int id) {
        Event newEvent = null;
        for (Event event : events) {
            if (event.getEventId() == id) {
                newEvent = event;
            }
        }
        return newEvent;
    }

    public void addEvent() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Date: ");
        String date = scanner.next();
        System.out.println("Description: ");
        String description = scanner.next();
        System.out.println("Duration: ");
        int duration = scanner.nextInt();
        System.out.println("Please enter the id of your location");
        int lId = scanner.nextInt();
        System.out.println("Participants limit: ");
        int limit = scanner.nextInt();

        eventMenu();
        int option = scanner.nextInt();
        switch (option) {
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


    public void generateEvent() {
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
        int locationId = rand.nextInt(11) + 1;
        int participantsLimit = rand.nextInt(1000);
        int target = rand.nextInt(5000000);
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        eventMenu();
        int option = scanner.nextInt();
        switch (option) {
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


    public void checkEventAvailability() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        TicketService ticketService = new TicketService();
        System.out.println("Enter the id of the event you want to check out: ");
        int id = scanner.nextInt();
        Event event = searchEvent(id);
        if (event == null) {
            System.out.println("Sorry, couldn't find the event you're looking for.");
        } else {
            ticketService.checkAvailability(event);
        }
    }

    public void buyTicket() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        TicketService ticketService = new TicketService();
        ClientRepository clientRepository = new ClientRepository();
        System.out.println("Enter the id of the event you want to attend: ");
        int eventId = scanner.nextInt();
        System.out.println("Enter your client id: ");
        int clientId = scanner.nextInt();
        System.out.println("How much are you paying for this ticket?");
        double price = scanner.nextDouble();
        Event event = searchEvent(eventId);
        Client client = clientRepository.findClient(clientId);

        if (event == null) {
            System.out.println("Sorry, couldn't find the event you're looking for.");
        } else if (client == null) {
            System.out.println("Sorry, there is no client with this id.");
        } else {
            Ticket ticket = new Ticket(event, client, price);
            boolean value = ticketService.addTicket(event, ticket);
            if (value) {
                System.out.println("Purchase successful!");
            }
        }
    }

    //FUNDRAISER SPECIFIC

    public Event searchFundraiser(int id) {
        Event newEvent = null;
        for (Event event : events) {
            if (event instanceof Fundraiser && event.getEventId() == id) {
                newEvent = event;
            }
        }
        return newEvent;
    }

    public void makeDonation() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        ClientRepository clientRepository = new ClientRepository();
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        } else {
            System.out.println("Enter your client id:");
            int cid = scanner.nextInt();
            Client client = clientRepository.findClient(cid);
            if (client == null) {
                System.out.println("Sorry, there is no client with this id.");
            } else {
                System.out.println("How much would you like to donate?");
                int don = scanner.nextInt();
                Donation donation = new Donation((Fundraiser) fund, client, don);
                ((Fundraiser) fund).addDonation(donation);
                System.out.println("Donation successful!");
            }
        }
    }

    public void showSum() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        } else {
            int sum = ((Fundraiser) fund).totalDonations();
            System.out.println("This fundraiser raised: " + sum);
        }
    }

    public void checkReached() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        } else {
            boolean reached = ((Fundraiser) fund).reachedTarget();
            if (reached) {
                System.out.println("Looking good! This fundraiser reached their goal.");
            } else {
                System.out.println("This fundraiser didn't reach their goal, but maybe you can help :)");
            }
        }
    }


}
