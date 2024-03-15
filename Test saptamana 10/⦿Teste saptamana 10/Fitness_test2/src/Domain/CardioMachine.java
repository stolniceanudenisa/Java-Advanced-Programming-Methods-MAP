package Domain;

public class CardioMachine extends TrainingMachine{
    private int resistance_level;

    public CardioMachine(int serialNumber, boolean maintenance, int resistance_level) {
        super(serialNumber, maintenance);
        this.resistance_level = resistance_level;
    }

    @Override
    public int getPrice() {
        int price = 0;
        if (getResistance_level() < 10)
            price = 500;
        else
            price =200;

        if (isMaintenance() == true)
            price = price * 2;
        return price;
    }

    public int getResistance_level() {
        return resistance_level;
    }

    @Override
    public String toString() {
        return "CardioMachine: " +
                "resistance_level = " + resistance_level +
                ", serialNumber = " + serialNumber +
                ", maintenance = " + maintenance +
                ", price = " + getPrice() ;
    }

}