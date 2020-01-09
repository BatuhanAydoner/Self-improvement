package kisiselgelisim.moonturns.com.kisiselgelisim.Data;

public class RVData {

    private String text;
    private String title;
    private String image;

    public RVData() {
    }

    public RVData(String text, String title, String image) {
        this.text = text;
        this.title = title;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
