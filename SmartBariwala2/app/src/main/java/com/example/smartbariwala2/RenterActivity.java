package com.example.smartbariwala2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RenterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText renterIdSignIn,renterPasswordSignIn,renterSpinners;
    private Button renterButtonSignIn,spinnerSelected;
    private Spinner spinner;
    private DatabaseReference databaseReference;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        renterIdSignIn = (EditText) findViewById(R.id.renterIdSignIn);
        renterPasswordSignIn = (EditText) findViewById(R.id.renterPasswordSignIn);
        renterSpinners = (EditText) findViewById(R.id.rentersSpinner);
        renterButtonSignIn = (Button) findViewById(R.id.renterButtonSignIn);
        spinner = (Spinner) findViewById(R.id.renterSpinners);
        spinnerSelected = (Button) findViewById(R.id.spinnerSelected);
        searchView = (SearchView) findViewById(R.id.searchRent);


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

        renterButtonSignIn.setOnClickListener(this);
        spinnerSelected.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.spinnerSelected){

            final String spinner1 = spinner.getSelectedItem().toString().toLowerCase();
            renterSpinners.setText(spinner1);


        }

        if (v.getId() == R.id.renterButtonSignIn){
            final String ownerPhoneNumber = renterIdSignIn.getText().toString().trim();
            final String retersPassword = renterPasswordSignIn.getText().toString().trim();
            final String renterSpinners1 = renterSpinners.getText().toString().trim();

            if (ownerPhoneNumber.isEmpty()){

                renterIdSignIn.setError("Please insert the username of your's home ownner's correctly");
                renterIdSignIn.requestFocus();
                return;
            }

            if (retersPassword.isEmpty()){
                renterPasswordSignIn.setError("Password filed cannot be empty");
                renterPasswordSignIn.requestFocus();
                return;
            }

            if (retersPassword.length() != 4){
                renterPasswordSignIn.setError("Password length provided by ownrer is not more than 4");
                renterPasswordSignIn.requestFocus();
                return;
            }

            if (renterSpinners1.isEmpty()){
                renterSpinners.setError("Field Cannot be left empty");
                renterSpinners.requestFocus();
                return;
            }


            databaseReference = FirebaseDatabase.getInstance().getReference();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.child(ownerPhoneNumber).exists())
                    {
                        String rentersPassword1 = dataSnapshot.child(ownerPhoneNumber).child("unit-password").child(renterSpinners1).getValue().toString();

                        if (rentersPassword1.equals(retersPassword)){

                            String numberPass = ownerPhoneNumber;
                            String month1 = renterSpinners.getText().toString();

                            Intent intent = new Intent(getApplicationContext(),RentersMainGridPage.class);
                            intent.putExtra("ownerNumber",numberPass);
                            intent.putExtra("month1",month1);
                            intent.putExtra("month2",month1);
                            startActivity(intent);


                            Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();

                        }
                        else {

                            Toast.makeText(getApplicationContext(),"Id does not exists",Toast.LENGTH_SHORT).show();

                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        }

    }
