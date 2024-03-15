package Domain;

public class BMI extends HealthData{
    private float value;

    public BMI(String date, float value) {
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
        return value > 18.5 && value < 25;
    }

    @Override
    public String toString() {
        return "BMI: " +
                "value = " + value +
                ", date = '" + date + '\'' ;
    }
}