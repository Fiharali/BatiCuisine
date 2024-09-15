package services;

import domain.entities.Client;
import domain.entities.Material;
import domain.entities.Project;
import domain.enums.ProjectStatus;
import repository.ProjectRepository;

import java.util.Optional;

public class ProjectService {

   private  ProjectRepository projectRepository ;

    public ProjectService(){
        this.projectRepository = new ProjectRepository();
    }

    public Optional<Project> createProject(String projectName, double surface ,double profit, double total, Client client) {
        Project project = new Project(projectName,surface,profit,total, ProjectStatus.INPROGRESS,client);
        return Optional.ofNullable(projectRepository.save(project));
    }

    public boolean addMaterialToProject(Project project, String materialName, double quantity, double unitCost, double transportCost, double qualityCoefficient) {
        Material material = new Material(materialName, "material", quantity, unitCost, transportCost, qualityCoefficient);
        project.addComponent(material);
        return true;
    }
}
