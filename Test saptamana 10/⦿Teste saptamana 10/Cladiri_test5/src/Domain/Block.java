package Domain;

public class Block extends Building{
    private Integer totalApartments;
    private Integer occupiedApartments;

    public Block(Integer constructionYear, Integer totalApartments, Integer occupiedApartments) {
        super(constructionYear);
        this.totalApartments = totalApartments;
        this.occupiedApartments = occupiedApartments;
    }

    public Integer getTotalApartments() {
        return totalApartments;
    }

    public void setTotalApartments(Integer totalApartments) {
        this.totalApartments = totalApartments;
    }

    public Integer getOccupiedApartments() {
        return occupiedApartments;
    }

    public void setOccupiedApartments(Integer occupiedApartments) {
        this.occupiedApartments = occupiedApartments;
    }

    @Override
    public boolean mustBeRestored() {
        return super.mustBeRestored() || (occupiedApartments < totalApartments * 0.5);
    }

    @Override
    public boolean canBeDemolished() {
        return super.canBeDemolished() || (occupiedApartments == 0);
    }

    @Override
    public String toString() {
        return "Block: " +
                "constructionYear = " + constructionYear +
                ", totalApartments = " + totalApartments +
                ", occupiedApartments = " + occupiedApartments
                + ", id = " + id;
    }
}