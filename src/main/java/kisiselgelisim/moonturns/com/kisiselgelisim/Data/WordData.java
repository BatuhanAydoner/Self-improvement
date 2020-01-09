package kisiselgelisim.moonturns.com.kisiselgelisim.Data;

public class WordData {

    //this class is for WordFragment

    private String text;
    private String person;

    public WordData() {
    }

    public WordData(String text, String person) {
        this.text = text;
        this.person = person;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
