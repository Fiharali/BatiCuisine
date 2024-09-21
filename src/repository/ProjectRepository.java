package repository;

import config.DBConnection;
import domain.entities.*;
import domain.enums.ProjectStatus;
import exceptions.ProjectsNotFoundException;
import repository.interfaces.ProjectInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements ProjectInterface {


    private Connection connection;

    public ProjectRepository() {
        this.connection = DBConnection.getConnection();
    }


    @Override
    public Project save(Project project) {
        String sql = "INSERT INTO projects (projectname, profitmargin, totalcost, status, surface, client_id) VALUES (?, ?, ?, 'INPROGRESS', ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, project.getName());
            statement.setDouble(2, project.getProfitMargin());
            statement.setDouble(3, project.getTotalCost());
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

        String sql = "SELECT * FROM projects WHERE id = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, project.getId());

           ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
              project.setName(resultSet.getString("projectname"));
              project.setProfitMargin(resultSet.getDouble("profitmargin"));
              project.setTotalCost(resultSet.getDouble("totalcost"));
              project.setSurface(resultSet.getDouble("surface"));
              project.setStatus(ProjectStatus.valueOf("INPROGRESS"));
            }

            return Optional.of(project);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Project> getProjectWithClientAndComponent(Project project) {

        String query = "SELECT " +
                "    c.id AS client_id, " +
                "    c.name AS client_name, " +
                "    c.address AS client_address, " +
                "    c.phone AS client_phone, " +
                "    c.is_professional AS client_is_professional, " +
                " " +
                "    p.id AS project_id, " +
                "    p.projectName AS project_name, " +
                "    p.profitMargin AS project_profit_margin, " +
                "    p.totalCost AS project_total_cost, " +
                "    p.surface AS project_surface, " +
                "    p.status AS project_status, " +
                " " +
                "    comp.id AS component_id, " +
                "    comp.name AS component_name, " +
                "    comp.componentType AS component_type, " +
                "    comp.vatRate AS component_vat_rate, " +
                " " +
                "    m.id AS material_id, " +
                "    m.unitCost AS material_unit_cost, " +
                "    m.quantity AS material_quantity, " +
                "    m.transportCost AS material_transport_cost, " +
                "    m.qualityCoefficient AS material_quality_coefficient, " +
                " " +
                "    l.id AS labor_id, " +
                "    l.hourlyRate AS labor_hourly_rate, " +
                "    l.workHours AS labor_work_hours, " +
                "    l.workerProductivity AS labor_worker_productivity " +
                "FROM Clients c " +
                "LEFT JOIN Projects p ON c.id = p.client_id " +
                "LEFT JOIN Components comp ON p.id = comp.project_id " +
                "LEFT JOIN Materials m ON comp.id = m.component_id " +
                "LEFT JOIN Labor l ON comp.id = l.component_id " +
                "WHERE p.projectName = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, project.getName());
            ResultSet rs = stmt.executeQuery();

            Project currentProject = null;
            Component currentComponent = null;

            while (rs.next()) {
                if (currentProject == null) {
                    currentProject = new Project();
                    currentProject.setId(rs.getInt("project_id"));
                    currentProject.setName(rs.getString("project_name"));
                    currentProject.setProfitMargin(rs.getDouble("project_profit_margin"));
                    currentProject.setTotalCost(rs.getDouble("project_total_cost"));
                    currentProject.setSurface(rs.getDouble("project_surface"));
                    currentProject.setStatus(ProjectStatus.valueOf("INPROGRESS"));


                    Client client = new Client();
                    client.setId(rs.getInt("client_id"));
                    client.setName(rs.getString("client_name"));
                    client.setAddress(rs.getString("client_address"));
                    client.setPhone(rs.getString("client_phone"));
                    client.setProfessional(rs.getBoolean("client_is_professional"));
                    currentProject.setClient(client);
                }


                int componentId = rs.getInt("component_id");
                if (currentComponent == null || currentComponent.getId() != componentId) {
                    currentComponent = new Component();
                    currentComponent.setId(componentId);
                    currentComponent.setName(rs.getString("component_name"));
                    currentComponent.setComponentType(rs.getString("component_type"));
                    currentComponent.setVatRate(rs.getDouble("component_vat_rate"));
                    currentProject.getListComponents().add(currentComponent);
                }

                int materialId = rs.getInt("material_id");
                if (materialId != 0) {
                    Material material = new Material();
                    material.setId(materialId);
                    material.setUnitCost(rs.getDouble("material_unit_cost"));
                    material.setQuantity(rs.getDouble("material_quantity"));
                    material.setTransportCost(rs.getDouble("material_transport_cost"));
                    material.setQualityCoefficient(rs.getDouble("material_quality_coefficient"));
                    currentComponent.setMaterial(material);
                }

                int laborId = rs.getInt("labor_id");
                if (laborId != 0) {
                    Labor labor = new Labor();
                    labor.setId(laborId);
                    labor.setHourlyRate(rs.getDouble("labor_hourly_rate"));
                    labor.setWorkHours(rs.getDouble("labor_work_hours"));
                    labor.setWorkerProductivity(rs.getDouble("labor_worker_productivity"));
                    currentComponent.setLabor(labor);
                }
            }

            return Optional.ofNullable(currentProject);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Project> findByName(Project project) {

        String sql = "SELECT * FROM projects WHERE projectname = ?";

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, project.getName());


            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("projectname"));
                project.setProfitMargin(resultSet.getDouble("profitmargin"));
                project.setTotalCost(resultSet.getDouble("totalcost"));
                project.setSurface(resultSet.getDouble("surface"));
                project.setStatus(ProjectStatus.valueOf("INPROGRESS"));
                return Optional.of(project);
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new ProjectsNotFoundException("Project with name '" + project.getName() + "' not found.");

        }

    }

    @Override
    public List<Project> all() {
        return List.of();
    }

    @Override
    public boolean delete(Project project) {
        String sql = "DELETE FROM projects WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, project.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project update(Project project) {

        String sql = "UPDATE projects SET status = ?::projectstatus WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, project.getStatus().name());
            preparedStatement.setInt(2, project.getId());
            preparedStatement.executeUpdate();
            return project;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
