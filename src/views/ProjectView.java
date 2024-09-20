package views;

import domain.entities.*;
import services.ProjectService;
import utils.InputUtils;

import java.util.Objects;
import java.util.Optional;

public class ProjectView {

private MaterialView  materialView;
private ProjectService projectService;
private QuoteView quoteView;

public ProjectView() {
    materialView = new MaterialView();
    projectService = new ProjectService();
    quoteView = new QuoteView();

}


public void  createProject(Client client) {
        System.out.println("--- Création d'un Nouveau Projet ---");
        String projectName= InputUtils.readString("Entrez le nom du projet : ");
        double surface= InputUtils.readDouble("Entrez la surface de la cuisine (en m²) : ");
        double profit= InputUtils.readDouble("Entrez profit margin: ");
        Optional<Project> project = projectService.createProject(projectName, surface,profit,0,client);
        client.getListProjects().add(project.get());
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
                    deleteProject();
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


    public void deleteProject(){
        String name= InputUtils.readString("Entrez le nom du client  :  ");
        Optional<Project> project = projectService.getProjectByName(name);
        project.ifPresentOrElse(
                prj -> {
                    String response = InputUtils.readString("Souhaitez-vous supprimé  ce projet ? (y/n) : ");
                    if ("y".equalsIgnoreCase(response)) {
                        boolean isDeleted = projectService.deleteProject(prj);
                        if (isDeleted) {
                            System.out.println("projet été supprimé !");
                        } else {
                            System.out.println("projet ne peut pas être supprimé !");
                        }
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("projet non trouvé.")
        );

    }


    public  void calculiProjectTotal(Project project) {
        Optional<Project> projectOpt = projectService.getProjectWithClientAndComponent(project);

        if (projectOpt.isEmpty()) {
            System.out.println("Le projet est introuvable.");
            return;
        }

        Project currentProject = projectOpt.get();

        System.out.println("--- Résultat du Calcul---");
        System.out.println("Nom du projet : " + currentProject.getName());
        System.out.println("Client : " + currentProject.getClient().getName());
        System.out.println("Adresse du  : " + currentProject.getClient().getAddress());
        System.out.println("Surface : " + currentProject.getSurface() + " m²");


        double totalMaterialCostBeforeVAT = 0.0;
        double totalMaterialCostWithVAT = 0.0;
        double totalLaborCostBeforeVAT = 0.0;
        double totalLaborCostWithVAT = 0.0;

        System.out.println("\n--- Détail des Coûts---");

        System.out.println("1. Matériaux :");
        for (Component component : currentProject.getListComponents()) {
            Material material = component.getMaterial();
            if (material != null) {
                double materialCostBeforeVAT = (material.getUnitCost() * material.getQuantity() * material.getQualityCoefficient()) + material.getTransportCost();
                double materialCostWithVAT = materialCostBeforeVAT * (1 + component.getVatRate() / 100);

                totalMaterialCostBeforeVAT += materialCostBeforeVAT;
                totalMaterialCostWithVAT += materialCostWithVAT;

                System.out.println("-  " + component.getName() +  " : " + String.format("%.2f", materialCostBeforeVAT) + "," +  String.format("%.2f", materialCostWithVAT) + " € (quantité : " + material.getQuantity() + ", coût unitaire : " + material.getUnitCost() + " €, qualité : " + material.getQualityCoefficient() + ", transport : " + material.getTransportCost() + ") " + component.getVatRate());
            }
        }

        System.out.println("---  Coût total des matériaux avant TVA      : " + String.format("%.2f", totalMaterialCostBeforeVAT) + " €**");
        System.out.println("---  Coût total des matériaux avec TVA (20%) : " + String.format("%.2f", totalMaterialCostWithVAT) + " €**");


        System.out.println("2. Main-d'œuvre :");
        for (Component component : currentProject.getListComponents()) {
            Labor labor = component.getLabor();
            if (labor != null) {
                double laborCostBeforeVAT = labor.getHourlyRate() * labor.getWorkHours() * labor.getWorkerProductivity();
                double laborCostWithVAT = laborCostBeforeVAT * (1 + component.getVatRate() / 100.0);

                totalLaborCostBeforeVAT += laborCostBeforeVAT;
                totalLaborCostWithVAT += laborCostWithVAT;

                System.out.println(" - "  + " : " + String.format("%.2f", laborCostBeforeVAT) + " € (taux horaire : " + labor.getHourlyRate() + " €, heures travaillées : " + labor.getWorkHours() + ", productivité : " + labor.getWorkerProductivity() + ")");
            }
        }

        System.out.println("---  Coût total de la main-d'œuvre avant TVA      : " + String.format("%.2f", totalLaborCostBeforeVAT) + " €**");
        System.out.println("---  Coût total de la main-d'œuvre avec TVA  : " + String.format("%.2f", totalLaborCostWithVAT) + " €**");


        double totalCostBeforeMargin = totalMaterialCostBeforeVAT + totalLaborCostBeforeVAT;
        double profitMarginValue = totalCostBeforeMargin * (currentProject.getProfitMargin() / 100.0);
        double finalProjectCost = totalCostBeforeMargin + profitMarginValue;

        System.out.println("3. Coût total avant  le Marge bénéficiaire : " + String.format("%.2f", totalCostBeforeMargin) + " €");
        System.out.println("4. Marge bénéficiaire (" + currentProject.getProfitMargin() + "%): " + String.format("%.2f", profitMarginValue) + " €");
        System.out.println(" --- Coût total final du projet : " + String.format("%.2f", finalProjectCost) + " €**");

        InputUtils.pause("Appuyez sur Entrée pour continuer...");

        String response = InputUtils.readString("  ce projet appaetient à  : " + currentProject.getClient().getName() + " ce client est  " + (currentProject.getClient().isProfessional() ? "professionnel" : "particulier") + " voulez-vous appliquer une remise ? (y/n) : ");
        if ("y".equalsIgnoreCase(response)) {
            double discount = InputUtils.readDouble("Entrez le montant de la remise (en %) : ");
            finalProjectCost = finalProjectCost - (finalProjectCost * discount / 100);
            quoteView.generateQuoteProoject(currentProject , finalProjectCost);
        } else {
            System.out.println("Opération annulée.");
        }


    }










}
