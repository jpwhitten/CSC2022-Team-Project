package teamone.tanfieldrailway;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class WalkingRoutesFragment extends Fragment {

    public WalkingRoutesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_walking_routes, container, false);

        RelativeLayout startWalkButton = (RelativeLayout) view.findViewById(R.id.start_walk_button);
        startWalkButton.setBackgroundColor(Color.parseColor("#FF000000"));

        ImageView image = (ImageView) view.findViewById(R.id.walking_route_image);

        LinearLayout imageOverlay = (LinearLayout) view.findViewById(R.id.walking_route_image_overlay);
        imageOverlay.setBackgroundColor(Color.parseColor("#CC000000"));

        // Inflate the layout for this fragment
        return view;

    }

}
