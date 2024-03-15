package org.example.domain;

public class SoftwareInstrument extends FlightInstrument{

    private int versiune_software;
    public SoftwareInstrument(String cod, boolean maintenance) {
        super(cod, maintenance);
    }

    public SoftwareInstrument(String code, boolean maintenance, int versiuneSoftware)
    {
        super(code, maintenance);
        this.versiune_software = versiuneSoftware;
    }

    public double getPrice() {
        int price=0;
        if(this.versiune_software <10)
         price = 100;
        else {
            price = 200;
        }
        if(maintenance = true)
            price = price * 2;
        return price;
    }

    public int getVersiuneSoftware() {
        return this.versiune_software;
    }

    @Override
    public String toString() {
        return "SoftwareInstrument: " +
                "versiune_software = " + versiune_software +
                ", cod = '" + cod + '\'' +
                ", maintenance = " + maintenance +
                ", price = " + getPrice();
    }

    public boolean isMaintenance() {
        return maintenance;
    }
}