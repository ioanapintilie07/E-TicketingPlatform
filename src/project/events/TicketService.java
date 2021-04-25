package project.events;

import project.clientsAndPurchases.Ticket;

public class TicketService {
    public void checkAvailability(Event event) {
        if (event == null) {
            System.out.println("Invalid event id.");
        } else if (event.soldOut) {
            System.out.println("Looks like this event is sold out!");
        } else {
            int remaining = event.participantsLimit - event.confirmedParticipations;
            if (remaining == 1) {
                System.out.println("Hurry, only one ticket left!");
            } else {
                System.out.println("There are " + remaining + " tickets left for this event!");
            }
        }
    }

    public boolean addTicket(Event event, Ticket ticket) {
        if (ticket == null) {
            System.out.println("Invalid request");
            return false;
        } else {
            if (event.soldOut) {
                System.out.println("Sorry, this event is sold out.");
                return false;
            } else {
                if (event.participantsLimit == event.confirmedParticipations + 1) {
                    event.soldOut = true;
                }
                event.confirmedParticipations++;
                event.tickets.add(ticket);
                return true;
            }
        }
    }
}
