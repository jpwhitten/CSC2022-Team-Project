package teamone.tanfieldrailway;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 *
 * @author Joshua
 *
 */
public class WalkingRouteFragment extends Fragment {
    private WalkingRoutes walkingRoute;
    public WalkingRouteFragment() {
        // Required empty public constructor
    }

    public void setWalkingRoute(WalkingRoutes walkingRoute){
        this.walkingRoute = walkingRoute;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walking_routes, container, false);
        if(walkingRoute == null){
            throw new NullPointerException("You need to call setWalkingRoute() first!");
        }

        //String routeName, String description, double distance, double duration, String startingLocation, String endLocation, String mapData, String terrain
        TextView routeName = (TextView) view.findViewById(R.id.WalkingRoute_Name);
        routeName.setText(walkingRoute.getRouteName());

        TextView routeDesc = (TextView) view.findViewById(R.id.WalkingRoute_Desc);
        routeDesc.setText(walkingRoute.getDescription());

        TextView routeDistance = (TextView) view.findViewById(R.id.WalkingRoute_Distance);
        routeDistance.setText(String.valueOf(walkingRoute.getDistance()));

        TextView routeDuration = (TextView) view.findViewById(R.id.WalkingRoute_Duration);
        routeDuration.setText(String.valueOf(walkingRoute.getDuration()));

        TextView routeStartingLocation = (TextView) view.findViewById(R.id.WalkingRoute_StartingLocation);
        routeStartingLocation.setText(walkingRoute.getStartingLocation());

        TextView routeEndLocation = (TextView) view.findViewById(R.id.WalkingRoute_EndLocation);
        routeEndLocation.setText(walkingRoute.getEndLocation());

        TextView routeTerrain = (TextView) view.findViewById(R.id.WalkingRoute_Terrain);
        routeTerrain.setText(walkingRoute.getTerrain());

        Button takeWalkButton = (Button) view.findViewById(R.id.WalkingRoute_TakeWalkButton);
        takeWalkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse(walkingRoute.getMapData());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                if(mapIntent.resolveActivity(getContext().getPackageManager()) != null){
                    startActivity(mapIntent);
                }else{
                    Toast.makeText(getActivity(), "A suitable application could not be found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Inflate the layout for this fragment
        return view;

    }
}