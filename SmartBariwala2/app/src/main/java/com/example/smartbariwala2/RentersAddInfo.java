package com.example.smartbariwala2;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RentersAddInfo extends AppCompatActivity implements View.OnClickListener {

    private EditText rentersAddDate,rentersAddUnit,rentersName,rentersMoblie,rentersFamily,rentersShortDescription;
    private Button submitInfo;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renters_add_info);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        final String owner = bundle.getString("ownerNumber");
        final String unit = bundle.getString("unit");

        rentersAddDate = (EditText) findViewById(R.id.rentersAddDate);
        rentersAddUnit = (EditText) findViewById(R.id.rentersAddUnit);
        rentersName = (EditText) findViewById(R.id.rentersName);
        rentersMoblie = (EditText) findViewById(R.id.rentersMoblie);
        rentersFamily = (EditText) findViewById(R.id.rentersFamily);
        rentersShortDescription = (EditText) findViewById(R.id.rentersShortDescription);
        submitInfo = (Button) findViewById(R.id.submitInfo);

        Date date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentdate = dateFormat.format(date);



        rentersAddDate.setText("Date: "+currentdate);

        rentersAddUnit.setText(unit);

        submitInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.submitInfo)
        {
            String rentersAddDate1 = rentersAddDate.getText().toString();
            String rentersAddUnit1 = rentersAddUnit.getText().toString();
            String rentersName1 = rentersName.getText().toString();
            String rentersMoblie1 = rentersMoblie.getText().toString();
            String rentersFamily1 = rentersFamily.getText().toString();
            String rentersShortDescription1 = rentersShortDescription.getText().toString();

            Bundle bundle = getIntent().getExtras();
            final String owner = bundle.getString("ownerNumber");
            final String unit = bundle.getString("unit");

            if (rentersName1.isEmpty())
            {
                rentersName.setError("Please insert your full name");
                rentersName.requestFocus();
                return;
            }

            if (rentersMoblie1.isEmpty())
            {
                rentersMoblie.setError("Please insert your full name");
                rentersMoblie.requestFocus();
                return;
            }

            if (rentersFamily1.isEmpty())
            {
                rentersFamily.setError("Please insert your full name");
                rentersFamily.requestFocus();
                return;
            }

            if (rentersShortDescription1.isEmpty())
            {
                rentersShortDescription.setError("Please insert your full name");
                rentersShortDescription.requestFocus();
                return;
            }

            RentersAddInfoConstractor constractor = new RentersAddInfoConstractor(rentersAddDate1,rentersAddUnit1,rentersName1,rentersMoblie1,rentersFamily1,rentersShortDescription1);
            databaseReference.child(owner).child("renters-info").child(unit).setValue(constractor);

            String key = databaseReference.push().getKey();
            databaseReference.child(owner).child("renters-info-list").child(key).setValue(constractor);

            finish();

            Toast.makeText(getApplicationContext(),"Info saved successfully",Toast.LENGTH_SHORT).show();

            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        }
    }
}
