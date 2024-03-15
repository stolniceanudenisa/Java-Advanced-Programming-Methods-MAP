package Domain;

public class SmokeSensor extends Sensor{

    int length;

    public SmokeSensor(String producer, double last_recording, int length) {
        super(producer, last_recording);
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length= length;
    }

    @Override
    public int getPrice() {
        return 25;
    }

    @Override
    public boolean sendAlert() {
        if (getLast_recording() > 1600)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "SmokeSensor: " +
                "length = " + length +
                ", producer = " + producer +
                ", last_recording = " + last_recording +
                ", price = " + getPrice();
    }
}