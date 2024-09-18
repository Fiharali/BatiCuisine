package repository.interfaces;

import domain.entities.Material;
import java.util.List;
import java.util.Optional;

public interface MaterialInterface {
    
    public Material save(Material material);

    public Optional<Material> findById(Material material);

    public List<Material> all();

    public Material update(Material material);

    public boolean delete(Material material);
}
