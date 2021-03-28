package project.clientsAndPurchases;

import project.events.Fundraiser;

public class Donation{
    private Fundraiser fundraiser;
    private Client client;
    private int donation;

    public Donation(Fundraiser fundraiser, Client client, int donation) {
        this.fundraiser = fundraiser;
        this.client = client;
        this.donation = donation;
    }

    public Fundraiser getFundraiser() {
        return fundraiser;
    }

    public void setFundraiser(Fundraiser fundraiser) {
        this.fundraiser = fundraiser;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getDonation() {
        return donation;
    }

    public void setDonation(int donation) {
        this.donation = donation;
    }
}
