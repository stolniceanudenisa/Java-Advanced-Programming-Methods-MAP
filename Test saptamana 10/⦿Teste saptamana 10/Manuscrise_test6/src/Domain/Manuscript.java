package Domain;

public class Manuscript extends Document{
    private int nr_words;
    private int nr_pages;

    public Manuscript(String author, int nr_words, int nr_pages) {
        super(author);
        this.nr_words = nr_words;
        this.nr_pages = nr_pages;
    }

    public int getNr_words() {
        return this.nr_words;
    }

    public void setNr_words(int nr_words) {
        this.nr_words = nr_words;
    }

    public int getNr_pages() {
        return this.nr_pages;
    }

    public void setNr_pages(int nr_pages) {
        this.nr_pages = nr_pages;
    }

    public boolean isConformant() {
       if(this.getNr_words() >= 2000 && this.getNr_pages() <5)
              return true;
         return false;
    }
    @Override
    public String toString() {
        return "Manuscript: " + " nr_words = " + nr_words + " nr_pages = " + nr_pages + " author = " + getAuthor();
    }


}