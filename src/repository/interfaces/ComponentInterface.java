package repository.interfaces;

import domain.entities.Component;

import java.util.List;
import java.util.Optional;

public interface ComponentInterface {

    public Component save(Component component);

    public Optional<Component> findById(Component component);


    public List<Component> all();

    public Component update(Component component);

    public boolean delete(Component component);

}