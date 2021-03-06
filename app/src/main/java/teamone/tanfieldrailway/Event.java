package teamone.tanfieldrailway;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Joshua on 28/02/2016.
 */
public class Event implements Row {
    private static int numberOfEvents = 0;

    private String title;
    private String date;
    private String imageURL;
    private Drawable image;
    private String description;
    private int id = numberOfEvents++;
    public PictureDownloadedCallback pictureDownloadedCallback;
    public Event(String title, String date, String description, String imageURL) {
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void setPictureDownloadedCallback(PictureDownloadedCallback callback) {
        pictureDownloadedCallback = callback;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getTitle() {
        return title;
    }

    public String getEventDescription(){
        return description;
    }
    @Override
    public String getColor() {
        return "#AB000000";
    }

    @Override
    public String getDescription() {
        return date;
    }

    @Override
    public int getId() {
        return id;
    }

    public void pictureDownloaded(){
        if(pictureDownloadedCallback != null){ //If null, picture has been downloaded before view is created
            pictureDownloadedCallback.onPictureDownloaded(getPicture());
        }
    }

    public void setPicture(Drawable drawable){
        this.image = drawable;
    }

    @Override
    public Drawable getPicture() {
        return image;
    }
    public int getPictureID() {
        return R.drawable.slideshow2; //For backup purposes.
    }

    public static void getEvents(final Context context, final Response.Listener<Event[]> callback) throws VolleyError{
        numberOfEvents = 0;

        //https://developer.android.com/training/volley/simple.html
        String URL = "http://www.tanfield-railway.co.uk/events.php";
        RequestQueue queue = Volley.newRequestQueue(context);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        //Parse the the response into events...
                        Event[] eventList = new Event[0];
                        String titleRegex = "<td id='tName'>(.*)</td>";
                        String imageRegex = "(images\\/events\\/.*\\.(?:png|jpg))";
                        String dateRegex = "<td id='tDate' colspan='2'>(.*)</td>";
                        String descriptionRegex = "<td>&nbsp;<\\/td>\\n\\s*?<td>(.*?)?<\\/td>";

                        Matcher titleMatcher = Pattern.compile(titleRegex).matcher(response);
                        Matcher dateMatcher = Pattern.compile(dateRegex).matcher(response);
                        Matcher imageMatcher = Pattern.compile(imageRegex).matcher(response);
                        Matcher descriptionMatcher = Pattern.compile(descriptionRegex, Pattern.DOTALL).matcher(response);
                        while(titleMatcher.find()){
                            dateMatcher.find();
                            imageMatcher.find();
                            descriptionMatcher.find();
                            final Event event = new Event(titleMatcher.group(1), dateMatcher.group(1), android.text.Html.fromHtml(descriptionMatcher.group(1)).toString().replaceAll("￼", ""), "http://www.tanfield-railway.co.uk/" + imageMatcher.group(1).replaceAll(" ", "%20"));
                            new DownloadFileClass()
                            {
                                @Override public void onPostExecute(Drawable result)
                                {
                                    event.setPicture(result);
                                    event.pictureDownloaded();
                                }
                            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, event.getImageURL());

                            eventList = java.util.Arrays.copyOf(eventList, eventList.length +1);
                            eventList[eventList.length -1] = event;

                        }
                        callback.onResponse(eventList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onResponse(null);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
