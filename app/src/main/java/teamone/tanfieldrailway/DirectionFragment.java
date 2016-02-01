package teamone.tanfieldrailway;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class DirectionFragment extends Fragment {


    public DirectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_direction, container, false);

        String byCarDescriptionText = "From A1(M) Northbound - Exit at junction 63 for Chester le Street and Beamish. " +
                "At the roundabout below the motorway take the second exit A693 west towards Stanley and Beamish." +
                " Continue for nearly 4 miles on the A693, across 4 roundabouts. Going uphill, turn first right following the Tanfield Railway brown sign. " +
                "At the bottom of the bank turn right onto the A6076, following the brown sign. The railway is about 2 miles along the A6076, on the left.\n" +
                "\n" +
                "From A1 Southbound - Exit at the Lobley Hill intersection and follow the A692 west towards Consett for about 2 miles, passing through Streetgate into Sunniside. " +
                "Straight ahead at the first traffic lights, past the pedestrian lights, at the next traffic lights turn left onto the A6076 (following the Tanfield Railway brown sign). " +
                "Our main entrance is about 1 mile along the A6076, on the right.\n" +
                "\n" +
                "Sat Nav Post Code - NE16 5ET";

        String byBusDescriptionText = "By Rail:\n" + "Our nearest main line railway stations are Newcastle Central and Durham. " +
                "The Tyne and Wear Metro system also serves Gateshead Interchange and Newcastle Central for easy bus connections on most days.\n" +
                "\n" +
                "By Bus:\n" +
                "Monday to Saturday: the X30 and X31 from Newcastle, Gateshead and Stanley stop at the Andrews House entrance to the site.\n" +
                "Sunday: The X30 provides an hourly service to Andrews House.\n" +
                "Details are available from Go North East 0191 4205050 or visit http://www.gonortheast.co.uk/";



        TextView byCarDescription =(TextView) view.findViewById(R.id.by_car_description);
        byCarDescription.setText(byCarDescriptionText);

        TextView byBusDescription =(TextView) view.findViewById(R.id.by_bus_description);
        byBusDescription.setText(byBusDescriptionText);

        // Inflate the layout for this fragment
        return view;

    }
}
