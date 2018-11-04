package teamone.tanfieldrailway;

/**
 * Created by Joe on 25/03/2016.
 */
public class TimelineEvent {

    String date;
    String title;
    String description;
    int image;

    public TimelineEvent(String date, String title, String description, int image) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
