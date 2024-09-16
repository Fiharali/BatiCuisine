package domain.entities;

public class Labor extends Component{
    private double hourlyRate;
    private double workHours;
    private double workerProductivity;
    private Component component;


    public Labor(String name, String componentType, double hourlyRate, double workHours, double workerProductivity) {
        super(name, componentType);
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
        this.workerProductivity = workerProductivity;
    }

    public Labor() {
    }

    public double gethourlyRate() {
        return hourlyRate;
    }

    public void sethourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getworkHours() {
        return workHours;
    }

    public void setworkHours(double workHours) {
        this.workHours = workHours;
    }

    public double getWorkerProductivity() {
        return workerProductivity;
    }

    public void setWorkerProductivity(double workerProductivity) {
        this.workerProductivity = workerProductivity;
    }


    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    @Override
    public String toString() {
        return "WorkForce{" +
                "hourlyRate=" + hourlyRate +
                ", workHours=" + workHours +
                ", workerProductivity=" + workerProductivity +
                '}';
    }


}
