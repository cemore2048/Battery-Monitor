package com.demoji.rafa.demoji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ssolorzano on 7/23/16.
 */
public class TestIntentActivity extends Activity {

    @BindView(R.id.demo_spinner) Spinner demoSpinner;

    // Array of strings...

    String emoji1=(new StringBuilder()).append(new String(Character.toChars(0x1F603))).append(" Male").toString();
    String emoji2=(new StringBuilder()).append(new String(Character.toChars(0x1F628))).append(" Female").toString();
    String emoji3=(new StringBuilder()).append(new String(Character.toChars(0x1F621))).append(" Non-binary").toString();
    String emoji4=(new StringBuilder()).append(new String(Character.toChars(0x1F62A))).append(" Alien").toString();
    String emoji5=(new StringBuilder()).append(new String(Character.toChars(0x1F61E))).append(" Trans").toString();
    String emoji6=(new StringBuilder()).append(new String(Character.toChars(0x1F620))).append(" Both").toString();

    String[] mobileArray = {emoji1,emoji2,emoji3,emoji4,emoji5,emoji6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_screen);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, mobileArray);

        ListView listView = (ListView) findViewById(R.id.demo_list);
        listView.setAdapter(adapter);

        String[] demographics = new String[] { "Race", "Gender", "Sexual Orientation", "Religion", "Location", "Age", "Income" };

        ArrayAdapter<String> agesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, demographics);
        agesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        demoSpinner.setAdapter(agesAdapter);
        //demoSpinner.setOnItemSelectedListener(this);

        //Intent intent = getIntent();
        //String age = intent.getStringExtra("age");
        //String message = intent.getStringExtra(DemographicActivity.EXTRA_MESSAGE);
        //Toast.makeText(TestIntentActivity.this, age, Toast.LENGTH_LONG).show();

    }

}
