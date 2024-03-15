package Domain;

public class BP extends HealthData {
    public int systolicValue;
    public int diastolicValue;


    public BP(String date, int systolicValue, int diastolicValue) {
        super(date);
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
    }

    public int getSystolicValue() {
        return systolicValue;
    }

    public void setSystolicValue(int systolicValue) {
        this.systolicValue = systolicValue;
    }

    public int getDiastolicValue() {
        return diastolicValue;
    }

    public void setDiastolicValue(int diastolicValue) {
        this.diastolicValue = diastolicValue;
    }

    @Override
    public boolean isNormalValue(float value) {
        return false;
    }

    @Override
    public boolean isNormalValue(BP bp) {
        if (bp.getSystolicValue() >= 100 && bp.getSystolicValue() <= 130 &&
                bp.getDiastolicValue() >= 60 && bp.getDiastolicValue() <= 80)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "BP{" +
                "systolicValue=" + systolicValue +
                ", diastolicValue=" + diastolicValue +
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
