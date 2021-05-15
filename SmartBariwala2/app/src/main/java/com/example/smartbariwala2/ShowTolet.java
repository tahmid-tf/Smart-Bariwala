package com.example.smartbariwala2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class ShowTolet extends AppCompatActivity {

    Button btn;
    EditText searchbar;
    ListView list;
    DatabaseReference databaseReference;
    private List<ToLetConstractor> testList;
    private ToletAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tolet);

        list = (ListView) findViewById(R.id.list);
        searchbar = (EditText) findViewById(R.id.search);
        btn = (Button) findViewById(R.id.btn);
        testList = new ArrayList<>();
        customAdapter = new ToletAdapter(ShowTolet.this,testList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                testList.clear();

                String searchText = searchbar.getText().toString().trim().toLowerCase();

                databaseReference = FirebaseDatabase.getInstance().getReference("Tolet").child(searchText);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            ToLetConstractor toLetConstractor = dataSnapshot1.getValue(ToLetConstractor.class);
                            testList.add(toLetConstractor);
                        }
                        list.setAdapter(customAdapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
