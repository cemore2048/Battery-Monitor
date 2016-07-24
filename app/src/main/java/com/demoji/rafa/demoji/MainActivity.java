package com.demoji.rafa.demoji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    interface OnVHClickedListener {
        void onVHClicked(ImageViewHolder vh);
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final OnVHClickedListener mListener;
        @BindView(R.id.pic)
        ImageView pic;

        public ImageViewHolder(View itemView, OnVHClickedListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            pic = (ImageView) itemView.findViewById(R.id.pic);
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            mListener.onVHClicked(this);
        }
    }
    private void populate() {
        URL url = FlickrApi.composeFlickrUrl("miscellaneous", FlickerApiKey.key, 20);
        Log.d("Flickr Url", url.toString());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        StaggeredGridLayoutManager lm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
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
                            RecyclerView.Adapter<ImageViewHolder> adapter = new RecyclerView.Adapter<ImageViewHolder>() {

                                ArrayList<Picture> picList = getImageIds(jsonString);
                                @Override
                                public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                                    View itemView = LayoutInflater.from(parent.getContext())
                                            .inflate(R.layout.pic_grid_item, parent, false);
                                    return new ImageViewHolder(itemView, new OnVHClickedListener() {
                                        @Override
                                        public void onVHClicked(ImageViewHolder vh) {
                                            int position = vh.getPosition();
                                            String id = picList.get(position).getId();
                                            String server = picList.get(position).getServer();
                                            String farm = picList.get(position).getFarm();
                                            String secret = picList.get(position).getSecret();

                                            Log.d("id being passed", id);
                                            Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);
                                            intent.putExtra("id", id);
                                            intent.putExtra("server", server);
                                            intent.putExtra("farm", farm);
                                            intent.putExtra("secret", secret);

                                            startActivity(intent);
                                        }
                                    });
                                }

                                @Override
                                public void onBindViewHolder(ImageViewHolder holder, int position) {
                                    String picUrl = "";
                                    String farm = picList.get(position).getFarm();
                                    String id = picList.get(position).getId();
                                    String secret = picList.get(position).getSecret();
                                    String server = picList.get(position).getServer();
                                    picUrl = "https://farm" + farm +".staticflickr.com/"
                                            + server +"/" + id + "_" + secret +"_z.jpg";

                                    Log.d("Pic Url", picUrl);
                                    Picasso.with(MainActivity.this).load(picUrl).into(holder.pic);
                                }

                                @Override
                                public int getItemCount() {
                                    return picList.size();
                                }
                            };
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } else {
                    Log.e("Error connecting", "there was an error connecting to flickr");
                }
            }

            public ArrayList<Picture> getImageIds(String jsonString) {
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
        });
    }
}
