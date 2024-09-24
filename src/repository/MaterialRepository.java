package repository;

import config.DBConnection;
import domain.entities.Component;
import domain.entities.Material;
import repository.interfaces.MaterialInterface;
//import repository.interfaces.ComponentInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MaterialRepository implements MaterialInterface {
    private Connection connection;

    public MaterialRepository() {
        this.connection =  DBConnection.getInstance().getConnection();
    }


    public Material save(Material material) {
        String sql = "INSERT INTO materials (unitcost, quantity, transportcost, qualitycoefficient, component_id) VALUES (?, ?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, material.getUnitCost());
            statement.setDouble(2, material.getQuantity());
            statement.setDouble(3, material.getTransportCost());
            statement.setDouble(4, material.getQualityCoefficient());
            statement.setDouble(5, material.getComponent().getId());
            int rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return material;
    }


    public Optional<Material> findById(Material material) {
        return Optional.empty();
    }


    public List<Material> all() {
        return List.of();
    }


    public Material update(Material material) {
        return null;
    }


    public boolean delete(Material material) {
        return false;
    }
}
