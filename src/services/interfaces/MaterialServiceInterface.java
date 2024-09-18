package services.interfaces;

import domain.entities.Component;
import domain.entities.Material;

public interface MaterialServiceInterface {

     boolean addMaterialToProject(Material material , Component component);
}
