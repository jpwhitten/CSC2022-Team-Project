package teamone.tanfieldrailway;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListViewFragment extends Fragment implements FragmentTitle {
// TODO: Rename and change types of parameters
   private Row[] listViewItems;
    private ListViewCallBack callback;
    private String title;
    public ListViewFragment() {
        // Required empty public constructor
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setListViewItems(Row[] listViewItems, ListViewCallBack callback){
        this.listViewItems = listViewItems;
        this.callback = callback;
    }

    public String getTitle(){
        return title;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        LinearLayout scrollLayout = (LinearLayout) view.findViewById(R.id.Scroll_View_Linear_Layout);

        for(final Row row : listViewItems){
         View listView = inflater.inflate(R.layout.fragment_list_view_item, container, false);
            scrollLayout.addView(listView);

            RelativeLayout item = (RelativeLayout) listView.findViewById(R.id.ListViewItem);
            TextView name = (TextView) item.findViewById(R.id.list_view_Name);
            name.setText(row.getTitle());

            TextView desc = (TextView) item.findViewById(R.id.list_view_Desc);
            desc.setText(row.getDescription());

            LinearLayout bg = (LinearLayout) item.findViewById(R.id.list_view_image_overlay);

            if(row.getColor() != null) {
                bg.setBackgroundColor(Color.parseColor(row.getColor()));
            }

            ImageView backgroundImage = (ImageView) item.findViewById(R.id.list_view_image);

            Drawable picture = row.getPicture();
            if(!(picture == null)){
                backgroundImage.setImageDrawable(picture);
            }else {
                backgroundImage.setImageResource(row.getPictureID());
            }
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    callback.itemClicked(row.getId());
                }
            });
        }
        return view;
    }


    //https://stackoverflow.com/questions/10200256/out-of-memory-error-imageview-issue
    private void setScaledImage(ImageView imageView, final int resId) {
        final ImageView iv = imageView;
        ViewTreeObserver viewTreeObserver = iv.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                iv.getViewTreeObserver().removeOnPreDrawListener(this);
                int imageViewHeight = iv.getMeasuredHeight();
                int imageViewWidth = iv.getMeasuredWidth();
                iv.setImageBitmap(
                        decodeSampledBitmapFromResource(getResources(),
                                resId, imageViewWidth, imageViewHeight));
                return true;
            }
        });
    }

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds = true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {

        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
