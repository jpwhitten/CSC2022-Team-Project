package teamone.tanfieldrailway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SpecialEventsFragment extends Fragment implements FragmentTitle {
    private Event event;
    public SpecialEventsFragment() {
        // Required empty public constructor
    }

    public String getTitle(){
        return "Special Events";
    }

    public void setEvent(Event event){
       this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //image title date description
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ImageView imageView = (ImageView)  view.findViewById(R.id.image);
        TextView titleView = (TextView) view.findViewById(R.id.title);
        TextView dateView = (TextView) view.findViewById(R.id.date);
        TextView descriptionView = (TextView) view.findViewById(R.id.description);

        imageView.setImageDrawable(event.getPicture());
        titleView.setText(event.getTitle());
        dateView.setText(event.getDate());
        descriptionView.setText(event.getEventDescription());
        // Inflate the layout for this
        return view;
    }
}
