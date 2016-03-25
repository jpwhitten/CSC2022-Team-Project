package teamone.tanfieldrailway;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TreasureFragment extends Fragment {

    private String title = "Treasure Hunt";
    Boolean overlayTextToggle = true;

    Animator anim = new Animator();

    TreasureHuntManager treasureHuntManager;

    ColorMatrix matrix = new ColorMatrix();
    ColorMatrixColorFilter filter;

    ArrayList<TreasureImageView> treasureImageViews = new ArrayList<>();

    TreasureImageView treasureImageViewOne;
    TreasureImageView treasureImageViewTwo;
    TreasureImageView treasureImageViewThree;
    TreasureImageView treasureImageViewFour;
    TreasureImageView treasureImageViewFive;
    TreasureImageView treasureImageViewSix;

    TextView overlayText;
    RelativeLayout overlay;

    ImageView overlayTreasureImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        treasureHuntManager = (TreasureHuntManager) getArguments().get("teamone.tanfieldrailway.TreasureHuntManager");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_treasure, container, false);
        matrix.setSaturation(0);
        filter = new ColorMatrixColorFilter(matrix);

        overlay = (RelativeLayout) view.findViewById(R.id.treasureHuntOverlay);
        overlay.setVisibility(View.GONE);

        overlayTreasureImage = (ImageView) view.findViewById(R.id.treasureHuntOverlayTreasureImage);
        overlayTreasureImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView overlayImage = (ImageView) view.findViewById(R.id.treasureHuntOverlayImage);

        overlayText = (TextView) view.findViewById(R.id.treasureHuntOverlayText);
        overlayText.setText(String.format("%s Found!", treasureHuntManager.foundTreasureName));
        animateOverlayText();

        RelativeLayout overlayClose = (RelativeLayout) view.findViewById(R.id.treasureHuntOverlayClose);

        ImageView treasureOne = (ImageView) view.findViewById(R.id.treasure1);
        ImageView treasureTwo = (ImageView) view.findViewById(R.id.treasure2);
        ImageView treasureThree = (ImageView) view.findViewById(R.id.treasure3);
        ImageView treasureFour = (ImageView) view.findViewById(R.id.treasure4);
        ImageView treasureFive = (ImageView) view.findViewById(R.id.treasure5);
        ImageView treasureSix = (ImageView) view.findViewById(R.id.treasure6);

        treasureImageViewOne = new TreasureImageView(treasureOne, this.treasureHuntManager.getTreasures().get(0));
        treasureImageViewTwo = new TreasureImageView(treasureTwo, this.treasureHuntManager.getTreasures().get(1));
        treasureImageViewThree = new TreasureImageView(treasureThree, this.treasureHuntManager.getTreasures().get(2));
        treasureImageViewFour = new TreasureImageView(treasureFour, this.treasureHuntManager.getTreasures().get(3));
        treasureImageViewFive = new TreasureImageView(treasureFive, this.treasureHuntManager.getTreasures().get(4));
        treasureImageViewSix = new TreasureImageView(treasureSix, this.treasureHuntManager.getTreasures().get(5));

        treasureImageViews.add(treasureImageViewOne);
        treasureImageViews.add(treasureImageViewTwo);
        treasureImageViews.add(treasureImageViewThree);
        treasureImageViews.add(treasureImageViewFour);
        treasureImageViews.add(treasureImageViewFive);
        treasureImageViews.add(treasureImageViewSix);

        if(treasureHuntManager.treasureFound) {
            treasureHuntManager.treasureFound = false;
            overlay.setVisibility(View.VISIBLE);
            for(int i = 0; i < treasureImageViews.size(); i++) {
                if(treasureImageViews.get(i).getTreasureItem().getName().equals(treasureHuntManager.foundTreasureName)){
                    overlayTreasureImage.setImageResource(treasureImageViews.get(i).getTreasureItem().getImage());
                    anim.translate(overlayTreasureImage, 0, 0, 400, 0, 9000);
                    break;
                }
            }
            overlayText.setText(String.format("You have found %d/%d treasures! Well Done!", treasureHuntManager.numTreasuresFound, treasureHuntManager.getTreasures().size()));
            animateTreasureFound();

        }

        updateTreasureImageViews(false);

        Button resetTreasures = (Button) view.findViewById(R.id.ResetTreasureButton);

        overlayClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overlay.setVisibility(View.GONE);

            }
        });

        resetTreasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < treasureHuntManager.getTreasures().size(); i++) {
                    treasureHuntManager.getTreasures().get(i).setFound(false);
                }
                treasureHuntManager.numTreasuresFound = 0;
                updateTreasureImageViews(true);
            }
        });
        return view;
    }

    private void updateTreasureImageViews(Boolean isReset) {

        if(!isReset) {
            for(int i = 0; i < treasureImageViews.size(); i++){
                TreasureImageView item = treasureImageViews.get(i);
                item.getImageView().setImageResource(item.getTreasureItem().getImage());
                item.getImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
                if(!item.getTreasureItem().isFound() && !item.getTreasureItem().getName().equals(treasureHuntManager.foundTreasureName)) {
                    item.getImageView().setColorFilter(filter);
                }
            }
        } else {
            for(int i = 0; i < treasureImageViews.size(); i++){
                TreasureImageView item = treasureImageViews.get(i);
                if(!item.getTreasureItem().isFound()) {
                    item.getImageView().setColorFilter(filter);
                }
            }
        }


    }

    private void animateTreasureFound() {

    }

    private void showTreasureCard() {

    }

    private void animateOverlayText() {
        new CountDownTimer(3000, 3000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                if(overlayTextToggle) {
                    overlayText.setTextSize(25f);
                    overlayText.setText(String.format("You have found %d/%d treasures! Well Done!", treasureHuntManager.numTreasuresFound, treasureHuntManager.getTreasures().size()));
                    overlayTextToggle = false;
                    animateOverlayText();
                } else {
                    overlayText.setTextSize(25f);
                    overlayText.setText(String.format("%s Found!", treasureHuntManager.foundTreasureName));
                    overlayTextToggle = true;
                    animateOverlayText();
                }
            }
        }.start();
    }


        //gives the title to display
    public String getTitle() {
        return title;
    }
}