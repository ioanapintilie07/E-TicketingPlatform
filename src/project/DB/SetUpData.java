package project.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpData {
    public void setup(){
        createTableFundraisers();
        createTableClients();
        createTablePhysicalLocations();
        createTableOnlineLocations();
        createTableAudit();
    }

    private void createTableFundraisers() {
        String query = "CREATE TABLE IF NOT EXISTS concerts(\n" +
                "    id INT PRIMARY KEY,\n" +
                "    eventName VARCHAR(255),\n" +
                "    eventDate VARCHAR(255),\n" +
                "    description VARCHAR(500), \n" +
                "    durationInHours INT, \n" +
                "    locationId INT, \n" +
                "    participantsLimit INT, \n" +
                "    confirmedParticipations INT, \n" +
                "    soldOut BIT, \n" +
                "    musician VARCHAR(255), \n" +
                "    genre VARCHAR(255) \n" +
                ")";

        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableClients() {
        String query = "CREATE TABLE IF NOT EXISTS clients (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    firstName VARCHAR(255),\n" +
                "    lastName VARCHAR(255),\n" +
                "    age INT \n" +
                ")";

        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTablePhysicalLocations() {
        String query = "CREATE TABLE IF NOT EXISTS physicalLocations (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    locationName VARCHAR(255), \n" +
                "    city VARCHAR(255),\n" +
                "    address VARCHAR(300), \n" +
                "    capacity INT \n" +
                ")";

        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableOnlineLocations() {
        String query = "CREATE TABLE IF NOT EXISTS onlineLocations (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    locationName VARCHAR(255), \n" +
                "    accessLink VARCHAR(255),\n" +
                "    maxAttendees INT \n" +
                ")";

        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableAudit() {
        String query = "CREATE TABLE IF NOT EXISTS audit (\n" +
                "    id INT PRIMARY KEY AUTO_INCREMENT,\n" +
                "    message VARCHAR(255), \n" +
                "    timeOfAction VARCHAR(255)\n" +
                ")";

        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
