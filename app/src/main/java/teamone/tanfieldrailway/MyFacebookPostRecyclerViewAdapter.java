package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MyFacebookPostRecyclerViewAdapter extends RecyclerView.Adapter<MyFacebookPostRecyclerViewAdapter.ViewHolder> {

    private final List<FacebookPost> mValues;
    private final FacebookPostFragment.OnListFragmentInteractionListener mListener;

    public MyFacebookPostRecyclerViewAdapter(List<FacebookPost> items, FacebookPostFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_facebookpost, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.facebookMessage.setText(mValues.get(position).getMessage());
        Drawable picture = mValues.get(position).getPicture();
        holder.facebookImage.setImageDrawable(picture);
        if(picture != null){
            holder.facebookImage.setVisibility(View.VISIBLE);
        }

        holder.mItem.pictureDownloadedCallback = new PictureDownloadedCallback() {
            @Override
            public void onPictureDownloaded(Drawable drawable) {
                holder.facebookImage.setImageDrawable(drawable);
                holder.facebookImage.setVisibility(View.VISIBLE);
            }
        };


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView facebookMessage;
        public final ImageView facebookImage;
        public FacebookPost mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            facebookMessage = (TextView) view.findViewById(R.id.FacebookMessage);
            facebookImage = (ImageView) view.findViewById(R.id.FacebookImage);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
