package teamone.tanfieldrailway;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FacebookPostFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FacebookPostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_facebookpost_list, container, false);

        ImageView slideshow = (ImageView) view.findViewById(R.id.slideshow);

        RandomTransitionDrawable transition = new RandomTransitionDrawable(new Drawable[] {
                getResources().getDrawable(R.drawable.slideshow1),
                getResources().getDrawable(R.drawable.slideshow2),
                getResources().getDrawable(R.drawable.slideshow3),
                getResources().getDrawable(R.drawable.slideshow4),
                getResources().getDrawable(R.drawable.slideshow5)
        });
        slideshow.setImageDrawable(transition);
        slideshow.setScaleType(ImageView.ScaleType.CENTER_CROP);
        transition.startTransition(1500, 3000);

        RecyclerView recycleView = (RecyclerView) view.findViewById(R.id.FacebookPostList);
        // Set the adapter
        if (recycleView != null) {
            Context context = view.getContext();
            if (mColumnCount <= 1) {
                recycleView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recycleView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recycleView.setAdapter(new MyFacebookPostRecyclerViewAdapter(FacebookPost.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(FacebookPost item);
    }
}
