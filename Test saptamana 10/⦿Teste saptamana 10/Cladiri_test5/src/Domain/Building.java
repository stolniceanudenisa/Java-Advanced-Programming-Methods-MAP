package Domain;

public class Building {
    protected Integer constructionYear;
    protected int id;

    public Building(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public boolean mustBeRestored() {
        return false;
    }

    public boolean canBeDemolished() {
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Building: " +
                "constructionYear = " + constructionYear
                + ", id = " + id;
    }
}