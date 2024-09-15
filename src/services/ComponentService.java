package services;

import domain.entities.Component;
import domain.entities.Material;
import domain.entities.Project;
import repository.ComponentRepository;
import repository.MaterialRepository;
import repository.ProjectRepository;

public class ComponentService {

    private ComponentRepository componentRepository ;
    private ProjectService projectService ;

    public ComponentService(){
        this.componentRepository = new ComponentRepository();
        this.projectService = new ProjectService();
    }

    public boolean addComponentToProject(Project project , Material material, Component component ) {
        project.addComponent(material);
        component.setProject(project);
        component = componentRepository.save(component);
       if (component != null) {
           return projectService.addMaterialToProject(project,material,component);
       };
       return false;
    }
}
