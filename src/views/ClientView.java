package views;

import domain.entities.*;
import services.ClientService;
import services.ComponentService;
import services.ProjectService;
import utils.InputUtils;

import java.util.Optional;

public class ClientView {


    private ClientService clientService ;
    private ProjectService projectService;
    private ComponentService componentService;

    public ClientView(){
        this.clientService = new ClientService();
        this.projectService = new ProjectService();
        this.componentService = new ComponentService();
    }
    public void chercheOrAjouterClient() {
        boolean exit = false;
        while (!exit) {

            System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
            System.out.println(" 1. Chercher un client existant");
            System.out.println(" 2. Ajouter un nouveau client");
            System.out.println(" 3. Ajouter un nouveau client");

            int choice = InputUtils.readInt("Choisissez une option  : ");

            switch (choice) {
                case 1:
                    findExistClient();
                    exit = true;
                    break;
                case 2:
                    System.out.println("222");
                    exit = true;
                    break;
                default:
                    System.out.println("Option invalide, veuillez choisir 1 ou 2.");
            }
        }
    }


    public void findExistClient() {
        System.out.println("--- Recherche de client existan ---");
        String name= InputUtils.readString("Entrez le nom du client  :  ");
        Optional<Client> client = clientService.findClientByName(name);
      //  clientService.allClients().forEach(System.out::println);
        client.ifPresentOrElse(
                cli -> {
                    System.out.println("Client trouvé !");
                    System.out.println("Nom : " + cli.getName());
                    System.out.println("Adresse : " + cli.getAddress());
                    System.out.println("Numéro de téléphone : " + cli.getPhone());
                    String response = InputUtils.readString("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                    if ("y".equalsIgnoreCase(response)) {
                       createProject(cli);
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("Client non trouvé.")
        );
    }

public void  createProject(Client client) {
    System.out.println("--- Création d'un Nouveau Projet ---");
    String projectName= InputUtils.readString("Entrez le nom du projet : ");
    double surface= InputUtils.readDouble("Entrez la surface de la cuisine (en m²) : ");
    //double profit= InputUtils.readDouble("Entrez le profit margin : ");
   // double total= InputUtils.readDouble("Entrez le total  : ");
    Optional<Project> project = projectService.createProject(projectName, surface,0,0,client);
    project.ifPresent(this::addMaterialToProject);



}

    public void addMaterialToProject(Project project) {
        boolean addingMaterials = true;
        while (addingMaterials) {
            System.out.println("--- Ajout des matériaux ---");
            String componentName = InputUtils.readString("Entrez le nom du component : ");
            double tva = InputUtils.readDouble("Entrez le tva : ");
            String materialName = InputUtils.readString("Entrez le nom du matériau : ");
            double quantity = InputUtils.readDouble("Entrez la quantité de ce matériau (en m² ou en litres) : ");
            double unitCost = InputUtils.readDouble("Entrez le coût unitaire de ce matériau (€/m² ou €/litre) : ");
            double transportCost = InputUtils.readDouble("Entrez le coût de transport de ce matériau (€) : ");
            double qualityCoefficient = InputUtils.readDouble("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");

            Component component = new Component(componentName,"material",tva);
            Material material = new Material(materialName,"material",unitCost,quantity,transportCost,qualityCoefficient);
            boolean success = componentService.addComponentToProjectWithMaterial(project , material, component);

            System.out.println(success ? "Matériau ajouté avec succès !" : "Erreur lors de l'ajout du matériau.");

            String response = InputUtils.readString("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if ("n".equalsIgnoreCase(response)) {
                addingMaterials = false;
                addLaborToProject(project);
            }
        }
    }

    public void addLaborToProject(Project project) {
        boolean addingLabor = true;
        while (addingLabor) {
            System.out.println("--- Ajout  de la main-d'œuvre ---");
            String componentName = InputUtils.readString("Entrez le nom du component : ");
            double tva = InputUtils.readDouble("Entrez le tva : ");

            String name = InputUtils.readString("Entrez le nom du component  : ");
            double hourlyrate = InputUtils.readDouble("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double workhours = InputUtils.readDouble("Entrez le nombre d'heures travaillées: ");
            double productivity = InputUtils.readDouble("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            Component component = new Component(componentName,"material",tva);
            Labor labor = new Labor(name,"labor", workhours,hourlyrate, productivity);
            boolean success = componentService.addComponentToProjectWithLabor(project , labor, component);

            System.out.println(success ? "Matériau ajouté avec succès !" : "Erreur lors de l'ajout du matériau.");

            String response = InputUtils.readString("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if ("n".equalsIgnoreCase(response)) {
                addingLabor = false;
            }
        }
    }

}
