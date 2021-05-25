package project.actions;

import project.DB.ClientRepository;
import project.clientsAndPurchases.Client;

import java.util.*;

public class ClientService {
    static ClientRepository clientRepository = new ClientRepository();

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


}
