package views;

import domain.entities.Component;
import domain.entities.Labor;
import domain.entities.Project;
import services.ComponentService;
import services.LaborService;
import utils.InputUtils;

public class LaborView {

    public ComponentService componentService;
    public ComponentView componentView;
    //public QuoteView quoteView = new QuoteView();

    public LaborView(){
        componentService = new ComponentService();
        componentView = new ComponentView();
       // quoteView = new QuoteView();
    };

    public Labor createLabor(Component component) {
        System.out.println("--- Ajout  de la main-d'œuvre ---");
        double hourlyRate = InputUtils.readDouble("Entrez le taux horaire (€/h) : ");
        double workHours = InputUtils.readDouble("Entrez le nombre d'heures travaillées : ");
        double productivity = InputUtils.readDouble("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
        return new Labor(component.getName(), "labor", workHours, hourlyRate, productivity);
    }



    public void addLaborToProject(Project project  ) {
        boolean addingLabor = true;
        while (addingLabor) {
            Component component = componentView.createComponent();
            Labor labor = createLabor(component);
            boolean success = componentService.addComponentToProjectWithLabor(project , labor, component);
            System.out.println(success ? "Matériau ajouté avec succès !" : "Erreur lors de l'ajout du matériau.");

            String response = InputUtils.readString("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if ("n".equalsIgnoreCase(response)) {
                addingLabor = false;

                QuoteView quoteView = new QuoteView();
                quoteView.addQuoteAfterCreateProoject(project);


            }
        }
    }
}
