package repository;

import config.DBConnection;
import domain.entities.Client;
import domain.entities.Quote;
import repository.interfaces.QuoteInterface;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class QuoteRepository implements QuoteInterface {
    private Connection connection;

    public QuoteRepository() {
        this.connection =  DBConnection.getInstance().getConnection();
    }


    public Optional<Quote> save(Quote quote) {
        String sql = "INSERT INTO quotes (estimatedamount, issuedate, validateddate, isaccepted ,project_id ) VALUES (?, ?, ?, ?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, quote.getEstimatedAmount());
            statement.setDate(2, Date.valueOf( quote.getIssueDate()));
            statement.setDate(3, Date.valueOf(quote.getValidatedDate()));
            statement.setBoolean(4, quote.isAccepted());
            statement.setInt(5, quote.getProject().getId());
            statement.executeUpdate();

            return Optional.of(quote);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<Quote> findById(Quote quote)    {
        String sql = "SELECT * FROM quotes WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quote.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                quote.setEstimatedAmount(resultSet.getDouble("estimatedamount"));
                quote.setIssueDate(resultSet.getDate("issuedate").toLocalDate());
                quote.setValidatedDate(resultSet.getDate("validateddate").toLocalDate());
                quote.setAccepted(resultSet.getBoolean("isaccepted"));
                return Optional.of(quote);
            }
            else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Quote> all() {
        return List.of();
    }

    @Override
    public Quote update(Quote quote) {
        return null;
    }

    @Override
    public boolean delete(Quote quote) {
        String sql = "DELETE FROM quotes WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, quote.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
