package views;

import utils.InputUtils;

public class Menu {



    public  static  void mainMenu()  {
        boolean exit = false;
        ClientView clientView = new ClientView();
        ProjectView projectView = new ProjectView();
        QuoteView quoteView = new QuoteView();

        while (!exit) {

            System.out.println("\n  === Menu Principal ===");
            System.out.println("\n 1. Créer un nouveau projet ");
            System.out.println("\n 2. Afficher les projets existants");
            System.out.println("\n 3. Calculer le coût d'un projet");
            System.out.println("\n >4. Enregistrer  un Devis");
            System.out.println("\n 5. Quitter");
            int choice = InputUtils.readInt("Choisissez une option :");
            switch (choice) {

                case 1:
                    clientView.chercheOrAjouterClient();
                    break;
                case 2:
                    clientView.displayProjects();
                    break;
                case 4:
                    quoteView.ajouteQuote();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
}