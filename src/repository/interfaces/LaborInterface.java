package repository.interfaces;

import domain.entities.Labor;

import java.util.List;
import java.util.Optional;

public interface LaborInterface {

    public Labor save(Labor labor);

    public Optional<Labor> findById(Labor labor);

    public List<Labor> all();

    public Labor update(Labor labor);

    public boolean delete(Labor labor);
}
