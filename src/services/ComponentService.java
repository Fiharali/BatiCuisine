package services;

import domain.entities.Component;
import domain.entities.Labor;
import domain.entities.Material;
import domain.entities.Project;
import repository.ComponentRepository;
import services.interfaces.ComponentServiceInterface;
import services.interfaces.LaborServiceInterface;

import java.util.Optional;

public class ComponentService implements ComponentServiceInterface {

    private ComponentRepository componentRepository ;
    private ProjectService projectService ;
    private LaborService laborService;
    private MaterialService materialService;

    public ComponentService(){
        this.componentRepository = new ComponentRepository();
        this.projectService = new ProjectService();
        this.laborService = new LaborService();
        this.materialService = new MaterialService();
    }

    @Override
    public boolean addComponentToProjectWithMaterial(Project project, Material material, Component component) {
      //  project.addComponent(material);
        component.setProject(project);
        component = componentRepository.save(component);
       if (component != null) {
           return materialService.addMaterialToProject(material,component);
       }
       return false;
    }



    @Override
    public boolean addComponentToProjectWithLabor(Project project, Labor labor, Component component) {
       // project.addComponent(labor);
        component.setProject(project);
        component = componentRepository.save(component);
        project.addComponent(labor);
       if (component != null) {
           return laborService.addLaborToProject(labor,component);
       };
       return false;
    }


    public Optional<Component> findById(int id){
        Component component = new Component();
        component.setId(id);
        return componentRepository.findById(component);
    }


    public boolean deleteComponent(Component component) {
        return componentRepository.delete(component);
    }
}
