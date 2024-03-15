package Domain;

public class StrenghtMachine extends TrainingMachine{
    private int motion_level;
    private String muscle_group;

    public StrenghtMachine(int serialNumber, boolean maintenance, int motion_level, String muscle_group) {
        super(serialNumber, maintenance);
        this.motion_level = motion_level;
        this.muscle_group = muscle_group;
    }

    @Override
    public int getPrice() {
        int price = 0;
        if (getMuscle_group().equals("arms") || (getMuscle_group().equals("legs")))
            price = 600;
        else // core
            price = 800;
        if (isMaintenance())
            price = price * 2;
        return price;
    }

    public int getMotion_level() {
        return motion_level;
    }

    public String getMuscle_group() {
        return muscle_group;
    }

    @Override
    public String toString() {
        return "StrenghtMachine: " +
                "motion_level = " + motion_level +
                ", muscle_group = '" + muscle_group + '\'' +
                ", serialNumber = " + serialNumber +
                ", maintenance = " + maintenance +
                ", price = " + getPrice() ;
    }

}