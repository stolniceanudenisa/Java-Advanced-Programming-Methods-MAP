package org.example.domain;

public class HardwareInstrument extends FlightInstrument{

    private String tip_de_masuratoare;


    public HardwareInstrument(String code, boolean maintenance, String tip_de_masuratoare) {
        super(code, maintenance);
        this.tip_de_masuratoare = tip_de_masuratoare;
    }

    public String getTipDeMasuratoare() {
        return this.tip_de_masuratoare;
    }

    public void setTipDeMasuratoare(String tip_de_masuratoare) {
        this.tip_de_masuratoare = tip_de_masuratoare;
    }

    public double getPrice() {
        int price=0;
        if(this.tip_de_masuratoare.equals("altitudine") || this.tip_de_masuratoare.equals("directie"))
            price = 50;
        else {
            price = 500;
        }
        if(maintenance = true)
            price = price * 2;
        return price;
    }

    @Override
    public String toString() {
        return "HardwareInstrument: cod: "+ this.cod + ", ment: " + this.maintenance + ", tip_de_masuratoare: " + this.tip_de_masuratoare
                + ", price: " + this.getPrice();
    }

}