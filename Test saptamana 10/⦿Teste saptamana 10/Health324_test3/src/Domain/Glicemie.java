package Domain;

public class Glicemie extends HealthData{
    private float value;

    public Glicemie(String date, float value) {
        super(date);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        if (value > 0) {
            this.value = value;
        }
    }

    public boolean isNormalValue() {
        return value > 70 && value < 100;
    }

    @Override
    public String toString() {
        return "Glicemie: " +
                "value = " + value +
                ", date = '" + date + '\'';
    }

}