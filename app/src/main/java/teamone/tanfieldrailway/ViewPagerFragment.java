package teamone.tanfieldrailway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    private Event[] events;
    private int position;

    public void setEvents(Event[] events, int position){
       this.events = events;
        this.position = position;
    }

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.viewpager, container, false);

        final DeviceAdapter deviceAdapter = new DeviceAdapter(getChildFragmentManager(), getContext());
        deviceAdapter.setEvent(events);

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(deviceAdapter);
        mViewPager.setCurrentItem(position);

        return view;
    }

}
