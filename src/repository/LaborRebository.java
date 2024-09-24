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
        this.connection =  DBConnection.getInstance().getConnection();
    }

    public Labor save(Labor labor) {
        String sql = "INSERT INTO labor (hourlyrate, workhours, workerproductivity, component_id) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, labor.getHourlyRate());
            statement.setDouble(2, labor.getWorkHours());
            statement.setDouble(3, labor.getWorkerProductivity());
            statement.setDouble(4, labor.getComponent().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return labor;
    }


}
