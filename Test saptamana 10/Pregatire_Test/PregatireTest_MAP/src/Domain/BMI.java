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
        this.value = value;
    }

    @Override
    public boolean isNormalValue(float value) {
        if (value >= 18.5 && value <= 25)
            return true;
        return false;
    }

    @Override
    public boolean isNormalValue(BP bp) {
        return false;
    }

    @Override
    public String toString() {
        return "BMI{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public String getDate() {
        return super.getDate();
    }

    @Override
    public void setDate(String date) {
        super.setDate(date);
    }
}
