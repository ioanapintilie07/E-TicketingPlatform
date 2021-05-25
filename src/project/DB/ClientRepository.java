package project.DB;

import project.clientsAndPurchases.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    public Client createClient (Client client) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO clients(firstName, lastName, age) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                client.setClientId(resultSet.getInt(1));
            }
            resultSet.close();
            return client;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM clients";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                clients.add(mapToClient(resultSet));
            }
            resultSet.close();
            return clients;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while tying to get all clients ");
        }
    }

    public Client findClient(int clientId) {
        Client newClient = null;
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();            if (resultSet.next()) {
                newClient = new Client(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getInt("age"));
                newClient.setClientId(resultSet.getInt("id"));
            }
            resultSet.close();
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while searching for client " + clientId);
        }
        return newClient;
    }

    public int deleteClient(int clientId) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM clients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clientId);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while deleting client " + clientId);
        }
    }

    public int updateClient(int clientId, String firstName) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "UPDATE clients SET firstName = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, clientId);
            return preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Client mapToClient(ResultSet resultSet) throws SQLException {
        Client client = new Client(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        client.setClientId(resultSet.getInt(1));
        return client;
    }
}
