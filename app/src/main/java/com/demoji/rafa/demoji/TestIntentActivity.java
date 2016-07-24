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
    String emoji = new String(Character.toChars(0x1F628));
    String[] mobileArray = {emoji,emoji,emoji,emoji,emoji,emoji,emoji,emoji};

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
