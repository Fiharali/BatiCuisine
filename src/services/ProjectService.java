package services;

import domain.entities.Client;
import domain.entities.Project;
import domain.enums.ProjectStatus;
import repository.MaterialRepository;
import repository.ProjectRepository;

import java.util.List;
import java.util.Optional;

public class ProjectService implements services.interfaces.ProjectServiceInterface {

   private  ProjectRepository projectRepository ;
   private MaterialRepository materialRepository ;

    public ProjectService(){
        this.projectRepository = new ProjectRepository();
        this.materialRepository = new MaterialRepository();
    }

    @Override
    public Optional<Project> createProject(String projectName, double surface, double profit, double total, Client client) {
        Project project = new Project(projectName,profit,total,surface, ProjectStatus.INPROGRESS,client);
        return Optional.ofNullable(projectRepository.save(project));
    }


    @Override
    public Optional<Project> find(int id) {
        Project project = new Project();
        project.setId(id);
        return projectRepository.findById(project);

    }

    @Override
    public Optional<Project> getProjectByName(String name) {
        Project project=new Project();
        project.setName(name);
        return projectRepository.findByName(project);
    }

    @Override
    public boolean deleteProject(Project project) {
        return projectRepository.delete(project);
    }

    @Override
    public Optional<Project> getProjectWithClientAndComponent(Project project) {
      return projectRepository.getProjectWithClientAndComponent(project);

    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.update(project);
    }

    @Override
    public List<Project> findAll(){
        return projectRepository.all();
    }


}
