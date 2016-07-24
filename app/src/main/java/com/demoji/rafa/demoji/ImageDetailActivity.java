package com.demoji.rafa.demoji;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        generateImage(id, server, farm, secret);

        happy.setText(new String(Character.toChars(0x1F603)));
        sad.setText(new String(Character.toChars(0x1F622)));
        love.setText(new String(Character.toChars(0x1F60D)));
        angry.setText(new String(Character.toChars(0x1F620)));
        laughing.setText(new String(Character.toChars(0x1F602)));
        annoyed.setText(new String(Character.toChars(0x1F612)));



    }

    public void generateImage(String id, String server, String farm, String secret) {
        String picUrl = "https://farm" + farm +".staticflickr.com/"
                + server +"/" + id + "_" + secret +"_z.jpg";

        Log.d("Pic Url", picUrl);
        Picasso.with(this).load(picUrl).into(image);
    }

}
