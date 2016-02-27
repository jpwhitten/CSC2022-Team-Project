package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView slideshow = (ImageView) view.findViewById(R.id.slideshow);

        RandomTransitionDrawable transition = new RandomTransitionDrawable( new Drawable[] {
                getResources().getDrawable(R.drawable.slideshow1),
                getResources().getDrawable(R.drawable.slideshow2),
                getResources().getDrawable(R.drawable.slideshow3),
                getResources().getDrawable(R.drawable.slideshow4),
                getResources().getDrawable(R.drawable.slideshow5)
        });
        slideshow.setImageDrawable(transition);
        slideshow.setScaleType(ImageView.ScaleType.CENTER_CROP);
        transition.startTransition(1500, 3000);
        // Inflate the layout for this fragment
        return view;

    }






}
