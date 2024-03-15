package Domain;

import java.util.Objects;

public class HardwareInstrument extends FlightInstrument{

    private static String measurementType;

    public HardwareInstrument(String code, boolean maintenance, String measurementType) {
        super(code, maintenance);
        this.measurementType = measurementType;
    }


    @Override
    public double getPrice() {
        if(Objects.equals(getMeasurementType(), "Altitudine") || Objects.equals(getMeasurementType(), "Directie"))
            return 50;
        else //if(Objects.equals(getMeasurementType(), "Starea motorului"))
            return 500;
    }

    public static String getMeasurementType() {
        return measurementType;
    }

    @Override
    public String toString() {
        return "HardwareInstrument{" +
                "measurementType='" + measurementType + '\'' +
                ", code='" + code + '\'' +
                ", maintenance=" + maintenance +
                '}';
    }
}
