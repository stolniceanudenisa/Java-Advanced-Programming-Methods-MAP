package Domain;

public class House extends Building{
    private boolean isHistorical;

    public House(Integer constructionYear, boolean isHistorical) {
        super(constructionYear);
        this.isHistorical = isHistorical;
    }

    public boolean isHistorical() {
        if (isHistorical) {
            return true;
        }
        return false;
    }

    public void setHistorical(boolean historical) {
        isHistorical = historical;
    }

    @Override
    public boolean mustBeRestored() {
        //o casa poate fi restaurata daca are peste 100 de ani si este istorica
        return super.mustBeRestored() || (isHistorical && constructionYear < 1920);
    }

    @Override
    public boolean canBeDemolished() {
//        return super.canBeDemolished() || isHistorical;
        //o casa poate fi demolata daca nu este istorica
        return !isHistorical;
    }

    @Override
    public String toString() {
        return "House: " +
                "constructionYear = " + constructionYear +
                ", isHistorical = " + isHistorical
                + ", id = " + id;
    }
}