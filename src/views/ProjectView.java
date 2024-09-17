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

    public Optional<Project> findfByName(String name  ){
        return   projectService.getProjectByName(name);
    }


    public void  manageProject() {


        boolean exit = false;
        while (!exit) {

            System.out.println("\n  === Menu de projects ===");
            System.out.println("\n 1. supprimer un projet  ");
            System.out.println("\n 2. afficher un projet  ");
            System.out.println("\n 3. Modifier un projet  ");
            System.out.println("\n 0. retour");

            int choice = InputUtils.readInt("Choisissez une option :");
            switch (choice) {
                case 1:
                    //deleteClient();
                    break;
                case 2:
                    displayProject();
                    break;
                case 0:
                    exit = true;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }






    public void displayProject(){
        String name= InputUtils.readString("Entrez le nom du projet  :  ");
        Optional<Project> project = projectService.getProjectByName(name);
        project.ifPresentOrElse(
                prj -> {
                    System.out.println("Projet trouvé !");
                    System.out.println("Nom : " + prj.getName());
                    System.out.println("surface : " + prj.getSurface());
                    System.out.println("etat : " + prj.getStatus());
                    System.out.println("total : " + prj.getTotalCost());
                    System.out.println("profit  : " + prj.getProfitMargin());
                },
                () -> System.out.println("projet non trouvé.")
        );
    }




}
