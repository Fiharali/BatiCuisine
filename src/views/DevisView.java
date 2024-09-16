package views;

import domain.entities.Project;
import domain.entities.Quote;
import services.DevisService;
import utils.InputUtils;

import java.time.LocalDate;
import java.util.Optional;

public class DevisView {

    private ProjectView projectView;
    private DevisService devisService;

    public DevisView() {
        this.projectView = new ProjectView();
        this.devisService = new DevisService();
    }



    public  void  ajouteQuote(){
        System.out.print("--- Enregistrement du Devis--");
        int  projectId= InputUtils.readInt("enter id du projet ");
        LocalDate issueDate = InputUtils.readDate("Entrez la date d'émission du devis (format : jj/mm/aaaa)");
        LocalDate validateDate = InputUtils.readDate("Entrez la date validité du devis (format : jj/mm/aaaa)");
        int  amount= InputUtils.readInt("enter la valeur du devis ");
        String response = InputUtils.readString("Souhaitez-vous enregistrer le devis ? (y/n) : ");
        if ("y".equalsIgnoreCase(response)) {
             Optional<Project> project = projectView.getProject(projectId);
            project.ifPresentOrElse(
                    proj -> {
                        Optional<Quote> quote = devisService.addDevis(new Quote(amount,issueDate,validateDate,false,proj));
                        quote.ifPresentOrElse(
                                q -> System.out.println("Devis enregistré avec succès !"),
                                () -> System.out.println("Devis ne pas ajouter." + projectId + project.get().getName())
                        );
                    },
                    () -> {
                        System.out.println("Project n'existe pas.");
                    }
            );
        } else {
            System.out.println("Opération annulée.");
        }

    }
}
