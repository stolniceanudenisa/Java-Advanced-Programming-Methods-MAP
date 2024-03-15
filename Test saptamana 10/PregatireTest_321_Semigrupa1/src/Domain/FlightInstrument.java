package Domain;

public class FlightInstrument {
    protected String code;
    protected boolean maintenance;

    public FlightInstrument(String code, boolean maintenance) {
        this.code = code;
        this.maintenance = maintenance;
    }

    public double getPrice() {
        return 0;
    }

    public String getCode() {
        return code;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public String toString() {
        return "FlightInstrument{" +
                "code='" + code + '\'' +
                ", maintenance=" + maintenance +
                '}';
    }
}
