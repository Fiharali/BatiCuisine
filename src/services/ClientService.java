package services;

import domain.entities.Client;
import repository.ClientRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {


private ClientRepository clientRepository;

    public ClientService() {
        clientRepository = new ClientRepository();
    }

    public List<Client>  allClients() {
       return  clientRepository.all();
    }

    public Optional<Client>  addClient(Client client) {
        return Optional.of(clientRepository.save(client));
    }


    public Optional<Client> findClientByName(String name) {
            return allClients().stream()
                    .filter(client -> client.getName().equalsIgnoreCase(name))
                    .findFirst();
    }

    public boolean deleteClient(Client client) {
        return clientRepository.delete(client);
    }


    public List<Client> getAllClientsWithProjects() {
        return  clientRepository.getClientWithProjects();
    }
}
