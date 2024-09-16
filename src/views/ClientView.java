package views;

import domain.entities.*;
import services.ClientService;
import services.ComponentService;
import services.ProjectService;
import utils.InputUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientView {


    private ClientService clientService ;
    private ProjectView projectView;

    public ClientView(){
        this.clientService = new ClientService();
        this.projectView = new ProjectView();
    }

    public void chercheOrAjouterClient() {
        boolean exit = false;
        while (!exit) {

            System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
            System.out.println(" 1. Chercher un client existant");
            System.out.println(" 2. Ajouter un nouveau client");
            int choice = InputUtils.readInt("Choisissez une option  : ");

            switch (choice) {
                case 1:
                    findExistClient();
                    exit = true;
                    break;
                case 2:
                    createNewClient();
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

        client.ifPresentOrElse(
                cli -> {
                    System.out.println("Client trouvé !");
                    System.out.println("Nom : " + cli.getName());
                    System.out.println("Adresse : " + cli.getAddress());
                    System.out.println("Numéro de téléphone : " + cli.getPhone());
                    String response = InputUtils.readString("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                    if ("y".equalsIgnoreCase(response)) {
                        projectView.createProject(cli);
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("Client non trouvé.")
        );
    }

    public void createNewClient() {
        System.out.println("--- ajouter nouveau  client  ---");
        String name= InputUtils.readString("Entrez le nom du client  :  ");
        String address= InputUtils.readString("Entrez l'address du client  :  ");
        String phone= InputUtils.readString("Entrez le number du  client  :  ");
        String isProfessionalInput = InputUtils.readString("Le client est-il professionnel ? (y/n) : ");
        boolean isProfessional = "y".equalsIgnoreCase(isProfessionalInput);

        Optional<Client> client=  clientService.addClient(new Client(name,address,phone,isProfessional));
        String response = InputUtils.readString("Souhaitez-vous continuer avec ce client ? (y/n) : ");
        if ("y".equalsIgnoreCase(response)) {
            projectView.createProject(client.get());
        } else {
            System.out.println("Opération annulée.");
        }
    }


    public void displayProjects() {
        try {
            List<Client> clients = clientService.getAllClientsWithProjects();

            for (Client client : clients) {
                System.out.println("Client ID: " + client.getId());
                System.out.println("Client Name: " + client.getName());
                System.out.println("Client Address: " + client.getAddress());
                System.out.println("Client Phone: " + client.getPhone());
                System.out.println("Is Professional: " + client.isProfessional());
                System.out.println("-----");

                for (Project project : client.getListProjects()) {
                    System.out.println("  Project ID: " + project.getId());
                    System.out.println("  Project Name: " + project.getName());
                    System.out.println("  Profit Margin: " + project.getprofitMargin());
                    System.out.println("  Total Cost: " + project.gettotalCost());
                    System.out.println("  Surface: " + project.getSurface());
                    System.out.println("  Status: " + project.getStatus());
                    System.out.println("  -----");

                    for (Component component : project.getListComponents()) {
                        System.out.println("    Component ID: " + component.getId());
                        System.out.println("    Component Name: " + component.getName());
                        System.out.println("    Component Type: " + component.getComponentType());
                        System.out.println("    VAT Rate: " + component.getVatRate());
                        System.out.println("    -----");

                        for (Material material : component.getMaterials()) {
                            System.out.println("      Material ID: " + material.getId());
                            System.out.println("      Unit Cost: " + material.getunitCost());
                            System.out.println("      Quantity: " + material.getQuantity());
                            System.out.println("      Transport Cost: " + material.getTransportCost());
                            System.out.println("      Quality Coefficient: " + material.getqualityCoefficient());
                            System.out.println("      -----");
                        }

                        for (Labor labor : component.getLabors()) {
                            System.out.println("      Labor ID: " + labor.getId());
                            System.out.println("      Hourly Rate: " + labor.gethourlyRate());
                            System.out.println("      Work Hours: " + labor.getworkHours());
                            System.out.println("      Worker Productivity: " + labor.getWorkerProductivity());
                            System.out.println("      -----");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving client and project data: " + e.getMessage());
        }
    }







}
