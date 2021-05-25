package project.DB;

import project.locations.Location;
import project.locations.PhysicalLocation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PhysicalLocationRepository {
    public PhysicalLocation createPhysicalLocation (PhysicalLocation physicalLocation) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO physicalLocations(locationName, city, address, capacity) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, physicalLocation.getName());
            preparedStatement.setString(2, physicalLocation.getCity());
            preparedStatement.setString(3, physicalLocation.getAddress());
            preparedStatement.setInt(4, physicalLocation.getCapacity());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                physicalLocation.setLocationId(resultSet.getInt(1));
            }
            resultSet.close();
            return physicalLocation;
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to create" + physicalLocation + exception.getMessage());
        }
    }

    public List<Location> findAllPhysicalLocations() {
        List<Location> locations = new ArrayList<>();
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM physicalLocations";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                locations.add(mapToPhysicalLocation(resultSet));
            }
            resultSet.close();
            return locations;

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to fetch all locations" + exception.getMessage());
        }
    }

    public PhysicalLocation findPhysicalLocation(int locationId) {
        PhysicalLocation physicalLocation = null;
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM physicalLocations WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, locationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                physicalLocation = new PhysicalLocation(resultSet.getString("locationName"), resultSet.getString("city"), resultSet.getString("address"), resultSet.getInt("capacity"));
                physicalLocation.setLocationId(resultSet.getInt("id"));
            }
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to find" + locationId + exception.getMessage());
        }
        return physicalLocation;
    }

    public int deletePhysicalLocation(int locationId) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM physicalLocations WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, locationId);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to delete location" + locationId + exception.getMessage());
        }
    }

    public int updatePhysicalLocation(int locationId, String locationName) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "UPDATE physicalLocations SET locationName = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, locationName);
            preparedStatement.setInt(2, locationId);
            return preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException("Something went wrong while trying to update location" + locationId + exception.getMessage());
        }
    }

    private Location mapToPhysicalLocation(ResultSet resultSet) throws SQLException {
        Location physicalLocation = new PhysicalLocation(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
        physicalLocation.setLocationId(resultSet.getInt(1));
        return physicalLocation;
    }
}
