package teamone.tanfieldrailway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DailyEventsFragment extends Fragment implements FragmentTitle {



    public DailyEventsFragment() {
        // Required empty public constructor
    }

    public String getTitle(){
        return "Daily Events";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_events, container, false);
    }

}
