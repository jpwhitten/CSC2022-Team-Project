package teamone.tanfieldrailway;

import android.widget.ImageView;

/**
 * Created by Joe on 23/03/2016.
 */
public class TreasureImageView {

    private ImageView imageView;
    private TreasureItem treasureItem;

    public TreasureImageView(ImageView imageView, TreasureItem treasureItem) {
        this.imageView = imageView;
        this.treasureItem = treasureItem;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public TreasureItem getTreasureItem() {
        return treasureItem;
    }
}
