package Domain;

public class BP extends HealthData{
    private Integer systolic;
    private Integer diastolic;

    public BP(String date, Integer systolic, Integer diastolic) {
        super(date);
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        if (systolic > 0) {
            this.systolic = systolic;
        }
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        if (diastolic > 0) {
            this.diastolic = diastolic;
        }
    }

    public boolean isNormalValue() {
        return systolic > 100 && systolic < 130 && diastolic > 60 && diastolic < 80;
    }

    @Override
    public String toString() {
        return "BP: " +
                "systolic = " + systolic +
                ", diastolic = " + diastolic +
                ", date = '" + date + '\'';
    }
}