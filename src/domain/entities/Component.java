package domain.entities;

import java.util.ArrayList;
import java.util.List;

public class Component {
    private int id;
    private String name;
    private String componentType;
    private double vatRate;
    private Project project;
    private Material material;
    private Labor labor;





    public Component(String name, String componentType, double vatRate) {
        this.name = name;
        this.componentType = componentType;
        this.vatRate = vatRate;

    }
    public Component(String name, String componentType) {
        this.name = name;
        this.componentType = componentType;

    }


    public Component() {

    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Material getMaterial() {
        return material;
    }




    public Labor getLabor() {
        return labor;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
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
