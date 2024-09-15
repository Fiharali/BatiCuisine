package domain.entities;

public class Material extends Component{
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double qualityCoefficient;
    private Component component;



    public Material(String name, String componentType, double unitCost, double quantity, double transportCost, double qualityCoefficient) {
        super(name, componentType);
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
    }

    public Material() {
    }

    public double getunitCost() {
        return unitCost;
    }

    public void setunitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTransportCost() {
        return transportCost;
    }

    public void setTransportCost(double transportCost) {
        this.transportCost = transportCost;
    }

    public double getqualityCoefficient() {
        return qualityCoefficient;
    }

    public void setqualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    @Override
    public String toString() {
        return "Material{" +
                "unitCost=" + unitCost +
                ", quantity=" + quantity +
                ", transportCost=" + transportCost +
                ", qualityCoefficient=" + qualityCoefficient +
                '}';
    }
}
