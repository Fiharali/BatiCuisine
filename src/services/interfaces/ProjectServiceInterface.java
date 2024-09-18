package services.interfaces;

import domain.entities.Client;
import domain.entities.Project;

import java.util.Optional;

public interface ProjectServiceInterface {
    Optional<Project> createProject(String projectName, double surface, double profit, double total, Client client);

    Optional<Project> find(int id);

    Optional<Project> getProjectByName(String name);

    boolean deleteProject(Project project);
}
