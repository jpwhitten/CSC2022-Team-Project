package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;

/**
 * Created by Joshua on 23/02/2016.
 */
public interface Row {

    String getDescription();

    int getId();

    int getPictureID();

    String getTitle();

    Drawable getPicture();

    String getColor();
}
