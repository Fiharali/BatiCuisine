package domain.entities;

import domain.enums.ProjectStatus;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private double profitMargin;
    private double totalCost;
    private double surface;
    List<Component> listComponents;
    private ProjectStatus status;
    private Client client;

    

    public Project(int id, String name, double profitMargin, double totalCost , double surface , String status, Client client) {
        this.id = id;
        this.name = name;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.surface = surface;
        this.listComponents=new ArrayList<>();
        this.status= ProjectStatus.valueOf(status);
        this.client=client;

    }
    public Project( String name, double profitMargin, double totalCost , double surface , ProjectStatus status, Client client) {
        this.name = name;
        this.profitMargin = profitMargin;
        this.totalCost = totalCost;
        this.surface = surface;
        this.listComponents=new ArrayList<>();
        this.status= status;
        this.client=client;

    }
    public Project() {
        this.listComponents=new ArrayList<>();
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

 

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
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

    public double getprofitMargin() {
        return profitMargin;
    }

    public void setprofitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double gettotalCost() {
        return totalCost;
    }

    public void settotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public List<Component> getListComponents() {
        return listComponents;
    }

    public void setListComponents(List<Component> listComponents) {
        this.listComponents = listComponents;
    }
    public void addComponent(Component component) {
        this.listComponents.add(component);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profitMargin=" + profitMargin +
                ", totalCost=" + totalCost +
                '}';
    }
}
