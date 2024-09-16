package services;

import domain.entities.Component;
import domain.entities.Labor;
import domain.entities.Material;
import domain.entities.Project;
import repository.ComponentRepository;

public class ComponentService {

    private ComponentRepository componentRepository ;
    private ProjectService projectService ;
    private LaborService laborService ;

    public ComponentService(){
        this.componentRepository = new ComponentRepository();
        this.projectService = new ProjectService();
        this.laborService = new LaborService();
    }

    public boolean addComponentToProjectWithMaterial(Project project , Material material, Component component ) {
        //project.addComponent(material);
       // component.setProject(project);
        component = componentRepository.save(component);
       if (component != null) {
           return projectService.addMaterialToProject(material,component);
       }
       return false;
    }



    public boolean addComponentToProjectWithLabor(Project project , Labor labor, Component component ) {
       // project.addComponent(labor);
       // component.setProject(project);
        component = componentRepository.save(component);
        project.addComponent(labor);
       if (component != null) {
           return laborService.addLaborToProject(labor,component);
       };
       return false;
    }
}
