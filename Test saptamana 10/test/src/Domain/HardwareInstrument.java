package Domain;

import java.io.Serializable;

public class HardwareInstrument extends FlightInstrument implements Serializable {
    private String measurementType;

    public HardwareInstrument(String code, boolean maintenance, String measurementType) {
        super(code, maintenance);
        this.measurementType = measurementType;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public double getPrice() {
        int price = 0;
        if (measurementType.equals("altitudine") || measurementType.equals("directie"))
            price = 50;
        else if (measurementType.equals("stare_motor"))
            price = 500;

        if (isMaintenance())
            price = price * 2;
        return price;
    }

    @Override
    public String toString() {
        return "HardwareInstrument: " +
                " code = '" + code + '\'' +
                ", maintenance = " + maintenance +
                ", measurementType = " + measurementType +
                ", price = " + getPrice();
    }


}