package Domain;

import java.io.Serializable;

public class FlightInstrument implements Serializable {
    protected String code;
    protected boolean maintenance;

    public FlightInstrument(String code, boolean maintenance) {
        this.code = code;
        this.maintenance = maintenance;
    }

    public String getCode() {
        return code;
    }

    public boolean isMaintenance() {
        return maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public double getPrice() {
        return 0;
    }


    @Override
    public String toString() {
        return "FlightInstrument: " +
                " code = '" + code + '\'' +
                ", maintenance = " + maintenance;
    }
}