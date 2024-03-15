package Domain;

public abstract class HealthData {
    protected String date;

    public HealthData(String date) {
        this.date = date;
    }

    public abstract boolean isNormalValue(float value);

    public abstract boolean isNormalValue(BP bp);

    public abstract String toString();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}