package project.DB;

import project.events.Concert;
import project.events.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcertRepository {
    public Event createConcert (Event concert) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO concerts(eventName, eventDate, description, durationInHours, locationId, " +
                    "participantsLimit, confirmedParticipations, soldOut, musician, genre) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, concert.getName());
            preparedStatement.setString(2, concert.getDate());
            preparedStatement.setString(3, concert.getDescription());
            preparedStatement.setInt(4, concert.getDurationInHours());
            preparedStatement.setInt(5, concert.getLocationId());
            preparedStatement.setInt(6, concert.getParticipantsLimit());
            preparedStatement.setInt(7, concert.getConfirmedParticipations());
            preparedStatement.setBoolean(8, concert.getSoldOut());
            preparedStatement.setString(9, ((Concert)concert).getMusician());
            preparedStatement.setString(10, ((Concert) concert).getGenre());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                concert.setEventId(resultSet.getInt(1));
            }
            resultSet.close();
            return concert;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<Event> findAllConcerts() {
        List<Event> concerts = new ArrayList<>();
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM concerts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                concerts.add(mapToConcert(resultSet));
            }
            resultSet.close();
            return concerts;

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<Event> findConcert(String musician) {
        List <Event> concerts = new ArrayList<>();
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM concerts WHERE musician = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, musician);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Event concert = new Concert(resultSet.getString("eventName"), resultSet.getString("eventDate"),
                        resultSet.getString("description"), resultSet.getInt("durationInHours"),
                        resultSet.getInt("locationId"), resultSet.getInt("participantsLimit"),
                        resultSet.getString("musician"), resultSet.getString("genre"));
                concert.setEventId(resultSet.getInt("id"));
                concert.setConfirmedParticipations(resultSet.getInt("confirmedParticipations"));
                concert.setSoldOut(resultSet.getBoolean("soldOut"));
                concerts.add(concert);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
        return concerts;
    }

    public int deleteConcert(int eventId) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "DELETE FROM concerts WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, eventId);
            return preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public int updateConcert(int eventId, int participantsLimit) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "UPDATE concerts SET participantsLimit = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, participantsLimit);
            preparedStatement.setInt(2, eventId);
            return preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private Event mapToConcert(ResultSet resultSet) throws SQLException {
        Event concert = new Concert(resultSet.getString("eventName"), resultSet.getString("eventDate"),
                resultSet.getString("description"), resultSet.getInt("durationInHours"),
                resultSet.getInt("locationId"), resultSet.getInt("participantsLimit"),
                resultSet.getString("musician"), resultSet.getString("genre"));
        concert.setEventId(resultSet.getInt("id"));
        concert.setConfirmedParticipations(resultSet.getInt("confirmedParticipations"));
        concert.setSoldOut(resultSet.getBoolean("soldOut"));
        return concert;
    }

}
