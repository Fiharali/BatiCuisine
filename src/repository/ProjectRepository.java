package repository;

import config.DBConnection;
import domain.entities.Project;
import repository.interfaces.ProjectInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements ProjectInterface {


    private Connection connection;

    public ProjectRepository() {
        this.connection = DBConnection.getConnection();
    }


    @Override
    public Project save(Project project) {
        String sql = "INSERT INTO projects (projectname, profitmargin, totalcost, status, surface, client_id) VALUES (?, ?, ?, 'inprogress', ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, project.getName());
            statement.setDouble(2, project.getprofitMargin());
            statement.setDouble(3, project.gettotalCost());
            // statement.setString(4, project.getStatus().name());
            statement.setDouble(4, project.getSurface());
            statement.setDouble(5, project.getClient().getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        project.setId((int) generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public Optional<Project> findById(Project project) {
        return Optional.empty();
    }

    @Override
    public List<Project> all() {
        return List.of();
    }

    @Override
    public boolean delete(Project entity) {
        return false;
    }

    @Override
    public Project update(Project entity) {
        return null;
    }
}
