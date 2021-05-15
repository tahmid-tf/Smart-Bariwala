package com.example.smartbariwala2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RentersInfoList extends AppCompatActivity {

    ListView list;
    DatabaseReference databaseReference;
    private List<RentersAddInfoConstractor> testList;
    private RentersInfoAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renters_info_list);

        getSupportActionBar().setTitle("Exit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        list = (ListView) findViewById(R.id.list12);
        testList = new ArrayList<>();
        customAdapter = new RentersInfoAdapter(RentersInfoList.this,testList);
    }

    @Override
    protected void onStart() {

        Bundle bundle = getIntent().getExtras();
        final String value = bundle.getString("number");

        databaseReference = FirebaseDatabase.getInstance().getReference("01828665566").child("renters-info-list");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    RentersAddInfoConstractor toLetConstractor = dataSnapshot1.getValue(RentersAddInfoConstractor.class);
                    testList.add(toLetConstractor);
                }
                list.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        super.onStart();
    }
}
