package services.interfaces;

import domain.entities.Component;
import domain.entities.Labor;

public interface LaborServiceInterface {
    boolean addLaborToProject(Labor labor, Component component);
}
