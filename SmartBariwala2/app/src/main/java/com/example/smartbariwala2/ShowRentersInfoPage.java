package com.example.smartbariwala2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowRentersInfoPage extends AppCompatActivity implements View.OnClickListener {

    private EditText renterSpinners;
    private Button spinnerSelected;
    private Spinner spinner;
    private DatabaseReference databaseReference;
    SearchView searchView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_renters_info_page);

        Bundle bundle = getIntent().getExtras();
        final String owner = bundle.getString("number");

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renterSpinners = (EditText) findViewById(R.id.rentersSpinnerShow);
        spinner = (Spinner) findViewById(R.id.renterSpinnersShow);
        spinnerSelected = (Button) findViewById(R.id.spinnerSelectedShow);
        searchView = (SearchView) findViewById(R.id.searchRentShow);
        textView = (TextView) findViewById(R.id.rentersFullInfo);

        String [] month = getResources().getStringArray(R.array.months);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_addbills,R.id.spinnerAdapter,month);
        spinner.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        spinnerSelected.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Bundle bundle = getIntent().getExtras();
        final String owner = bundle.getString("number");

        if (view.getId() == R.id.spinnerSelectedShow){

            final String spinner1 = spinner.getSelectedItem().toString().toLowerCase();
            renterSpinners.setText(spinner1);

            databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(owner).child("renters-info").child(spinner1).exists())
                    {
                        String date = dataSnapshot.child(owner).child("renters-info").child(spinner1).child("date").getValue().toString();
                        String unit = dataSnapshot.child(owner).child("renters-info").child(spinner1).child("unit").getValue().toString();
                        String name = dataSnapshot.child(owner).child("renters-info").child(spinner1).child("name").getValue().toString();
                        String moblie = dataSnapshot.child(owner).child("renters-info").child(spinner1).child("mobile").getValue().toString();
                        String family = dataSnapshot.child(owner).child("renters-info").child(spinner1).child("family").getValue().toString();
                        String description = dataSnapshot.child(owner).child("renters-info").child(spinner1).child("description").getValue().toString();

                        textView.setText(date+"\nUnit: "+unit+"\nName: "+name+"\nMobile: "+moblie+"\nFamily: "+family+"\nDescription: "+description);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }
}
