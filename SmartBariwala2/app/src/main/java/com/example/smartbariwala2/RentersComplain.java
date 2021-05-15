package com.example.smartbariwala2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RentersComplain extends AppCompatActivity implements View.OnClickListener {

    EditText ownersNumber,unitComplain,dateComplain,complainDescription;
    Button addComplain;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renters_complain);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        final String owner = bundle.getString("ownerNumber");
        final String unit = bundle.getString("unit");

        databaseReference = FirebaseDatabase.getInstance().getReference(owner);

        ownersNumber = (EditText) findViewById(R.id.ownersNumber);
        unitComplain = (EditText) findViewById(R.id.unitComplain);
        dateComplain = (EditText) findViewById(R.id.dateComplain);
        complainDescription = (EditText) findViewById(R.id.complainDescription);
        addComplain = (Button) findViewById(R.id.addRentersComplain);

        unitComplain.setText(unit);

        DatePicker datePicker = new DatePicker(this);
        int currentDate1 = datePicker.getDayOfMonth();
        int currentMonth1 = (datePicker.getMonth() + 1);
        int currentYear1 = datePicker.getYear();

        String currentDate = String.valueOf(currentDate1);
        String currentMonth = String.valueOf(currentMonth1);
        String currentYear = String.valueOf(currentYear1);

        String date = currentMonth+"/"+currentDate+"/"+currentYear;
        dateComplain.setText(date);

        ownersNumber.setText(owner);

        addComplain.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.addRentersComplain)
        {

            String unit = "Unit: "+unitComplain.getText().toString();
            String date = "Date: "+dateComplain.getText().toString();
            String complain ="Complain: "+ complainDescription.getText().toString();

            if (unit.length() < 7)
            {
                unitComplain.setError("Available for renters only");
                unitComplain.requestFocus();
                Toast.makeText(getApplicationContext(),"Available for renters only",Toast.LENGTH_SHORT).show();
                return;
            }

            if (complain.length() < 12)
            {
                complainDescription.setError("Please insert valid description");
                complainDescription.requestFocus();
                return;
            }

            String key = databaseReference.push().getKey();

            RenterComplainAddConstractor constractor = new RenterComplainAddConstractor(unit,date,complain);
            databaseReference.child("complain").child(key).setValue(constractor);

            Toast.makeText(getApplicationContext(),"Complain sent successfully",Toast.LENGTH_SHORT).show();

            finish();

            Intent intent = new Intent(getApplicationContext(),RentersMainGridPage.class);
            startActivity(intent);

        }
    }
}
