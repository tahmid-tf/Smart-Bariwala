package com.example.smartbariwala2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RentersMainGridPage extends AppCompatActivity {

    String name [] = {"Show Bills","Add Complain","Panic Button","Renter's info submit"};
    int img [] = {R.drawable.receipt,R.drawable.bad,R.drawable.warning,R.drawable.contact};
    DatabaseReference databaseReference;
    AlertDialog.Builder builder;

    GridView gridView;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renters_main_grid_page);

        gridView = (GridView) findViewById(R.id.grid1);
        mAuth = FirebaseAuth.getInstance();

        Bundle bundle = getIntent().getExtras();
        final String month = bundle.getString("month1").toLowerCase();
        final String owner = bundle.getString("ownerNumber");

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final CustomAdapter adapter = new CustomAdapter(RentersMainGridPage.this,name,img);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String name1 = name[i];

                if (name1.equals("Show Bills"))
                {
                    Intent intent = new Intent(getApplicationContext(),OwnersShowBillPage.class);
                    intent.putExtra("ownerNumber",owner);
                    intent.putExtra("month1",month);
                    intent.putExtra("month2",month);
                    startActivity(intent);
                }

                if (name1.equals("Add Complain"))
                {
                    Intent intent = new Intent(getApplicationContext(),RentersComplain.class);
                    intent.putExtra("ownerNumber",owner);
                    intent.putExtra("unit",month);
                    startActivity(intent);
                }

                if (name1.equals("Panic Button"))
                {
                    Toast.makeText(getApplicationContext(),"Under Maintanance",Toast.LENGTH_SHORT).show();
                }

                if (name1.equals("Renter's info submit"))
                {

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (!dataSnapshot.child(owner).child("renters-info").child(month).exists()){

                                infoAdd();

                            }

                            else {
                                Toast.makeText(getApplicationContext(),"Info of this unit already exists",Toast.LENGTH_SHORT).show();

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
            }
        });
    }

    private void infoAdd() {

        Bundle bundle = getIntent().getExtras();
        final String month = bundle.getString("month1").toLowerCase();
        final String owner = bundle.getString("ownerNumber");


        builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.warningicon);
        builder.setTitle("Warning");
        builder.setMessage("Please read carefully before proceed with this action. After successfully adding your info as a renter the app will be closed for a successfull data recovery. Please open this app again after this operation. Are you sure to proceed?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(getApplicationContext(),RentersAddInfo.class);
                intent.putExtra("ownerNumber",owner);
                intent.putExtra("unit",month);
                startActivity(intent);
                finish();


            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
