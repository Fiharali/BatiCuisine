package views;

import domain.entities.*;
import services.ProjectService;
import utils.InputUtils;

import java.util.Optional;

public class ProjectView {

private MaterialView  materialView;
private ProjectService projectService;

public ProjectView() {
    materialView = new MaterialView();
    projectService = new ProjectService();

}


    public void  createProject(Client client) {
        System.out.println("--- Création d'un Nouveau Projet ---");
        String projectName= InputUtils.readString("Entrez le nom du projet : ");
        double surface= InputUtils.readDouble("Entrez la surface de la cuisine (en m²) : ");
        Optional<Project> project = projectService.createProject(projectName, surface,0,0,client);
        project.ifPresent(proj ->materialView.addMaterialToProject(proj) );

    }

    public Optional<Project> getProject(int id  ){
      return   projectService.find(id);
    }







}
