package teamone.tanfieldrailway;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

/**
 * Created by WINETOU on 29/02/2016.
 * Modified by Joshua
 */


public class DeviceAdapter extends FragmentPagerAdapter {
    private Event[] events;
    public DeviceAdapter(FragmentManager fm, Context context) {
        super(fm);
    }

    public void setEvent(Event[] events){
        this.events = events;
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("getItem", "Item " + position + " requested");
        SpecialEventsFragment specialEventsFragment = new SpecialEventsFragment();
        specialEventsFragment.setEvent(events[position]);
        return specialEventsFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return events[position].getTitle();
    }

    @Override
    public int getCount() {
        return events.length;
    }
}
