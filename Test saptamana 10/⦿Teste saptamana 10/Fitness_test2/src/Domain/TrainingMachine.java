package Domain;

public class TrainingMachine {
    protected int serialNumber;
    protected boolean maintenance;

    public TrainingMachine(int serialNumber, boolean maintenance) {
        this.serialNumber = serialNumber;
        this.maintenance = maintenance;
    }

    public int getPrice() {
        return 0;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public String toString() {
        return "TrainingMachine:" +
                "serialNumber = " + serialNumber +
                ", maintenance = " + maintenance +
                ", price = " + getPrice();
    }


}