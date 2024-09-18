package services;

import domain.entities.Component;
import domain.entities.Material;
import repository.MaterialRepository;
import services.interfaces.MaterialServiceInterface;

public class MaterialService implements MaterialServiceInterface {

    private MaterialRepository materialRepository ;

    public MaterialService( ) {
        this.materialRepository = new MaterialRepository() ;
    }


    @Override
    public boolean addMaterialToProject(Material material , Component component) {
        material.setComponent(component);
        return materialRepository.save(material) != null;
    }

}
