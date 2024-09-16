package repository;


import config.DBConnection;
import domain.entities.Client;
import repository.interfaces.ClientInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public Optional<Client> findById(Client client) {
        return Optional.empty();
    }



    @Override
    public Client update(Client client) {
        return null;
    }

    @Override
    public boolean delete(Client client) {
        return false;
    }
}
