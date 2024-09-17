package repository;

import config.DBConnection;
import domain.entities.Labor;
import domain.entities.Labor;
import repository.interfaces.LaborInterface;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LaborRebository  implements LaborInterface {
    private Connection connection;

    public LaborRebository() {
        this.connection = DBConnection.getConnection();
    }

    public Labor save(Labor labor) {
        String sql = "INSERT INTO labor (hourlyrate, workhours, workerproductivity, labor_id) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, labor.getHourlyRate());
            statement.setDouble(2, labor.getWorkHours());
            statement.setDouble(3, labor.getWorkerProductivity());
            statement.setDouble(4, labor.getLabor().getId());
            int rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return labor;
    }

    public Optional<Labor> findById(Labor labor) {
        return Optional.empty();
    }


    public List<Labor> all() {
        return List.of();
    }


    public Labor update(Labor labor) {
        return null;
    }


    public boolean delete(Labor labor) {
        return false;
    }
}
