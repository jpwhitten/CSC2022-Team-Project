package teamone.tanfieldrailway;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

           final ImageView backgroundImage = (ImageView) item.findViewById(R.id.list_view_image);
            row.setPictureDownloadedCallback(new PictureDownloadedCallback(){
                @Override
                public void onPictureDownloaded(Drawable drawable) {
                    Log.i("img", "onPictureDownloaded");
                    backgroundImage.setImageDrawable(drawable);
                }
            });

            new AsyncTask<Integer, Void, Drawable>() {
                @Override
                protected Drawable doInBackground(Integer... params) {
                    Log.i("img", "doInBackground");
                    Drawable picture = row.getPicture();
                    if(picture != null){
                        Log.i("img", "Not null");
                        return picture;
                    }else {
                        Log.i("img", "Null");
                        return getResources().getDrawable(row.getPictureID());
                    }
                }

                @Override
                protected void onPostExecute(Drawable drawable) {
                    Log.i("img", "onPostExecute");
                    backgroundImage.setImageDrawable(drawable);
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);

            TextView name = (TextView) item.findViewById(R.id.list_view_Name);
            name.setText(row.getTitle());

            TextView desc = (TextView) item.findViewById(R.id.list_view_Desc);
            desc.setText(row.getDescription());

            String rowColor = row.getColor();

            if(rowColor != null) {
                LinearLayout bg = (LinearLayout) item.findViewById(R.id.list_view_image_overlay);
                bg.setBackgroundColor(Color.parseColor(rowColor));
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

    /**
     * This method ensures that background images are correctly set when coming back to the listview.
     */
    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        if(view != null){
            for(Row row : listViewItems){
                row.pictureDownloaded();
            }
        }
    }
}
