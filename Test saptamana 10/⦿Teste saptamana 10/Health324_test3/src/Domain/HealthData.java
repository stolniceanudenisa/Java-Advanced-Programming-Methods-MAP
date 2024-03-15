package Domain;

public class HealthData {
    protected String date;

    public HealthData(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if (date != null) {
            this.date = date;
        }
    }

    public boolean isNormalValue() {
        return false;
    }


    @Override
    public String toString() {
        return "HealthData: " +
                "date = '" + date + '\'' +
                '}';
    }
}