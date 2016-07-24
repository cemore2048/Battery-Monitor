package com.demoji.rafa.demoji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
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
public class TestIntentActivity extends Activity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.demo_spinner) Spinner demoSpinner;

    // Array of strings...

    String emoji1=(new StringBuilder()).append(new String(Character.toChars(0x1F603))).append(" Male").toString();
    String emoji2=(new StringBuilder()).append(new String(Character.toChars(0x1F628))).append(" Female").toString();
    String emoji3=(new StringBuilder()).append(new String(Character.toChars(0x1F621))).append(" Non-binary").toString();
    String emoji4=(new StringBuilder()).append(new String(Character.toChars(0x1F62A))).append(" Alien").toString();
    String emoji5=(new StringBuilder()).append(new String(Character.toChars(0x1F61E))).append(" Trans").toString();
    String emoji6=(new StringBuilder()).append(new String(Character.toChars(0x1F620))).append(" Both").toString();

    String[] mobileArray = {emoji1,emoji2,emoji3,emoji4,emoji5,emoji6};
    String[] reverseArray = {emoji6,emoji5,emoji4,emoji3,emoji2,emoji1};
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent_screen);
        ButterKnife.bind(this);

        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.rowlayout, mobileArray);
        final ArrayAdapter reverseAdapter = new ArrayAdapter<String>(this, R.layout.rowlayout, reverseArray);

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
                    Toast.makeText(TestIntentActivity.this, "HI", Toast.LENGTH_LONG).show();
                } else if(Text.equals("Race")) {
                    listView.setAdapter(adapter);
                    Toast.makeText(TestIntentActivity.this, "RACE", Toast.LENGTH_LONG).show();
                } else if(Text.equals("Gender")) {
                    listView.setAdapter(reverseAdapter);
                    Toast.makeText(TestIntentActivity.this, "GENDER", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        //demoSpinner.setOnItemSelectedListener(this);

        //Intent intent = getIntent();
        //String age = intent.getStringExtra("age");
        //String message = intent.getStringExtra(DemographicActivity.EXTRA_MESSAGE);
        //Toast.makeText(TestIntentActivity.this, age, Toast.LENGTH_LONG).show();

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
