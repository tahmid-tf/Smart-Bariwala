package com.example.smartbariwala2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OwnersAddTolet extends AppCompatActivity {

    EditText date,ownersName,ownersHomeInfo,ownersLocation,ownersAddress;
    Button postAdvertisement;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_add_tolet);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        date = (EditText) findViewById(R.id.date);
        ownersName = (EditText) findViewById(R.id.ownersName);
        ownersHomeInfo = (EditText) findViewById(R.id.ownersHomeInfo);
        ownersLocation = (EditText) findViewById(R.id.ownersLocation);
        ownersAddress = (EditText) findViewById(R.id.ownersAddress);
        postAdvertisement = (Button) findViewById(R.id.postAdvertisement);
        mAuth = FirebaseAuth.getInstance();

        final String email= mAuth.getCurrentUser().getEmail();

        DatePicker datePicker = new DatePicker(this);
        int currentDate1 = datePicker.getDayOfMonth();
        int currentMonth1 = (datePicker.getMonth() + 1);
        int currentYear1 = datePicker.getYear();

        String currentDate = String.valueOf(currentDate1);
        String currentMonth = String.valueOf(currentMonth1);
        String currentYear = String.valueOf(currentYear1);

        String date1 = currentDate+"/"+currentMonth+"/"+currentYear;
        date.setText(date1);

        postAdvertisement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String date1 = "Updated On: "+date.getText().toString();
                String ownersName1 = "Posted By: "+ownersName.getText().toString()+"\nEmail: "+email;
                String ownersHomeInfo1 = "House Info: "+ownersHomeInfo.getText().toString();
                String ownersLocation1 = ownersLocation.getText().toString().toLowerCase();
                String ownersAddress1 = "Address: "+ownersAddress.getText().toString();

                if(ownersName1.length()<39)
                {
                    ownersName.setError("Please insert valid information");
                    ownersName.requestFocus();
                    return;
                }

                if(ownersLocation1.isEmpty())
                {
                    ownersLocation.setError("Please insert valid information");
                    ownersLocation.requestFocus();
                    return;
                }

                if(ownersAddress1.length()<14)
                {
                    ownersAddress.setError("Please insert valid information");
                    ownersAddress.requestFocus();
                    return;
                }

                if(ownersHomeInfo1.length()<14)
                {
                    ownersHomeInfo.setError("Please insert valid information");
                    ownersHomeInfo.requestFocus();
                    return;
                }


                databaseReference = FirebaseDatabase.getInstance().getReference("Tolet").child(ownersLocation1);
                String key = databaseReference.push().getKey();
                ToLetConstractor constractor = new ToLetConstractor(date1,ownersName1,ownersHomeInfo1,ownersLocation1,ownersAddress1);
                databaseReference.child(key).setValue(constractor);

                finish();
                Toast.makeText(getApplicationContext(),"Advertisement Sent Succesfully",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(),OwnersMainGridPage.class);
                startActivity(intent);

            }
        });
    }
}
