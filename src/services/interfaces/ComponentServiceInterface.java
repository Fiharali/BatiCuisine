package services.interfaces;

import domain.entities.Component;
import domain.entities.Labor;
import domain.entities.Material;
import domain.entities.Project;

public interface ComponentServiceInterface {
    boolean addComponentToProjectWithMaterial(Project project, Material material, Component component);

    boolean addComponentToProjectWithLabor(Project project, Labor labor, Component component);
}
