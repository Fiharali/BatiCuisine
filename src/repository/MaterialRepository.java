package repository;

import config.DBConnection;
import domain.entities.Component;
import domain.entities.Material;
//import repository.interfaces.ComponentInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MaterialRepository  {
    private Connection connection;

    public MaterialRepository() {
        this.connection = DBConnection.getConnection();
    }


    public Material save(Material material) {
        String sql = "INSERT INTO materials (unitcost, quantity, transportcost, qualitycoefficient, component_id) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, material.getunitCost());
            statement.setDouble(2, material.getQuantity());
            statement.setDouble(3, material.getTransportCost());
            statement.setDouble(4, material.getqualityCoefficient());
            statement.setDouble(5, material.getComponent().getId());
            int rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return material;
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
