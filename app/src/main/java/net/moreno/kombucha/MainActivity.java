package net.moreno.kombucha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    @BindView(R.id.kombucha_list)
    RecyclerView kombuchaList;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        kombuchaList.setHasFixedSize(true);
        kombuchaList.setLayoutManager(new LinearLayoutManager(this));

        
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // Get Post object and use the values to update the UI
        Bottle bottle = dataSnapshot.getValue(Bottle.class);
        // ...
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        // Getting Post failed, log a message
        Log.w("DB Error", "loadPost:onCancelled", databaseError.toException());
        // ...
    }
}
