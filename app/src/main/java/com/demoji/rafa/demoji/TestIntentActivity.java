package com.demoji.rafa.demoji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by ssolorzano on 7/23/16.
 */
public class TestIntentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_screen);

        //Intent intent = getIntent();
        //String age = intent.getStringExtra("age");
        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //Toast.makeText(TestIntentActivity.this, age, Toast.LENGTH_LONG).show();

    }

}
