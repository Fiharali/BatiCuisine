package repository;

import config.DBConnection;
import domain.entities.Component;
import repository.interfaces.ComponentInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ComponentRepository implements ComponentInterface {
    private Connection connection;
    public ComponentRepository() {
        this.connection = DBConnection.getConnection();
    }


    @Override
    public Component save(Component component) {
        String sql = "INSERT INTO components (name, componenttype, vatrate, project_id) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, component.getName());
            statement.setString(2, component.getComponentType());
            statement.setDouble(3, component.getVatRate());
            statement.setInt(4, component.getProject().getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        component.setId((int) generatedKeys.getLong(1));
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return component;
    }

    @Override
    public Optional<Component> findById(Component component) {

        String sql = "SELECT * FROM components WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, component.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                component.setName(resultSet.getString("name"));
                component.setComponentType(resultSet.getString("componenttype"));
                component.setVatRate(resultSet.getDouble("vatrate"));
                return Optional.of(component);
            }
            else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Component> all() {
        return null;
    }

    @Override
    public Component update(Component component) {
        return null;
    }

    @Override
    public boolean delete(Component component) {

        String sql = "DELETE FROM components WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, component.getId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
