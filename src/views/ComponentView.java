package views;

import domain.entities.Component;
import utils.InputUtils;



public class ComponentView {

    public  Component createComponent() {
        String componentName = InputUtils.readString("Entrez le nom du composant : ");
        double tva = InputUtils.readDouble("Entrez la TVA : ");
        return new Component(componentName, "material", tva);
    }
}
