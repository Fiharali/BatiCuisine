package views;

import domain.entities.*;
import services.ClientService;
import services.ComponentService;
import services.ProjectService;
import utils.InputUtils;

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
        String isProfessionnelInput = InputUtils.readString("Le client est-il professionnel ? (y/n) : ");
        boolean isProfessionel = "y".equalsIgnoreCase(isProfessionnelInput);

        Optional<Client> client=  clientService.addClient(new Client(name,address,phone,isProfessionel));
        String response = InputUtils.readString("Souhaitez-vous continuer avec ce client ? (y/n) : ");
        if ("y".equalsIgnoreCase(response)) {
            projectView.createProject(client.get());
        } else {
            System.out.println("Opération annulée.");
        }
    }






}
