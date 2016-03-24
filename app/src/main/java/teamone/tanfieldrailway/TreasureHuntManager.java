package teamone.tanfieldrailway;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Joe on 26/02/2016.
 */
public class TreasureHuntManager implements Parcelable{

    public  Boolean treasureFound;
    private ArrayList<TreasureItem> treasures = new ArrayList<>();

    public int numTreasuresFound = 0;

    public String foundTreasureName;

    public TreasureHuntManager() {
        createTreasures();
        treasureFound = false;
    }

    public TreasureHuntManager(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<TreasureHuntManager> CREATOR = new Creator<TreasureHuntManager>() {
        @Override
        public TreasureHuntManager createFromParcel(Parcel in) {
            return new TreasureHuntManager(in);
        }

        @Override
        public TreasureHuntManager[] newArray(int size) {
            return new TreasureHuntManager[size];
        }
    };

    public void createTreasures() {
        TreasureItem item1 = new TreasureItem("One", "aaaaaaaaaaaaaa", R.drawable.slideshow1);
        TreasureItem item2 = new TreasureItem("Two", "bbbbbbbbbbbbbb", R.drawable.slideshow1);
        TreasureItem item3 = new TreasureItem("Three", "cccccccccccccc", R.drawable.slideshow1);
        TreasureItem item4 = new TreasureItem("Four", "dddddddddddddd", R.drawable.slideshow1);
        TreasureItem item5 = new TreasureItem("Five", "eeeeeeeeeeeeee", R.drawable.slideshow1);
        TreasureItem item6 = new TreasureItem("Six", "ffffffffffffff", R.drawable.slideshow1);
        treasures.add(item1);
        treasures.add(item2);
        treasures.add(item3);
        treasures.add(item4);
        treasures.add(item5);
        treasures.add(item6);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(treasures);
        dest.writeByte((byte) (treasureFound ? 1 : 0));
        dest.writeString(foundTreasureName);
        dest.writeInt(numTreasuresFound);
    }

    private void readFromParcel(Parcel in) {
        treasures = in.createTypedArrayList(TreasureItem.CREATOR);
        treasureFound = in.readByte() != 0;
        foundTreasureName = in.readString();
        numTreasuresFound = in.readInt();
    }

    public ArrayList<TreasureItem> getTreasures() {
        return treasures;
    }
}