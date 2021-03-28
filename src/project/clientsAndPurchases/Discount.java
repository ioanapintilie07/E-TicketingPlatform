package project.clientsAndPurchases;

public class Discount {
    private Client client;
    private String reason;
    private Boolean isUsed;

    public Discount(Client client, String reason, Boolean isUsed) {
        this.client = client;
        this.reason = reason;
        this.isUsed = isUsed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }
}
