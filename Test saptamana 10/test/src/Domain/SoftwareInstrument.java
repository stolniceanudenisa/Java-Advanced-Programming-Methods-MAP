package Domain;

import java.io.Serializable;

public class SoftwareInstrument extends FlightInstrument implements Serializable {
    private int version;

    public SoftwareInstrument(String code, boolean maintenance, int version) {
        super(code, maintenance);
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isMaintenance() {
        return maintenance;
    }
    public double getPrice() {
        int price = 0;
        if (version < 10) {
            price = 100;
        } else
            price = 200;
        if (isMaintenance())
            price = price * 2;
        return price;
    }

    @Override
    public String toString() {
        return "SoftwareInstrument: " +
                " code = '" + code + '\'' +
                ", maintenance = " + maintenance +
                ", version = " + version
                + ", price = " + getPrice();
    }

}