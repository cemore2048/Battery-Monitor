package com.demoji.rafa.demoji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

public class DemographicActivity extends AppCompatActivity implements OnItemSelectedListener {

    @BindView(R.id.input_username) EditText usernameField;
    @BindView(R.id.input_race) EditText raceField;
    @BindView (R.id.input_gender) EditText genderField;
    @BindView(R.id.input_orientation) EditText orientationField;
    @BindView(R.id.input_religion) EditText religionField;
    @BindView(R.id.input_location) EditText locationField;
    @BindView(R.id.age_spinner) Spinner ageSpinner;
    @BindView(R.id.income_spinner) Spinner incomeSpinner;
    @BindView(R.id.btn_submit) Button submitButton;

    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Gender = "genderKey"; // change to bool
    public static final String EXTRA_MESSAGE = "SLACK";
    public static String age;
    public static String income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        submitButton.setText(new String(Character.toChars(0x1F44D)));

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(sharedPreferences.contains("genderKey")) {
//            String name = sharedPreferences.getString("genderKey", null);
//            Toast.makeText(DemographicActivity.this, name, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(DemographicActivity.this, TestIntentActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(DemographicActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
        }

        String[] incomes = new String[] { "$0-$30,000", "$30,000-$50,000", "$50,000-$75,000", "$100,000+" };
        String[] ages = new String[] { "20-30", "30-40", "40-50", "50+" };

        ArrayAdapter<String> agesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, ages);
        agesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageSpinner.setAdapter(agesAdapter);
        ageSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> incomesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, incomes);
        incomesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        incomeSpinner.setAdapter(incomesAdapter);
        incomeSpinner.setOnItemSelectedListener(this);

        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                age = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        incomeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                income = parent.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(DemographicActivity.this, TestIntentActivity.class);
            @Override
            public void onClick(View v) {
                String gen = genderField.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(Gender, gen);
                editor.commit();

                intent.putExtra("username", usernameField.getText().toString());
                intent.putExtra("race", raceField.getText().toString());
                intent.putExtra("gender", genderField.getText().toString());
                intent.putExtra("orientation", orientationField.getText().toString());
                intent.putExtra("religion", religionField.getText().toString());
                intent.putExtra("location", locationField.getText().toString());
                intent.putExtra("age", age);
                intent.putExtra("income", raceField.getText().toString());
                startActivity(intent);

                try {
                    post();
                }catch(IOException ex) {
                    //Do something witht the exception
                }
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

    private void post() throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("username", "\""+ usernameField.getText().toString() +"\"")
                .add("race", "\""+ raceField.getText().toString() +"\"")
                .add("gender", "\""+ genderField.getText().toString() +"\"")
                .add("sexual_orientation", "\""+ orientationField.getText().toString() +"\"")
                .add("religion", "\""+ religionField.getText().toString() +"\"")
                .add("city", "\""+ locationField.getText().toString() +"\"")
                .add("age", "1")
                .add("income", "\""+ income +"\"")
                .build();
        Request request = new Request.Builder()
                .url("http://159.203.240.126:3001/createUser")
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
                    Log.d("Successful Connection", "connection was successful");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DemographicActivity.this, "hello", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Log.e("Error connecting", "there was an error connecting to the DB");
                }
            }
        });
    }
}
