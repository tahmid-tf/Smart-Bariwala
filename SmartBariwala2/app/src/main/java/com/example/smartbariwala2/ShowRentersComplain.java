package com.example.smartbariwala2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowRentersComplain extends AppCompatActivity {

    ListView list;
    DatabaseReference databaseReference;
    private List<RenterComplainAddConstractor> testList;
    private ComplainShowAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_renters_complain);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list = (ListView) findViewById(R.id.list);
        testList = new ArrayList<>();
        customAdapter = new ComplainShowAdapter(ShowRentersComplain.this,testList);



    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        final String number = bundle.getString("number");

        databaseReference = FirebaseDatabase.getInstance().getReference(number).child("complain");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    RenterComplainAddConstractor toLetConstractor = dataSnapshot1.getValue(RenterComplainAddConstractor.class);
                    testList.add(toLetConstractor);
                }
                list.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
