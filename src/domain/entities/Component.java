package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Component {
    private int id;
    private String name;
    private String componentType;
    private double vatRate;
    private Project project;


    List<Material> materials;
    List<Labor> labors;



    public Component(String name, String componentType, double vatRate) {
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;
        this.labors = new ArrayList<>();
        this.materials = new ArrayList<>();
    }
    public Component(String name, String componentType) {
        this.name = name;
        this.componentType = componentType;
        this.labors = new ArrayList<>();
        this.materials = new ArrayList<>();
    }


    public Component() {
        this.labors = new ArrayList<>();
        this.materials = new ArrayList<>();
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Material> getMaterials() {
        return materials;
    }


    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Labor> getLabors() {
        return labors;
    }

    public void setLabors(List<Labor> labors) {
        this.labors = labors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public double getVatRate() {
        return vatRate;
    }

    public void setVatRate(double vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", componentType='" + componentType + '\'' +
                ", vatRate=" + vatRate +
                '}';
    }
}
