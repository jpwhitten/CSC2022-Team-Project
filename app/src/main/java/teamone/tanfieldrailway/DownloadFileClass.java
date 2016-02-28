package teamone.tanfieldrailway;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Joshua on 28/02/2016.
 */
public class DownloadFileClass extends AsyncTask<String, Void, Drawable> {
        protected Drawable doInBackground(String... url) {
        try{
            InputStream URLcontent = (InputStream) new URL(url[0]).getContent();
            Drawable image = Drawable.createFromStream(URLcontent, "your source link");
            return image;
        }catch (Exception ex){
            Log.e("Error", ex.toString());
            return null;
        }
    }
}
