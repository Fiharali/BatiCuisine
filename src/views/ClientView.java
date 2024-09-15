package views;

import domain.entities.Client;
import domain.entities.Material;
import domain.entities.Project;
import services.ClientService;
import utils.InputUtils;

import java.util.Optional;

public class ClientView {


    private ClientService clientService = new ClientService();
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
                       createProject();
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("Client non trouvé.")
        );
    }

public void  createProject(){
    System.out.println("--- Création d'un Nouveau Projet ---");
    String projectName= InputUtils.readString("Entrez le nom du projet : ");
    double surface= InputUtils.readDouble("Entrez la surface de la cuisine (en m²) : ");
    Project project =  new Project();
    project.setSurface(surface);
    project.setName(projectName);

    boolean addingMaterials = true;
    while (addingMaterials) {
        System.out.println("--- Ajout des matériaux ---");
        String materialName = InputUtils.readString("Entrez le nom du matériau : ");
        double quantity = InputUtils.readDouble("Entrez la quantité de ce matériau (en m² ou en litres) : ");
        double unitCost = InputUtils.readDouble("Entrez le coût unitaire de ce matériau (€/m² ou €/litre) : ");
        double transportCost = InputUtils.readDouble("Entrez le coût de transport de ce matériau (€) : ");
        double qualityCoefficient = InputUtils.readDouble("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");

        Material material = new Material(materialName,"material", quantity, unitCost, transportCost, qualityCoefficient);
        project.addComponent(material);
        System.out.println("Matériau ajouté avec succès !");

        String response = InputUtils.readString("Voulez-vous ajouter un autre matériau ? (y/n) : ");
        if ("n".equalsIgnoreCase(response)) {
            addingMaterials = false;
        }
    }
}

}
