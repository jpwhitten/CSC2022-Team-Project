package teamone.tanfieldrailway;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 24/03/2016.
 */
public class FacebookPost{
    public static List<FacebookPost> ITEMS;
    private static String URL = "https://graph.facebook.com/108957235311/posts?access_token=199740776762625|1ka0xPOb3f2XjmsEefgTVSgP86A";
    public PictureDownloadedCallback pictureDownloadedCallback;
    String message;
    Drawable picture;
    String link;

    public FacebookPost(String message, Drawable picture, String link) {
        this.message = message;
        this.picture = picture;
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public Drawable getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public static void getPosts(final Context context, final Response.Listener<List<FacebookPost>> callback) throws VolleyError {
        RequestQueue queue = Volley.newRequestQueue(context);
        final List<FacebookPost> posts = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Parse the JSON Schema into objects.
                        try{
                            JSONObject JSONResponse = new JSONObject(response);
                            JSONArray facebookPosts = JSONResponse.getJSONArray("data");
                            for(int i = 0; i < facebookPosts.length(); i++){
                                JSONObject facebookPost = facebookPosts.getJSONObject(i);
                                if(facebookPost.has("message")){
                                    String message = facebookPost.getString("message");
                                    String picture;
                                    if(facebookPost.has("object_id")){
                                        picture = "https://graph.facebook.com/" + facebookPost.getString("object_id") + "/picture?type=normal";
                                    }else{
                                        //External image
                                        picture = facebookPost.getString("picture");
                                    }
                                    String link = facebookPost.getString("link");
                                    final FacebookPost post = new FacebookPost(message, null, link);
                                    posts.add(post);
                                    new DownloadFileClass()
                                    {
                                        @Override public void onPostExecute(Drawable result)
                                        {
                                            post.setPicture(result);

                                            if(post.pictureDownloadedCallback != null){
                                                post.pictureDownloadedCallback.onPictureDownloaded(result);
                                            }

                                        }
                                    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, picture);
                                }
                            }
                        }catch(Exception e){
                            Log.i("FB", e.getMessage());
                        }
                        callback.onResponse(posts);
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
