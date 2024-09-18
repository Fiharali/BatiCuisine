package views;

import domain.entities.Component;
import domain.entities.Material;
import domain.entities.Project;
import services.ComponentService;
import utils.InputUtils;

public class MaterialView {

    public ComponentView componentView;
    public ComponentService componentService;
    public LaborView laborView;

    public MaterialView() {
        this.componentView = new ComponentView();
        this.componentService =new ComponentService() ;
        this.laborView = new LaborView();
    }

    public Material createMaterial() {
        String materialName = InputUtils.readString("Entrez le nom du matériau : ");
        double quantity = InputUtils.readDouble("Entrez la quantité (m² ou litres) : ");
        double unitCost = InputUtils.readDouble("Entrez le coût unitaire (€/m² ou €/litre) : ");
        double transportCost = InputUtils.readDouble("Entrez le coût de transport (€) : ");
        double qualityCoefficient = InputUtils.readDouble("Entrez le coefficient de qualité (1.0 = standard, > 1.0 = haute qualité) : ");
        return new Material(materialName, "material", unitCost, quantity, transportCost, qualityCoefficient);
    }


    public void addMaterialToProject(Project project) {
        boolean addingMaterials = true;
        while (addingMaterials) {
            System.out.println("--- Ajout des matériaux ---");
            Component component = componentView.createComponent();
            Material material = createMaterial();

            boolean success = componentService.addComponentToProjectWithMaterial(project , material, component);
            System.out.println(success ? "Matériau ajouté avec succès !" : "Erreur lors de l'ajout du matériau.");
            String response = InputUtils.readString("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if ("n".equalsIgnoreCase(response)) {
                addingMaterials = false;
                laborView.addLaborToProject(project);
            }
        }
    }
}
