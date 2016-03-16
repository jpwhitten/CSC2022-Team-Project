package teamone.tanfieldrailway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class TreasureFragment extends Fragment {

    private String title = "Treasure Hunt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_treasure2, container, false);
        Button resetTreasures = (Button) view.findViewById(R.id.ResetTreasureButton);

        resetTreasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "hi", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    //gives the title to display
    public String getTitle() {
        return title;
    }
}