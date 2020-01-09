package kisiselgelisim.moonturns.com.kisiselgelisim.Data;

public class VideoData {

    private String video_url;
    private String video_title;
    private String video_image;

    public VideoData() {
    }

    public VideoData(String video_url, String video_title, String video_image) {
        this.video_url = video_url;
        this.video_title = video_title;
        this.video_image = video_image;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_image() {
        return video_image;
    }

    public void setVideo_image(String video_image) {
        this.video_image = video_image;
    }
}
