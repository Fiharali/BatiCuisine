package views;

import domain.entities.Project;
import domain.entities.Quote;
import domain.enums.ProjectStatus;
import exceptions.ProjectsNotFoundException;
import services.ProjectService;
import services.QuoteService;
import utils.InputUtils;

import java.time.LocalDate;
import java.util.Optional;

public class QuoteView {

    //private ProjectView projectView;
    private QuoteService quoteService ;
    private ProjectService projectService ;

    public QuoteView() {
      //  this.projectView = new ProjectView();
        this.quoteService = new QuoteService();
        this.projectService = new ProjectService();
    }



    public  void  addQuoteInputs(){

        System.out.println("--- Enregistrement du Devis--");

        String projectName = InputUtils.readString("enter nom  du projet :");
        LocalDate issueDate = InputUtils.readDate("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        LocalDate validateDate = InputUtils.readDate("Entrez la date validité du devis (format : jj/mm/aaaa) :");
        int  amount= InputUtils.readInt("enter la valeur du devis :");
        String response = InputUtils.readString("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        if ("y".equalsIgnoreCase(response)) {
            addQuote(projectName,issueDate,validateDate,amount);
        } else {
            System.out.println("Opération annulée.");
        }

    }


    public void addQuote(String projectName, LocalDate issueDate , LocalDate validateDate , int amount ){
        ProjectView projectView = new ProjectView();
        try {
         Optional<Project> project = projectView.findfByName(projectName);
        project.ifPresentOrElse((proj -> {

            Optional<Quote> quote = quoteService.addQuote(new Quote(amount, issueDate, validateDate, false, proj));
            quote.ifPresentOrElse(
                    q -> System.out.println("Devis enregistré avec succès !"),
                    () -> System.out.println("Le devis n'a pas été ajouté.")
            );

         }), ()->System.out.println(" projct avec le nom  :  " + projectName +"  n'exist pas ")    );

        } catch (ProjectsNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }


    public  void generateQuoteProoject(Project project , double amount){

        try {
            LocalDate issueDate = InputUtils.readDate("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
            LocalDate validateDate =  InputUtils.readEndDateAfterStart( "Entrez la date de validité du devis (format : jj/mm/aaaa) : " , issueDate);
            System.out.println("Devis ------------------------------------ ");
            System.out.println("Montant : " + amount);
            System.out.println("Date d'émission : " + issueDate);
            System.out.println("Date de validité : " + validateDate);

            Quote quote = new Quote(amount, issueDate, validateDate, false, project);

            String response = InputUtils.readString("Vous voulez accepter ce devis ? (y/n) :  ");
            if ("y".equalsIgnoreCase(response)) {
                quote.setAccepted(true);
                project.setStatus(ProjectStatus.COMPLETED);
                projectService.updateProject(project);
                quoteService.addQuote(quote);
            } else {
                project.setStatus(ProjectStatus.CANCELED);
                quoteService.addQuote(quote);
            }


               // project.setQuote(quote.get());



        } catch (ProjectsNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }




    public void  manageQoute(){


        boolean exit = false;
        while (!exit) {

            System.out.println("\n  === Menu de clients ===");
            System.out.println("\n 1. supprimer un devis  ");
            System.out.println("\n 2. afficher un devis  ");
           // System.out.println("\n 3. Modifier un client  ");
            System.out.println("\n 0. retour");

            int choice = InputUtils.readInt("Choisissez une option :");
            switch (choice) {
                case 1:
                    deleteQoute();
                    break;
                case 2:
                    displayQoute();
                    break;
                case 0:
                    exit = true;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }


    public void displayQoute(){
        int id= InputUtils.readInt("Entrez le id  du devis  :  ");
        Optional<Quote> quote = quoteService.findById(id);
        quote.ifPresentOrElse(
                quot -> {
                    System.out.println("devis trouvé !");
                    System.out.println(" id : " + quot.getId());
                    System.out.println(" date de issue  : " + quot.getIssueDate());
                    System.out.println(" date de validité  : " + quot.getValidatedDate());
                    System.out.println(" amount  : " + quot.getEstimatedAmount());
                    System.out.println( quot.isAccepted()? "devis accepté" : "devis non accepté");
                },
                () -> System.out.println("devis non trouvé.")
        );
    }



    public void deleteQoute(){
        int id= InputUtils.readInt("Entrez le id  du devis  :  ");
        Optional<Quote> quote = quoteService.findById(id);
        quote.ifPresentOrElse(
                quot -> {
                    System.out.println("devis trouvé !");
                    System.out.println(" id : " + quot.getId());
                    System.out.println(" date de issue  : " + quot.getIssueDate());
                    System.out.println(" date de validité  : " + quot.getValidatedDate());
                    System.out.println(" amount  : " + quot.getEstimatedAmount());
                    System.out.println( quot.isAccepted()? "devis accepté" : "devis non accepté");
                    String response = InputUtils.readString("Souhaitez-vous supprimé  ce devis ? (y/n) : ");
                    if ("y".equalsIgnoreCase(response)) {
                        boolean isDeleted = quoteService.deleteQuote(quot);
                        if (isDeleted) {
                            System.out.println("devis supprimé !");
                        } else {
                            System.out.println("devis ne peut pas être supprimé !");
                        }
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("devis non trouvé.")
        );

    }


}
