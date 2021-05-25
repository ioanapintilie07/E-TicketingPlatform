package project.actions;


import jdk.swing.interop.SwingInterOpUtils;
import project.DB.ClientRepository;
import project.DB.ConcertRepository;
import project.DB.OnlineLocationRepository;
import project.DB.PhysicalLocationRepository;
import project.clientsAndPurchases.Client;
import project.clientsAndPurchases.Donation;
import project.clientsAndPurchases.Ticket;
import project.events.*;
import project.locations.Location;
import project.locations.OnlineLocation;
import project.locations.PhysicalLocation;

import java.util.*;

public class Service {
    static ArrayList<Event> events = new ArrayList<Event>();
    static ClientRepository clientRepository = new ClientRepository();
    static OnlineLocationRepository onlineLocationRepository = new OnlineLocationRepository();
    static PhysicalLocationRepository physicalLocationRepository = new PhysicalLocationRepository();
    static ConcertRepository concertRepository= new ConcertRepository();

    //LOCATIONS

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

    // CLIENTS
    public void listClients() {
        List<Client> clients = new ArrayList<>(clientRepository.findAllClients());
        if (clients.size() == 0) {
            System.out.println("No clients to show.\n");
        } else {
            Collections.sort(clients);
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    public Client searchClient(int id) {
        return clientRepository.findClient(id);
    }

    public void addClient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("First name: ");
        String first = scanner.next();
        System.out.println("Last name: ");
        String last = scanner.next();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        Client client = new Client(first, last, age);
        clientRepository.createClient(client);
        System.out.println("Client added successfully!");
    }

    public void generateClient() {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String[] firstNames = {"Anna", "Petunia", "Harold", "George", "Sam"};
        String[] lastNames = {"Peterson", "McLeroy", "Twig", "Freshers", "Gardner"};
        String firstName = firstNames[rand.nextInt(firstNames.length)];
        String lastName = lastNames[rand.nextInt(lastNames.length)];
        int age = rand.nextInt(60) + 10;
        Client client = new Client(firstName, lastName, age);
        clientRepository.createClient(client);
        System.out.println("\nClient added successfully!\n");
    }

    public void updateFirstName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Please enter your client id:");
        int id = scanner.nextInt();
        System.out.println("How would you like to be called?");
        String firstName = scanner.next();
        int updated = clientRepository.updateClient(id, firstName);
        if (updated > 0) {
            System.out.println("\nName updated successfully!\n");
        } else {
            System.out.println("\nSorry, there is no client with this id\n");
        }
    }

    public void deleteClient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Please enter your client id:");
        int id = scanner.nextInt();
        int deleted = clientRepository.deleteClient(id);
        if (deleted > 0) {
            System.out.println("\nClient deleted successfully!\n");
        } else {
            System.out.println("\nSorry, there is no client with this id\n");
        }
    }

    //EVENTS

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
        System.out.println("Enter the id of the event you want to attend: ");
        int eventId = scanner.nextInt();
        System.out.println("Enter your client id: ");
        int clientId = scanner.nextInt();
        System.out.println("How much are you paying for this ticket?");
        double price = scanner.nextDouble();
        Event event = searchEvent(eventId);
        Client client = searchClient(clientId);

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
        System.out.println("Enter fundraiser id: ");
        int id = scanner.nextInt();
        Event fund = searchFundraiser(id);
        if (fund == null) {
            System.out.println("Sorry, couldn't find the fundraiser you were looking for.");
        } else {
            System.out.println("Enter your client id:");
            int cid = scanner.nextInt();
            Client client = searchClient(cid);
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

    //CONCERT SPECIFIC

    public void listConcerts() {
        List<Event> concerts = new ArrayList<>(concertRepository.findAllConcerts());
        if (concerts.size() == 0) {
            System.out.println("Sorry, there are no concerts registered in the database");
        }
        else {
            for (Event concert : concerts) {
                System.out.println(concert);
                System.out.println("Tickets: " + concert.getParticipantsLimit() + "\n");
            }
        }
    }

    public void updateConcertTickets() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Please enter the concert id:");
        int id = scanner.nextInt();
        System.out.println("Updated number of max tickets:");
        int newParticipantsLimit = scanner.nextInt();
        int updated = concertRepository.updateConcert(id, newParticipantsLimit);
        if (updated == 0) {
            System.out.println("Sorry, there is no concert with this id in the database");
        }
        else {
            System.out.println("\nConcert updated successfully!\n");
        }
    }

    public void lookForConcert() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("What's the name of the artist you're interested in?");
        String musician = scanner.next();
        List<Event> concerts = new ArrayList<>(concertRepository.findConcert(musician));
        if (concerts.size() == 0) {
            System.out.println("Sorry, this artist has no upcoming concerts.");
        }
        else {
            System.out.println("Check these out!");
            for (Event concert : concerts) {
                System.out.println(concert);
            }
        }
    }

    public void deleteConcert() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Please enter the concert's id:");
        int id = scanner.nextInt();
        int deleted = concertRepository.deleteConcert(id);
        if (deleted == 0) {
            System.out.println("Sorry, couldn't find the concert you were looking for");
        }
        else {
            System.out.println("\nConcert deleted successfully!\n");
        }
    }

    public void addConcertDB() {
        Random rand = new Random();
        String[] names = {"Night Out", "The Colors Show", "Let's talk about things!", "Amazing Skies", "Look Beyond"};
        String[] dates = {"1/04/2021", "5/04/2021", "9/04/2021", "1/07/2021", "10/07/2021"};
        String[] descriptions = {"Check out this cool event", "Come have a great time", "We're waiting for you",
                "Buy a ticket, buy an experience", "Makes you want to come back every time"};
        String[] musicians = {"Halsey", "The New York Philharmonic", "Gorillaz", "Nothing But Thieves", "Beyonce"};
        String[] musicGenres = {"pop", "rock", "classical", "indie", "alternative"};
        String name = names[rand.nextInt(names.length)];
        String date = dates[rand.nextInt(dates.length)];
        String description = descriptions[rand.nextInt(descriptions.length)];
        String musician = musicians[rand.nextInt(musicians.length)];
        String musicGenre = musicGenres[rand.nextInt(musicGenres.length)];
        int durationInHours = rand.nextInt(25);
        int locationId = rand.nextInt(11);
        int participantsLimit = rand.nextInt(1000);
        Event concert = new Concert(name, date, description, durationInHours, locationId, participantsLimit, musician, musicGenre);
        concertRepository.createConcert(concert);
        System.out.println("\nConcert added successfully!\n");
    }
}
