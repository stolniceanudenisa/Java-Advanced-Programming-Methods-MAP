package domain;

public class BP extends HealthData{
    private int systolicValue;
    private int diastolicValue;

    public BP(String date, int systolicValue , int diastolicValue){
        super(date);
        this.systolicValue = systolicValue;
        this.diastolicValue = systolicValue;
    }
    @Override
    public boolean isNormalValue() {
        if(120 < systolicValue && systolicValue < 130 && 60 < diastolicValue && diastolicValue > 80)
            return true;
        return false;
    }
    @Override
    public String toString() {
        return "BP :" + date + "|" + systolicValue + "|" + diastolicValue;
    }

}
