package repository.interfaces;

import domain.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientInterface {

    public Client save(Client client);

    public Optional<Client> findById(Client client);

    public List<Client> all();

    public Client update(Client client);

    public boolean delete(Client client);
}
