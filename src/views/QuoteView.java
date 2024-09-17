package views;

import domain.entities.Project;
import domain.entities.Quote;
import services.QuoteService;
import utils.InputUtils;

import java.time.LocalDate;
import java.util.Optional;

public class QuoteView {

    private ProjectView projectView;
    private QuoteService quoteService;

    public QuoteView() {
        this.projectView = new ProjectView();
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
        Optional<Project> project = projectView.finfByName(projectName);
        project.ifPresent(proj -> {
            Optional<Quote> quote = quoteService.addQuote(new Quote(amount, issueDate, validateDate, false, proj));
            quote.ifPresentOrElse(
                    q -> System.out.println("Devis enregistré avec succès !"),
                    () -> System.out.println("Le devis n'a pas été ajouté.")
            );
        });

    }
}
