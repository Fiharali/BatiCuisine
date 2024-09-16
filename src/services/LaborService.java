package services;

import domain.entities.Component;
import domain.entities.Labor;
import domain.entities.Material;
import domain.entities.Project;
import repository.LaborRebository;

public class LaborService {
    private LaborRebository laborRebository;

    public LaborService() {
        this.laborRebository = new LaborRebository();

    }

    public boolean addLaborToProject( Labor labor , Component component) {
        labor.setComponent(component);
        return laborRebository.save(labor) != null;
    }
}
