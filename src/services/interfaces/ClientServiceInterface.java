package services.interfaces;

import domain.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {
    List<Client> allClients();

    Optional<Client> addClient(Client client);

    Optional<Client> findClientByName(String name);

    boolean deleteClient(Client client);

    List<Client> getAllClientsWithProjects();
}
