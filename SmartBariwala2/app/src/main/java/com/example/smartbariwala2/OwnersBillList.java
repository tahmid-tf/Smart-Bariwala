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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class OwnersBillList extends AppCompatActivity {

    ListView list;
    DatabaseReference databaseReference;
    private List<OwnersAddBillConstractor> testList;
    private BillListAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_bill_list);

        list = (ListView) findViewById(R.id.list11);
        testList = new ArrayList<>();
        customAdapter = new BillListAdapter(OwnersBillList.this,testList);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {

        Bundle bundle = getIntent().getExtras();
        final String owner = bundle.getString("number");

        testList.clear();

        databaseReference = FirebaseDatabase.getInstance().getReference(owner).child("bill-list");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    OwnersAddBillConstractor toLetConstractor = dataSnapshot1.getValue(OwnersAddBillConstractor.class);
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
