package project.DB;


import project.actions.AuditObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditRepository {
    public AuditObject createAuditObject(AuditObject audit) {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "INSERT INTO audit(message, timeOfAction) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, audit.getMessage());
            preparedStatement.setString(2, audit.getTimestamp());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                audit.setAuditId(resultSet.getInt(1));
            }
            resultSet.close();
            return audit;
        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public List<AuditObject> findAllEntries() {
        List<AuditObject> entries = new ArrayList<>();
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "SELECT * FROM audit";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                entries.add(mapToAuditObject(resultSet));
            }
            resultSet.close();
            return entries;

        } catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public void deleteAllEntries() {
        try (Connection connection = DBConfiguration.getDatabaseConnection()) {
            String query = "TRUNCATE audit";
            Statement statement = connection.createStatement();
            statement.execute(query);

        }catch (SQLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    private AuditObject mapToAuditObject(ResultSet resultSet) throws SQLException {
        AuditObject audit = new AuditObject(resultSet.getString(2), resultSet.getString(3));
        audit.setAuditId(resultSet.getInt(1));
        return audit;
    }

}