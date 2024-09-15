package services;

import domain.entities.Client;
import domain.entities.Component;
import domain.entities.Material;
import domain.entities.Project;
import domain.enums.ProjectStatus;
import repository.ComponentRepository;
import repository.MaterialRepository;
import repository.ProjectRepository;

import java.util.Optional;

public class ProjectService {

   private  ProjectRepository projectRepository ;
   private MaterialRepository materialRepository ;
   private ComponentRepository componentRepository ;

    public ProjectService(){
        this.projectRepository = new ProjectRepository();
        this.materialRepository = new MaterialRepository();
        this.componentRepository = new ComponentRepository();
    }

    public Optional<Project> createProject(String projectName, double surface ,double profit, double total, Client client) {
        Project project = new Project(projectName,surface,profit,total, ProjectStatus.INPROGRESS,client);
        return Optional.ofNullable(projectRepository.save(project));
    }

    public boolean addMaterialToProject(Project project,Material material ,Component component) {
        material.setComponent(component);
        return materialRepository.save(material) != null;
    }
}
