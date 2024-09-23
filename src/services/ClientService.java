package services;

import domain.entities.Client;
import repository.ClientRepository;
import services.interfaces.ClientServiceInterface;

import java.util.List;
import java.util.Optional;

public class ClientService implements ClientServiceInterface {


private ClientRepository clientRepository;

    public ClientService() {
        clientRepository = new ClientRepository();
    }

    @Override
    public List<Client>  allClients() {
       return  clientRepository.all();
    }

    @Override
    public Optional<Client>  addClient(Client client) {
        return Optional.of(clientRepository.save(client));
    }


    @Override
    public Optional<Client> findClientByName(String name) {
            return allClients().stream()
                    .filter(client -> client.getName().equalsIgnoreCase(name))
                    .findFirst();
    }

     @Override
    public Optional<Client> findById(int id) {
        return allClients().stream()
                .filter(client -> client.getId()==id)
                .findFirst();
    }

    @Override
    public boolean deleteClient(Client client) {
        return clientRepository.delete(client);
    }


    @Override
    public List<Client> getAllClientsWithProjects() {
        return  clientRepository.getClientWithProjects();
    }
}
