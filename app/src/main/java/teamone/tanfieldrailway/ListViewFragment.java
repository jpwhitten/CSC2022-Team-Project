package teamone.tanfieldrailway;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListViewFragment extends Fragment {
// TODO: Rename and change types of parameters
   private Row[] listViewItems;
    private ListViewCallBack callback;
    public ListViewFragment() {
        // Required empty public constructor
    }

    public void setListViewItems(Row[] listViewItems, ListViewCallBack callback){
        this.listViewItems = listViewItems;
        this.callback = callback;
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
            bg.setBackgroundColor(Color.parseColor(row.getColor()));

            ImageView backgroundImage = (ImageView) item.findViewById(R.id.list_view_image);

            Drawable picture = row.getPicture();
            if(!(picture == null)){
                backgroundImage.setImageDrawable(picture);
            }else {
                backgroundImage.setImageDrawable(getResources().getDrawable(row.getPictureID()));
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

}
