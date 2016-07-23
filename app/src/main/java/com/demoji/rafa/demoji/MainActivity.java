package com.demoji.rafa.demoji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView (R.id.input_gender) EditText editGender;
    @BindView(R.id.btn_submit) Button submitButton;
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Gender = "genderKey";
    public static final String EXTRA_MESSAGE = "SLACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);

        String[] incomes = new String[] { "$0-$50,000", "$50,000-$100,000", "$100,000+" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, incomes);

        dynamicSpinner.setAdapter(adapter);
        ;
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.contains("genderKey")) {
//            String name = sharedPreferences.getString("genderKey", null);
//            Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, TestIntentActivity.class);
            //intent.putExtra("Hi Bob!", "What");
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gen = editGender.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Gender, gen);
                editor.commit();
                Toast.makeText(MainActivity.this, "Submitted", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, TestIntentActivity.class);
                startActivity(intent);
            }
        });

        dynamicSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }
}
