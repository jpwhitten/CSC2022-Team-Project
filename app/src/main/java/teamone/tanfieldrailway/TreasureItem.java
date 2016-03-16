package teamone.tanfieldrailway;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Joe on 26/02/2016.
 */
public class TreasureItem implements Parcelable{

    private int image;
    private String name;
    private String description;
    private boolean found;

    public TreasureItem(String name, String description, int image) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.found = false;
    }

    protected TreasureItem(Parcel in) {
        image = in.readInt();
        name = in.readString();
        description = in.readString();
        found = in.readByte() != 0;
    }

    public static final Creator<TreasureItem> CREATOR = new Creator<TreasureItem>() {
        @Override
        public TreasureItem createFromParcel(Parcel in) {
            return new TreasureItem(in);
        }

        @Override
        public TreasureItem[] newArray(int size) {
            return new TreasureItem[size];
        }
    };

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeByte((byte) (found ? 1 : 0));
    }
}