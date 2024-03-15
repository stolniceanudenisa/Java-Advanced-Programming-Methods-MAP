package Domain;

public class Presentation extends Document{
    private int nr_slides;
    private String text;

    public Presentation(String author, int nr_slides, String text) {
        super(author);
        this.nr_slides = nr_slides;
        this.text = text;
    }

    public int getNr_slides() {
        return this.nr_slides;
    }

    public void setNr_slides(int nr_slides) {
        this.nr_slides = nr_slides;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isConformant() {
         /*
            nr mediu de caractere per slide = nr caractere / nr slides
         */
       // System.out.println("text " + getText());
        //System.out.println("slideuri " + getNr_slides());

        if ((getText().length() / getNr_slides()) <= 200) {
           // System.out.println("E conform, - TEST -  nu trebuie printat");
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return "Presentation: " + " nr_slides = " + nr_slides + " text = " + text + " author = " + getAuthor();
    }

}