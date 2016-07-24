package com.demoji.rafa.demoji;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Picture picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.pic_list);
        populate();
    }

    private void populate() {
        URL url = FlickrApi.composeFlickrUrl("miscellaneous", FlickerApiKey.key, 20);
        Log.d("Flickr Url", url.toString());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Call failed", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonString = response.body().string();
                if (response.isSuccessful()) {
                    Log.d("Successful Connection", "connection was successful");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(getImageIds(jsonString), MainActivity.this);
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Log.e("Error connecting", "there was an error connecting to flickr");
                }
            }
        });
    }

    private ArrayList<Picture> getImageIds(String jsonString) {
        ArrayList<Picture> idList = new ArrayList<>();
        Log.d("JSON Response", jsonString.substring(14, jsonString.length()-1));
        try {
            JSONObject jsonObject = new JSONObject(jsonString.substring(14, jsonString.length()-1));
            JSONObject photos = jsonObject.getJSONObject("photos");
            JSONArray photoIdArray = photos.getJSONArray("photo");

            for(int i = 0; i < photoIdArray.length(); i++) {
                String id = photoIdArray.getJSONObject(i).getString("id");
                String secret = photoIdArray.getJSONObject(i).getString("secret");
                String server = photoIdArray.getJSONObject(i).getString("server");
                String farm = photoIdArray.getJSONObject(i).getString("farm");

                Picture picture = new Picture();
                picture.setId(id);
                picture.setSecret(secret);
                picture.setServer(server);
                picture.setFarm(farm);
                idList.add(picture);
            }
        } catch(JSONException e) {
            Log.d("JSON can't be parsed", e.getMessage());
        }

        return idList;
    }
}
