package project.DB;

import project.locations.Location;
import project.locations.OnlineLocation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnlineLocationRepository {

    public OnlineLocation createOnlineLocation (OnlineLocation onlineLocation) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO onlineLocations(locationName, accessLink, maxAttendees) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, onlineLocation.getName());
            preparedStatement.setString(2, onlineLocation.getAccessLink());
            preparedStatement.setInt(3, onlineLocation.getMaxAttendees());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                onlineLocation.setLocationId(resultSet.getInt(1));
            }
            resultSet.close();
            return onlineLocation;
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to create " + onlineLocation + exception.getMessage());
        }
    }

    public List<Location> findAllOnlineLocations() {
        List<Location> locations = new ArrayList<>();
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM onlineLocations";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                locations.add(mapToOnlineLocation(resultSet));
            }
            resultSet.close();
            return locations;

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public OnlineLocation findOnlineLocation(int locationId) {
        OnlineLocation onlineLocation = null;
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM onlineLocations WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, locationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                onlineLocation = new OnlineLocation(resultSet.getString("locationName"), resultSet.getString("accessLink"),resultSet.getInt("maxAttendees"));
                onlineLocation.setLocationId(resultSet.getInt("id"));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to find location" + locationId + exception.getMessage());
        }
        return onlineLocation;
    }

    public int deleteOnlineLocation(int locationId) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM onlineLocations WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, locationId);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public int updateOnlineLocation(int locationId, String locationName) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "UPDATE onlineLocations SET locationName = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, locationName);
            preparedStatement.setInt(2, locationId);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to update location" + locationId + exception.getMessage());
        }
    }

    private Location mapToOnlineLocation(ResultSet resultSet) throws SQLException {
        Location onlineLocation = new OnlineLocation(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
        onlineLocation.setLocationId(resultSet.getInt(1));
        return onlineLocation;
    }
}
