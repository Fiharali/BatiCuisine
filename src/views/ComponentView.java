package views;

import domain.entities.Client;
import domain.entities.Component;
import domain.entities.Project;
import services.ComponentService;
import utils.InputUtils;

import java.util.Optional;


public class ComponentView {

    public ComponentService componentService;

    public ComponentView() {
        this.componentService = new ComponentService();
    }

    public static   Component createComponent() {
        String componentName = InputUtils.readString("Entrez le nom du composant : ");
        double tva = InputUtils.readDouble("Entrez la TVA : ");
        return new Component(componentName, "material", tva);
    }


    public  void displayComponent(Component component) {
        System.out.println("Nom du composant : " + component.getName());
      //  System.out.println("TVA : " + component.getTva());
    }

    public  void   manageComponent() {

        boolean exit = false;
        while (!exit) {

            System.out.println("\n  === Menu de projects ===");
            System.out.println("\n 1. supprimer une composant ");
            System.out.println("\n 2. afficher une composant  ");
            //System.out.println("\n 3. Modifier un projet  ");
            System.out.println("\n 0. retour");

            int choice = InputUtils.readInt("Choisissez une option :");
            switch (choice) {
                case 1:
                    deleteComponent();
                    break;
                case 2:
                    displayComponent();
                    break;
                case 0:
                    exit = true;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }



    public void displayComponent(){
        int id= InputUtils.readInt("Entrez le id du component  :  ");
        Optional<Component> component = componentService.findById(id);
        component.ifPresentOrElse(
                comp -> {
                    System.out.println("component trouvé ! -----------------");
                    System.out.println("Nom : " + comp.getName());
                    System.out.println("Type : " + comp.getComponentType());
                    System.out.println("Tva: " + comp.getVatRate());
                },
                () -> System.out.println("component non trouvé.")
        );
    }


    public void deleteComponent(){
        int id= InputUtils.readInt("Entrez le id du component  :  ");
        Optional<Component> component = componentService.findById(id);
        component.ifPresentOrElse(
                comp -> {
                    System.out.println("component trouvé ! -----------------");
                    System.out.println("Nom : " + comp.getName());
                    System.out.println("Type : " + comp.getComponentType());
                    System.out.println("Tva: " + comp.getVatRate());
                    String response = InputUtils.readString("Souhaitez-vous supprimé  cette  component ? (y/n) : ");
                    if ("y".equalsIgnoreCase(response)) {
                        boolean isDeleted = componentService.deleteComponent(comp);
                        if (isDeleted) {
                            System.out.println("component été supprimé !");
                        } else {
                            System.out.println("component ne peut pas être supprimé !");
                        }
                    } else {
                        System.out.println("Opération annulée.");
                    }
                },
                () -> System.out.println("component non trouvé.")
        );

    }

}
