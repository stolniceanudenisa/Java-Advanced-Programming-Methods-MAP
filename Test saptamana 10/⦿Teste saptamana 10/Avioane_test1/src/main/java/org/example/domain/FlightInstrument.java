package org.example.domain;

public class FlightInstrument {

    protected String cod;
    protected boolean maintenance;

    public FlightInstrument(String cod, boolean maintenance) {
        this.cod = cod;
        this.maintenance = maintenance;
    }

    public String getCode() {
        return this.cod;
    }

    public boolean getMaintenance() {
        return this.maintenance;
    }

    public double getPrice() {
        return 0;
    }
    @Override
    public String toString() {
        return "FlightInstrument: cod: "+ this.cod + ", mentenanta: " + this.maintenance ;
    }

}