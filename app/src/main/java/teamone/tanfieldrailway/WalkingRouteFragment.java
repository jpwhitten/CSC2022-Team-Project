package teamone.tanfieldrailway;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * @author Joshua
 *
 */

//JW: changed set text to fit layout, added overlay, button layout and image

public class WalkingRouteFragment extends Fragment implements FragmentTitle {
    private static WalkingRoutes walkingRoute; //TODO: Changed to static to prevent crash on rotation
    public WalkingRouteFragment() {
        // Required empty public constructor
    }

    public void setWalkingRoute(WalkingRoutes walkingRoute){
        WalkingRouteFragment.walkingRoute = walkingRoute;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String getTitle(){
        return "Walking Routes";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walking_routes, container, false);
        if(walkingRoute == null){
            throw new NullPointerException("You need to call setWalkingRoute() first!");
        }

        //String routeName, String description, double distance, double duration, String startingLocation, String endLocation, String mapData, String terrain
        TextView routeName = (TextView) view.findViewById(R.id.list_view_Name);
        routeName.setText(walkingRoute.getRouteName());

        TextView routeDesc = (TextView) view.findViewById(R.id.list_view_Desc);
        routeDesc.setText(walkingRoute.getDescription());

        TextView routeDistance = (TextView) view.findViewById(R.id.WalkingRoute_Distance);
        routeDistance.setText(String.valueOf("Distance: " + walkingRoute.getDistance() + " miles"));

        TextView routeDuration = (TextView) view.findViewById(R.id.WalkingRoute_Duration);
        routeDuration.setText(String.valueOf("Duration: " + walkingRoute.getDuration() + " minutes"));

        TextView routeStartingLocation = (TextView) view.findViewById(R.id.WalkingRoute_StartingLocation);
        routeStartingLocation.setText("Start: " + walkingRoute.getStartingLocation());

        TextView routeEndLocation = (TextView) view.findViewById(R.id.WalkingRoute_EndLocation);
        routeEndLocation.setText("End: " + walkingRoute.getEndLocation());

        TextView routeTerrain = (TextView) view.findViewById(R.id.WalkingRoute_Terrain);
        routeTerrain.setText("Terrain: " + walkingRoute.getTerrain());


        ImageView image = (ImageView) view.findViewById(R.id.list_view_image);
        image.setBackground(getResources().getDrawable(walkingRoute.getImage()));

        LinearLayout imageOverlay = (LinearLayout) view.findViewById(R.id.list_view_image_overlay);
        imageOverlay.setBackgroundColor(Color.parseColor(walkingRoute.getColor()));

        RelativeLayout takeWalkButton = (RelativeLayout) view.findViewById(R.id.walking_route_button);
        takeWalkButton.setBackgroundColor(Color.parseColor("#" + walkingRoute.getColor().substring(3)));

        takeWalkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(walkingRoute.getMapData());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(getActivity(), "A suitable application could not be found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Inflate the layout for this fragment
        return view;

    }
}