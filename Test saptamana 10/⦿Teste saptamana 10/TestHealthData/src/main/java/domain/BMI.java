package domain;

public class BMI extends HealthData {
    private float value;

    public BMI(String date, float value) {
        super(date);
        this.value = value;
    }
    @Override
    public boolean isNormalValue() {
        if (value > 18.5 && value < 25)
            return true;
        return false;
    }
    @Override
    public String toString(){
        return "BMI :" + date + "|" + value;
    }
}
