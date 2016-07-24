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
public class DemographicActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String black=(new StringBuilder()).append(" Black").append(" - 58%").toString();
    String latino=(new StringBuilder()).append(" Latino").append(" - 12%").toString();
    String white=(new StringBuilder()).append(" White").append(" - 22%").toString();
    String asian=(new StringBuilder()).append(" Asian").append(" - 33%").toString();
    String alien=(new StringBuilder()).append(" Alien").append(" - 12%").toString();
    String none=(new StringBuilder()).append(" None").append(" - 99%").toString();

    String male=(new StringBuilder()).append(" Male").append(" - 98%").toString();
    String female=(new StringBuilder()).append(" Female").append(" - 43%").toString();
    String nonBin=(new StringBuilder()).append(" Bi").append(" - 54%").toString();
    String alienG=(new StringBuilder()).append(" Alien").append(" - 12%").toString();
    String trans=(new StringBuilder()).append(" Trans").append(" - 56%").toString();
    String both=(new StringBuilder()).append(" Metro").append(" - 87%").toString();

    String[] racesArray = {black,latino,white,asian,alien,none};
    String[] genderArray = {male,female,nonBin,alienG,trans,both};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        ButterKnife.bind(this);

        final ArrayAdapter racesAdapter = new ArrayAdapter<String>(this, R.layout.rowlayout, racesArray);
        final ArrayAdapter genderAdapter = new ArrayAdapter<String>(this, R.layout.rowlayout, genderArray);

        listView = (ListView) findViewById(R.id.feels_list);

        Intent intent = getIntent();

        if(intent.hasExtra("race")) {
            listView.setAdapter(racesAdapter);
        } else if (intent.hasExtra("gender")) {
            listView.setAdapter(genderAdapter);
        }

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
