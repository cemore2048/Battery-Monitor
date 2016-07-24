package com.demoji.rafa.demoji;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by rafa on 7/24/16.
 */
public class ImageDetailActivity extends Activity {

    ImageView image;
    Button happy;
    Button sad;
    Button annoyed;
    Button laughing;
    Button love;
    Button angry;
    Button compare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity_detail);

        image = (ImageView) findViewById(R.id.pic_detail);
        happy = (Button) findViewById(R.id.happy);
        sad = (Button) findViewById(R.id.sad);
        annoyed = (Button) findViewById(R.id.annoyed);
        laughing = (Button) findViewById(R.id.laughing);
        love = (Button) findViewById(R.id.love);
        angry = (Button) findViewById(R.id.angry);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String server = intent.getStringExtra("server");
        String farm = intent.getStringExtra("farm");
        String secret = intent.getStringExtra("secret");

        Log.d("id that was passed", id);
        String imageUrl = generateImage(id, server, farm, secret);

        happy.setText(new String(Character.toChars(0x1F603)));
        sad.setText(new String(Character.toChars(0x1F622)));
        love.setText(new String(Character.toChars(0x1F60D)));
        angry.setText(new String(Character.toChars(0x1F620)));
        laughing.setText(new String(Character.toChars(0x1F602)));
        annoyed.setText(new String(Character.toChars(0x1F612)));


        happy.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                happy.setPressed(true);
                return true;
            }
        });

        sad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sad.setPressed(true);
                return true;
            }
        });

        love.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                love.setPressed(true);
                return true;
            }
        });

        angry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                angry.setPressed(true);
                return true;
            }
        });

        laughing.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                laughing.setPressed(true);
                return true;
            }
        });

        annoyed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                annoyed.setPressed(true);
                return true;
            }
        });

        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageDetailActivity.this, EmojiActivity.class);
                startActivity(intent);
            }
        });
        addImageUrl(imageUrl);

    }

    public String generateImage(String id, String server, String farm, String secret) {
        String picUrl = "https://farm" + farm +".staticflickr.com/"
                + server +"/" + id + "_" + secret +"_z.jpg";

        Log.d("Pic Url", picUrl);
        Picasso.with(this).load(picUrl).into(image);

        return picUrl;
    }

    public void addImageUrl(String imageUrl) {

        Log.d("Adding image url", "adding image url");
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("url", "\""+ imageUrl +"\"")
                .build();
        Request request = new Request.Builder()
                .url("http://159.203.240.126:3001/newImage")
                .post(formBody)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Call failed", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.d("Successful Connection", response.body().string());
                } else {
                    Log.e("Error connecting", "there was an error connecting to the DB");
                }
            }
        });
    }
}
