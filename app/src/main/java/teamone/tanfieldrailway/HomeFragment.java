package teamone.tanfieldrailway;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Random;


public class HomeFragment extends Fragment implements FragmentTitle {

    public String getTitle(){
        return "";
    }

    Animator anim = new Animator();

    RandomTransitionDrawable transition;
    ImageView slideshow;

    Random r = new Random();

    int p;
    int x;
    int y;

    int fromX;
    int fromY;

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

        slideshow = (ImageView) view.findViewById(R.id.slideshow);

        transition = new RandomTransitionDrawable( new Drawable[] {
                getResources().getDrawable(R.drawable.slideshow1),
                getResources().getDrawable(R.drawable.slideshow2),
                getResources().getDrawable(R.drawable.slideshow3),
                getResources().getDrawable(R.drawable.slideshow4),
                getResources().getDrawable(R.drawable.slideshow5)
        });
        slideshow.setImageDrawable(transition);
        slideshow.setScaleType(ImageView.ScaleType.CENTER_CROP);
        transition.startTransition(1500, 9000);
        x = (200 + r.nextInt(600)) * -1;
        y = (100 + r.nextInt(500)) * -1;
        anim.translate(slideshow, 0, x, 0, y, 1);
        fromX = x;
        fromY = y;
        move();
        // Inflate the layout for this fragment
        return view;

    }


    private void move() {

        p = r.nextInt(2);
        x = r.nextInt(1300) * -1;
        y = r.nextInt(700) * -1;

        increaser(x, -250, 200);
        increaser(y, -150, 150);


        if(p == 0) {
            anim.translate(slideshow, fromX, x, fromY, fromY, 9755);
            fromX = x;
        } else if (p == 1) {
            anim.translate(slideshow, fromX, fromX, fromY, y, 9755);
            fromY = y;
        }

        CountDownTimer c = new CountDownTimer(9755, 9755) {
            @Override
            public void onTick(long millisUntilFinished) {
                
            }

            @Override
            public void onFinish() {
                move();
            }
        }.start();
    }


    private void increaser(int x, int y, int z) {
        if(x > y) {
            x -= z;
        }
    }
}
