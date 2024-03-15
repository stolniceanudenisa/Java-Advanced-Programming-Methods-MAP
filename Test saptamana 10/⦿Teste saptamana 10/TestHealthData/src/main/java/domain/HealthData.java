package domain;

public abstract class HealthData {
    protected String date;

    public HealthData(String date){
        this.date = date;
    }
    public String getDate(){
        return date;
    }
    public abstract boolean isNormalValue();

    public abstract String toString();

}
