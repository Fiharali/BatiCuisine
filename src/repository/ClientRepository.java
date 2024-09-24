package repository;


import config.DBConnection;
import domain.entities.*;
import domain.enums.ProjectStatus;
import repository.interfaces.ClientInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientInterface {

    private Connection connection;
    public ClientRepository() {
        this.connection = DBConnection.getConnection();
    }


    @Override
    public List<Client> all() {
        String sql = "SELECT * FROM clients";
        List<Client> clients = new ArrayList<>();
        try  {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setPhone(rs.getString("phone"));
                client.setProfessional(rs.getBoolean("is_professional"));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Client save(Client client) {
        String sql = "INSERT INTO clients (name, address, phone, is_professional) VALUES (?, ?, ?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getName());
            statement.setString(2, client.getAddress());
            statement.setString(3, client.getPhone());
            statement.setBoolean(4, client.isProfessional());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        client.setId((int) generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public Optional<Client> findById(Client client) {
        return Optional.empty();
    }

    public List<Client> getClientWithProjects()   {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT " +
                "c.id AS client_id, c.name AS client_name, c.address AS client_address, c.phone AS client_phone, c.is_professional AS client_is_professional, " +
                "p.id AS project_id, p.projectName AS project_name, p.profitMargin AS project_profit_margin, p.totalCost AS project_total_cost, p.surface AS project_surface, p.status AS project_status, " +
                "comp.id AS component_id, comp.name AS component_name, comp.componentType AS component_type, comp.vatRate AS component_vat_rate, " +
                "m.id AS material_id, m.unitCost AS material_unit_cost, m.quantity AS material_quantity, m.transportCost AS material_transport_cost, m.qualityCoefficient AS material_quality_coefficient, " +
                "l.id AS labor_id, l.hourlyRate AS labor_hourly_rate, l.workHours AS labor_work_hours, l.workerProductivity AS labor_worker_productivity " +
                "FROM Clients c " +
                "LEFT JOIN Projects p ON c.id = p.client_id " +
                "LEFT JOIN Components comp ON p.id = comp.project_id " +
                "LEFT JOIN Materials m ON comp.id = m.component_id " +
                "LEFT JOIN Labor l ON comp.id = l.component_id";

        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Client client = findOrCreateClient(clients, rs.getInt("client_id"));
                client.setId(rs.getInt("client_id"));
                client.setName(rs.getString("client_name"));
                client.setAddress(rs.getString("client_address"));
                client.setPhone(rs.getString("client_phone"));
                client.setProfessional(rs.getBoolean("client_is_professional"));

                int projectId = rs.getInt("project_id");
                if (projectId != 0) {
                    Project project = findOrCreateProject(client.getListProjects(), projectId);
                    if (project.getId() == 0) {
                        project.setId(projectId);
                        project.setName(rs.getString("project_name"));
                        project.setProfitMargin(rs.getDouble("project_profit_margin"));
                        project.setTotalCost(rs.getDouble("project_total_cost"));
                        project.setSurface(rs.getDouble("project_surface"));
                        project.setStatus(ProjectStatus.valueOf("INPROGRESS"));
                        project.setClient(client);
                        client.getListProjects().add(project);
                    }
                    int componentId = rs.getInt("component_id");
                    if (componentId != 0) {
                        Component component = findOrCreateComponent(project.getListComponents(), componentId);
                        if (component.getId() == 0) {
                            component.setId(componentId);
                            component.setName(rs.getString("component_name"));
                            component.setComponentType(rs.getString("component_type"));
                            component.setVatRate(rs.getDouble("component_vat_rate"));
                            component.setProject(project);
                        }

                        int materialId = rs.getInt("material_id");
                        if (materialId != 0) {
                            Material material = new Material();
                            material.setId(materialId);
                            material.setUnitCost(rs.getDouble("material_unit_cost"));
                            material.setQuantity(rs.getDouble("material_quantity"));
                            material.setTransportCost(rs.getDouble("material_transport_cost"));
                            material.setQualityCoefficient(rs.getDouble("material_quality_coefficient"));
                            component.setMaterial(material);
                        }
                        int laborId = rs.getInt("labor_id");
                        if (laborId != 0) {
                            Labor labor = new Labor();
                            labor.setHourlyRate(rs.getDouble("labor_hourly_rate"));
                            labor.setWorkHours(rs.getDouble("labor_work_hours"));
                            labor.setWorkerProductivity(rs.getDouble("labor_worker_productivity"));
                            component.setLabor(labor);
                        }
                    }
                }
            }

            return clients;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private Client findOrCreateClient(List<Client> clients, int clientId) {
        for (Client client : clients) {
            if (client.getId() == clientId) {
                return client;
            }
        }
        Client newClient = new Client();
        clients.add(newClient);
        return newClient;
    }
    private Project findOrCreateProject(List<Project> projects, int projectId) {
        for (Project project : projects) {
            if (project.getId() == projectId) {
                return project;
            }
        }
        Project newProject = new Project();
        projects.add(newProject);
        return newProject;
    }
    private Component findOrCreateComponent(List<Component> components, int componentId) {
            for (Component component : components) {
                if (component.getId() == componentId) {
                    return component;
                }
            }
            Component newComponent = new Component();
            components.add(newComponent);
            return newComponent;
        }



    @Override
    public boolean delete(Client client) {
        String sql = "DELETE FROM clients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }
}
