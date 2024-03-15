package Domain;

public class SoftwareInstrument extends FlightInstrument {
    private static int version;

    public SoftwareInstrument(String code, boolean maintenance, int version) {
        super(code, maintenance);
        this.version = version;
    }


    @Override
    public double getPrice() {
        if (getVersion() < 10)
            return 100;
        else
            return 200;
    }

    public static int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "SoftwareInstrument{" +
                "version=" + version +
                ", code='" + code + '\'' +
                ", maintenance=" + maintenance +
                '}';
    }
}
