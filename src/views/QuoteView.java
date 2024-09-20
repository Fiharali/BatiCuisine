package views;

import domain.entities.Project;
import domain.entities.Quote;
import exceptions.ProjectsNotFoundException;
import services.QuoteService;
import utils.InputUtils;

import java.time.LocalDate;
import java.util.Optional;

public class QuoteView {

    //private ProjectView projectView;
    private QuoteService quoteService ;

    public QuoteView() {
      //  this.projectView = new ProjectView();
        this.quoteService = new QuoteService();
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
            LocalDate issueDate = LocalDate.now();
            LocalDate validateDate =  LocalDate.now().plusMonths(1);
            boolean isAccepted = "y".equalsIgnoreCase(InputUtils.readString("Vous voulez accepter ce devis ? (y/n) : "));

            Optional<Quote> quote = quoteService.addQuote(new Quote(amount, issueDate, validateDate, isAccepted, project));
            quote.ifPresentOrElse(
                    q -> System.out.println("Devis enregistré avec succès !"),
                    () -> System.out.println("Le devis n'a pas été ajouté.")
            );
            project.setQuote(quote.get());

        } catch (ProjectsNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
