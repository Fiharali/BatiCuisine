package views;

import utils.InputUtils;

public class Menu {

    public ClientView clientView;
    public ProjectView projectView;
    public QuoteView quoteView;


public  Menu(){
     clientView = new ClientView();
     projectView = new ProjectView();
     quoteView = new QuoteView();
}

    public   void mainMenu()  {
        boolean exit = false;
        while (!exit) {

            System.out.println("\n  === Menu Principal ===");
            System.out.println("\n 1. Créer un nouveau projet ");
            System.out.println("\n 2. Afficher les projets existants");
            System.out.println("\n 3. Calculer le coût d'un projet");
            System.out.println("\n 4. Enregistrer  un Devis");
            System.out.println("\n 5. Gestion des projets, clients ");
            System.out.println("\n 0. Quitter");
            int choice = InputUtils.readInt("Choisissez une option :");
            switch (choice) {

                case 1:
                    clientView.searchOrAjouterClient();
                    break;
                case 2:
                    clientView.displayClientWithProjects();
                    break;
                case 4:
                    quoteView.addQuoteInputs();
                    break;
                  case 5:
                      choiceManage();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }




  public void   choiceManage(){

      boolean exit2 = false;
      while (!exit2) {

          System.out.println("\n  === Menu Principal ===");
          System.out.println("\n 1. Créer les clients  ");
          System.out.println("\n 2. Créer les projects  ");
          System.out.println("\n 0. retour");

          int choice2 = InputUtils.readInt("Choisissez une option :");
          switch (choice2) {
              case 1:
                  clientView.manageClient();
                  break;
              case 2:
                 // clientView.displayClientWithProjects();
                  break;
              case 0:
                  exit2 = true;
              default:
                  System.out.println("Option invalide. Veuillez réessayer.");
                  break;
          }
      }
    }
}