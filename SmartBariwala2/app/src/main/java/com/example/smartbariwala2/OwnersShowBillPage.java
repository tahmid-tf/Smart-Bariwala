package com.example.smartbariwala2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnersShowBillPage extends AppCompatActivity {

    TextView dateShow,houseBillShow,electricBillShow,otherBillShow,totalBillShow,gasBillShow,monthView;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_show_bill_page);

        getSupportActionBar().setTitle("Back");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dateShow = (TextView) findViewById(R.id.dateShow);
        houseBillShow = (TextView) findViewById(R.id.houseBillShow);
        electricBillShow = (TextView) findViewById(R.id.electricBillShow);
        otherBillShow = (TextView) findViewById(R.id.otherBillShow);
        totalBillShow = (TextView) findViewById(R.id.totalBillShow);
        gasBillShow = (TextView) findViewById(R.id.gasBillShow);
        monthView = (TextView) findViewById(R.id.monthView);


        Bundle bundle = getIntent().getExtras();
        final String month = bundle.getString("month1").toLowerCase();
        final String owner = bundle.getString("ownerNumber");

        databaseReference = FirebaseDatabase.getInstance().getReference().child(owner);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child("bills").child(month).exists()){

                    String month1 = dataSnapshot.child("bills").child(month).child("month").getValue().toString().toUpperCase();
                    String date1 = dataSnapshot.child("bills").child(month).child("date").getValue().toString();
                    String electronicBill1 = dataSnapshot.child("bills").child(month).child("electronicBill").getValue().toString();
                    String gassBill1 = dataSnapshot.child("bills").child(month).child("gassBill").getValue().toString();
                    String otherBill1 = dataSnapshot.child("bills").child(month).child("otherBill").getValue().toString();
                    String houseBill1 = dataSnapshot.child("bills").child(month).child("houseBill").getValue().toString();


                    monthView.setText(""+month1);
                    dateShow.setText(""+date1);
                    electricBillShow.setText(""+electronicBill1);
                    gasBillShow.setText(""+gassBill1);
                    houseBillShow.setText(""+houseBill1);
                    otherBillShow.setText(""+otherBill1);

                    double electronicBill2 = Double.parseDouble(electronicBill1);
                    double gassBill2 = Double.parseDouble(gassBill1);
                    double otherBill2 = Double.parseDouble(otherBill1);
                    double houseBill2 = Double.parseDouble(houseBill1);

                    double sum = electronicBill2 + gassBill2 + otherBill2 + houseBill2;
                    String sum1 = String.valueOf(sum);
                    totalBillShow.setText(""+sum1);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
