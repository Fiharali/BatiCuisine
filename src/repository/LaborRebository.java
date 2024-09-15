package repository;

import config.DBConnection;
import domain.entities.Component;
import domain.entities.Labor;
import domain.entities.Material;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LaborRebository   {
    private Connection connection;

    public LaborRebository() {
        this.connection = DBConnection.getConnection();
    }

    public Labor save(Labor labor) {
        String sql = "INSERT INTO labor (hourlyrate, workhours, workerproductivity, component_id) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, labor.gethourlyRate());
            statement.setDouble(2, labor.getworkHours());
            statement.setDouble(3, labor.getWorkerProductivity());
            statement.setDouble(4, labor.getComponent().getId());
            int rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return labor;
    }

    public Optional<Component> findById(Component component) {
        return Optional.empty();
    }


    public List<Component> all() {
        return List.of();
    }


    public Component update(Component component) {
        return null;
    }


    public boolean delete(Component component) {
        return false;
    }
}
