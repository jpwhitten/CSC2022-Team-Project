package teamone.tanfieldrailway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SpecialEventsFragment extends Fragment {

    public SpecialEventsFragment() {
        // Required empty public constructor
    }

    public void setEvent(Event event){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {





        // Inflate the layout for this
        return inflater.inflate(R.layout.fragment_special_events, container, false);
    }

}
