package com.demoji.rafa.demoji;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ssolorzano on 7/23/16.
 */
public class EmojiActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.demo_spinner) Spinner demoSpinner;

    // Array of strings...

    String happy=(new StringBuilder()).append(new String(Character.toChars(0x1F603))).append("Happy").toString();
    String sad=(new StringBuilder()).append(new String(Character.toChars(0x1F622))).append("Sad").toString();
    String annoyed=(new StringBuilder()).append(new String(Character.toChars(0x1F60D))).append("Loving").toString();
    String laughing=(new StringBuilder()).append(new String(Character.toChars(0x1F620))).append("Angry").toString();
    String joy=(new StringBuilder()).append(new String(Character.toChars(0x1F602))).append("Laughing").toString();
    String angry=(new StringBuilder()).append(new String(Character.toChars(0x1F612))).append("Annoyed").toString();

    String male=(new StringBuilder()).append(new String(Character.toChars(0x1F603))).toString();
    String female=(new StringBuilder()).append(new String(Character.toChars(0x1F622))).toString();
    String nonBin=(new StringBuilder()).append(new String(Character.toChars(0x1F60D))).toString();
    String alienG=(new StringBuilder()).append(new String(Character.toChars(0x1F620))).toString();
    String trans=(new StringBuilder()).append(new String(Character.toChars(0x1F602))).toString();
    String both=(new StringBuilder()).append(new String(Character.toChars(0x1F612))).toString();

    String[] racesArray = {happy,sad,annoyed,laughing,joy,angry};
    String[] genderArray = {male,female,nonBin,alienG,trans,both};
    ListView listView;
    int route;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_screen);
        ButterKnife.bind(this);

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, racesArray);
        final ArrayAdapter reverseAdapter = new ArrayAdapter<String>(this, R.layout.rowlayout, genderArray);

        listView = (ListView) findViewById(R.id.demo_list);
        listView.setAdapter(adapter);

        final String[] demographics = new String[] { "Race", "Gender", "Sexual Orientation", "Religion", "Location", "Age", "Income" };

        ArrayAdapter<String> agesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, demographics);
        agesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        demoSpinner.setAdapter(agesAdapter);

        demoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String Text = parent.getSelectedItem().toString();
                if(Text.equals(null)) {
                    Toast.makeText(EmojiActivity.this, "HI", Toast.LENGTH_LONG).show();
                } else if(Text.equals("Race")) {
                    route = 0;
                } else if(Text.equals("Gender")) {
                    route = 1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(EmojiActivity.this, DemographicActivity.class);

                if(route == 0) {
                    myIntent.putExtra("race", racesArray);
                } else if (route == 1) {
                    myIntent.putExtra("gender", genderArray);
                }
                startActivity(myIntent);
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
