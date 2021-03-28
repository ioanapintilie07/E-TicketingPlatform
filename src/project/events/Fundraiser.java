package project.events;

import project.clientsAndPurchases.Donation;
import project.locations.Location;

import java.util.ArrayList;

public class Fundraiser extends Event{
    private String cause;
    private String organizer;
    private int targetSum;
    private ArrayList<Donation> donations = new ArrayList<Donation>();


    public Fundraiser(String name, String date, String description, int durationInHours, int locationId, int participantsLimit, String cause, String organizer, int targetSum) {
        super(name, date, description, durationInHours, locationId, participantsLimit);
        this.cause = cause;
        this.organizer = organizer;
        this.targetSum = targetSum;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public int getTargetSum() {
        return targetSum;
    }

    public void setTargetSum(int targetSum) {
        this.targetSum = targetSum;
    }

    public void addDonation (Donation donation) {
        if (donation != null) {
            donations.add(donation);
        }
    }

    public int totalDonations () {
        int sum = 0;
        for (Donation donation:donations) {
            sum += donation.getDonation();
        }
        return sum;
    }

    public boolean reachedTarget () {
        int sum = totalDonations();
        return sum >= targetSum;
    }

    @Override
    public String toString() {
        return super.toString() + "Fundraiser organized by " + organizer + ". "  + "Raising for: " + cause  + ". Target: " + targetSum;
    }
}
