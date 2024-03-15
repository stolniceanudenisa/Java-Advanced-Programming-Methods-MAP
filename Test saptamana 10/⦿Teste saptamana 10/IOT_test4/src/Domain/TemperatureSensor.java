package Domain;

public class TemperatureSensor extends Sensor{
    private int diameter;

    public TemperatureSensor(String producer, double last_recording, int diameter) {
        super(producer, last_recording);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public int getPrice() {
        int price = 9;
        if (getDiameter() < 3)
            price = price + 8;
        return price;
    }

    @Override
    public boolean sendAlert() {
        if (getLast_recording() < 10 || getLast_recording() > 35)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "TemperatureSensor: " +
                "diameter = " + diameter +
                ", producer = " + producer +
                ", last_recording = " + last_recording +
                ", price = " + getPrice();
    }
}