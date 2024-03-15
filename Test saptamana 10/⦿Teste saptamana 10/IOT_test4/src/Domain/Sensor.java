package Domain;

public class Sensor {
    protected String producer;
    protected double last_recording;

    public Sensor(String producer, double last_recording) {
        this.producer = producer;
        this.last_recording = last_recording;
    }

    public String getProducer() {
        return producer;
    }

    public double getLast_recording() {
        return last_recording;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setLast_recording(double last_recording) {
        this.last_recording = last_recording;
    }

    public int getPrice() {
        return 0;
    }

    public boolean sendAlert() {
        return false;
    }

    public String toString() {
        return "Sensor: " +
                "producer = " + producer +
                ", last_recording = " + last_recording;
    }
}