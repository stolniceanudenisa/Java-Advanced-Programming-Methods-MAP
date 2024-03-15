package Domain;

public class Document {
    protected String author;

    public Document(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isConformant() {
        return false;
    }
    @Override
    public String toString() {
        return "Document: " + " author = " + author;
    }
}