package teamone.tanfieldrailway;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarriageFragment extends Fragment {
    public static Carriage carriage = null;

    public CarriageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_carriage, container, false);

        if(carriage == null) return view;

        ImageView carriageImage = (ImageView) view.findViewById(R.id.CarriageImageView);
        carriageImage.setImageResource(carriage.getBiggerImage());

        TextView carriageCompany = (TextView) view.findViewById(R.id.CarriageCompany);
        carriageCompany.setText("Company: " + carriage.getCompany());

        TextView carriageBuilder = (TextView) view.findViewById(R.id.CarriageBuilder);
        carriageBuilder.setText("Builder: " + carriage.getBuilder());

        TextView carriagePeriod = (TextView) view.findViewById(R.id.CarriagePeriod);
        carriagePeriod.setText("Period: " + carriage.getPeriod());

        TextView carriageWheels = (TextView) view.findViewById(R.id.CarriageWheels);
        carriageWheels.setText("Wheels: " + carriage.getWheels());

        TextView carriageName = (TextView) view.findViewById(R.id.CarriageName);
        carriageName.setText(carriage.getTitle());

        int stringID = carriage.getIntDesc();
        TextView carriageDescription = (TextView) view.findViewById(R.id.CarriageDescription);
        if(stringID > 0){
            carriageDescription.setText(getString(stringID));
        }else{
            carriageDescription.setText("This carriage currently does not have a description. Check back soon!");
        }




        return view;
    }

}
