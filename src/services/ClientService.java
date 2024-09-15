package services;

import domain.entities.Client;
import repository.ClientRepository;

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


    public Optional<Client> findClientByName(String name) {
            return allClients().stream()
                    .filter(client -> client.getName().equalsIgnoreCase(name))
                    .findFirst();
    }
}
