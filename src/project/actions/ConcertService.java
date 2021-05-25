package project.actions;

import project.DB.ConcertRepository;
import project.events.Concert;
import project.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConcertService {
    static ConcertRepository concertRepository= new ConcertRepository();

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
