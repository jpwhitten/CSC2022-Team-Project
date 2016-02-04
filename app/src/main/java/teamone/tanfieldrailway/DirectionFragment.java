package teamone.tanfieldrailway;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class DirectionFragment extends Fragment {
    private static final int PHONE_PERMISSION_REQUEST = 5;

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


        RelativeLayout mapLayout = (RelativeLayout) view.findViewById(R.id.map_btn);
        mapLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap();
            }
        });

        RelativeLayout emailLayout = (RelativeLayout) view.findViewById(R.id.email_btn);
        emailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        RelativeLayout phoneLayout = (RelativeLayout) view.findViewById(R.id.phone_btn);
        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupCallPermissions();
            }
        });
        // Inflate the layout for this fragment
        return view;

    }

    private void showMap(){
        //https://developers.google.com/maps/documentation/android-api/intents#launch_turn-by-turn_navigation
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=Tanfield+Railway,+The+Engine+Shed,+Newcastle+upon+Tyne+NE16+5ET,+United+Kingdom");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        if(mapIntent.resolveActivity(getContext().getPackageManager()) != null){
            startActivity(mapIntent);
        }else{
            Toast.makeText(getActivity(), "A suitable application could not be found", Toast.LENGTH_SHORT).show();
        }

    }
    private void sendEmail(){
        //https://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
        Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
        intent.setType("message/rfc822");
        intent.setData(Uri.parse("mailto:info@tanfield-railway.co.uk")); // or just "mailto:" for blank
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
        startActivity(intent);
    }

    private void setupCallPermissions(){
        String permissionString = "android.permission.CALL_PHONE";
        if (ContextCompat.checkSelfPermission(getActivity(), permissionString) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permissionString}, PHONE_PERMISSION_REQUEST);
        }else{
            makeCall();
        }
    }

    private void makeCall(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:07508092365"));
        startActivity(intent);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PHONE_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeCall();
                } else {
                    //User has actively refused the permission. TODO: Show them a message?
                }
            }
        }
    }
}
