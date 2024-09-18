package views;

import domain.entities.*;
import services.ClientService;
import utils.InputUtils;

import java.util.List;
import java.util.Optional;

public class ClientView {


    private ClientService clientService ;
    private ProjectView projectView;

    public ClientView(){
        this.clientService = new ClientService();
        this.projectView = new ProjectView();
    }

    public void searchOrAjouterClient() {
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


    public void displayClientWithProjects() {
        try {
            List<Client> clients = clientService.getAllClientsWithProjects();

            for (Client client : clients) {
                System.out.println("\u001B[32m +-----------------+-----------------------+----------------------+----------------------+----------------------+");
                System.out.println("| Client ID  : " + client.getId()
                        + "        | Client Name  :" + client.getName()
                        + "           | Client Address  :" + client.getAddress()
                        + "     | Client Phone  : " + client.getPhone()
                        + "        | Is Professional :  " + (client.isProfessional() ? "Yes    " : "No  ") );
                System.out.println("+-----------------+-----------------------+----------------------+----------------------+----------------------+");
                System.out.println("\u001B[0m|");

                for (Project project : client.getListProjects()) {
                    System.out.println("\u001B[34m +-----------------+-----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
                    System.out.println("| Project ID : " + project.getId()
                            + "      | Project Name : " + project.getName()
                            + "          |Profit Margin : " + project.getProfitMargin()
                            + "   | Total Cost : " + project.getTotalCost()
                            + "   | mSurface : " + project.getSurface()
                            + "   | Status : " + project.getStatus() );
                    System.out.println("+-----------------+-----------------------+----------------------+----------------------+----------------------+----------------------+----------------------+");
                    System.out.println("\u001B[0m|");

                    for (Component component : project.getListComponents()) {
                        System.out.println("\u001B[33m +-----------------+-----------------------+----------------------+----------------------+----------------------+");
                        System.out.println("| Component ID : " + component.getId()
                                + "      | Component Name : " + component.getName()
                                + "          | Component Type : " + component.getComponentType()
                                + "   | VAT Rate : " + component.getVatRate());
                        System.out.println("+-----------------+-----------------------+----------------------+----------------------+----------------------+");
                        System.out.println("\u001B[0m|");

                        // Material
                        Material material = component.getMaterial();
                        if (material != null) {
                            System.out.println("\u001B[35m +-----------------+-----------------------+----------------------+----------------------+----------------------+");
                            System.out.println("| Material ID : " + material.getId()
                                    + "      | Unit Cost : " + material.getUnitCost()
                                    + "          | Quantity : " + material.getQuantity()
                                    + "   | Transport Cost : " + material.getTransportCost()
                                    + "   | Quality Coefficient : " + material.getQualityCoefficient());
                            System.out.println("+-----------------+-----------------------+----------------------+----------------------+----------------------+");
                            System.out.println("\u001B[0m|");
                        }

                        // Labor
                        Labor labor = component.getLabor();
                        if (labor != null) {
                            System.out.println("\u001B[36m +-----------------+-----------------------+----------------------+----------------------+");
                            System.out.println("| Labor ID : " + labor.getId()
                                    + "      | Hourly Rate : " + labor.getHourlyRate()
                                    + "          | Work Hours : " + labor.getWorkHours()
                                    + "   | Worker Productivity : " + labor.getWorkerProductivity());
                            System.out.println("+-----------------+-----------------------+----------------------+----------------------+");
                            System.out.println("\u001B[0m|");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving client and project data: " + e.getMessage());
        }
    }


    public void  manageClient(){


        boolean exit = false;
        while (!exit) {

            System.out.println("\n  === Menu de clients ===");
            System.out.println("\n 1. supprimer un client  ");
            System.out.println("\n 2. afficher un client  ");
            System.out.println("\n 3. Modifier un client  ");
            System.out.println("\n 0. retour");

            int choice = InputUtils.readInt("Choisissez une option :");
            switch (choice) {
                case 1:
                    deleteClient();
                    break;
                case 2:
                   displayClient();
                    break;
                case 0:
                    exit = true;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }




    public void displayClient(){
        String name= InputUtils.readString("Entrez le nom du client  :  ");
        Optional<Client> client = clientService.findClientByName(name);
        client.ifPresentOrElse(
                cli -> {
                    System.out.println("Client trouvé !");
                    System.out.println("Nom : " + cli.getName());
                    System.out.println("Adresse : " + cli.getAddress());
                    System.out.println("Numéro de téléphone : " + cli.getPhone());
                },
                () -> System.out.println("Client non trouvé.")
        );
    }


    public void deleteClient(){
        String name= InputUtils.readString("Entrez le nom du client  :  ");
        Optional<Client> client = clientService.findClientByName(name);
        client.ifPresentOrElse(
                cli -> {
                    String response = InputUtils.readString("Souhaitez-vous supprimé  ce client ? (y/n) : ");
                    if ("y".equalsIgnoreCase(response)) {
                        boolean isDeleted = clientService.deleteClient(cli);
                        if (isDeleted) {
                            System.out.println("Client supprimé !");
                        } else {
                            System.out.println("Client ne peut pas être supprimé !");
                        }
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("Client non trouvé.")
        );

    }

}














