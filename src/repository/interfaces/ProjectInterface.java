package repository.interfaces;

import domain.entities.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectInterface {
    public Project save(Project project);

    public Optional<Project> findById(Project project);



    public List<Project> all();

    public Project update(Project entity);


    public boolean delete(Project entity);
}
