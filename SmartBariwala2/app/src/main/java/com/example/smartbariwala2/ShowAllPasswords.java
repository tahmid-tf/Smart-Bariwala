package com.example.smartbariwala2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowAllPasswords extends AppCompatActivity {

    TextView passAll;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_passwords);

        passAll = (TextView) findViewById(R.id.passAll);

        Bundle bundle = getIntent().getExtras();
        final String value = bundle.getString("number");

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if ( !dataSnapshot.child(value).child("unit-password").exists())
                {
                    Toast.makeText(getApplicationContext(),"Passwords does not yet exists",Toast.LENGTH_SHORT).show();
                }
                else {

                    String l1_u1 = dataSnapshot.child(value).child("unit-password").child("l1_u1").getValue().toString();
                    String l1_u2 = dataSnapshot.child(value).child("unit-password").child("l1_u2").getValue().toString();
                    String l1_u3 = dataSnapshot.child(value).child("unit-password").child("l1_u3").getValue().toString();
                    String l1_u4 = dataSnapshot.child(value).child("unit-password").child("l1_u4").getValue().toString();

                    String l2_u1 = dataSnapshot.child(value).child("unit-password").child("l2_u1").getValue().toString();
                    String l2_u2 = dataSnapshot.child(value).child("unit-password").child("l2_u2").getValue().toString();
                    String l2_u3 = dataSnapshot.child(value).child("unit-password").child("l2_u3").getValue().toString();
                    String l2_u4 = dataSnapshot.child(value).child("unit-password").child("l2_u4").getValue().toString();

                    String l3_u1 = dataSnapshot.child(value).child("unit-password").child("l3_u1").getValue().toString();
                    String l3_u2 = dataSnapshot.child(value).child("unit-password").child("l3_u2").getValue().toString();
                    String l3_u3 = dataSnapshot.child(value).child("unit-password").child("l3_u3").getValue().toString();
                    String l3_u4 = dataSnapshot.child(value).child("unit-password").child("l3_u4").getValue().toString();

                    String l4_u1 = dataSnapshot.child(value).child("unit-password").child("l4_u1").getValue().toString();
                    String l4_u2 = dataSnapshot.child(value).child("unit-password").child("l4_u2").getValue().toString();
                    String l4_u3 = dataSnapshot.child(value).child("unit-password").child("l4_u3").getValue().toString();
                    String l4_u4 = dataSnapshot.child(value).child("unit-password").child("l4_u4").getValue().toString();

                    passAll.setText("L1_U1 Password: " + l1_u1+"\n"
                    +"L1_U2 Password: " + l1_u2+"\n"
                    +"L1_U3 Password: " + l1_u3+"\n"
                                    +"L1_U4 Password: " + l1_u4+"\n"
                                    +"L2_U1 Password: " + l2_u1+"\n"
                                    +"L2_U2 Password: " + l2_u2+"\n"
                                    +"L2_U3 Password: " + l2_u3+"\n"
                                    +"L2_U4 Password: " + l2_u4+"\n"
                                    +"L3_U1 Password: " + l3_u1+"\n"
                                    +"L3_U2 Password: " + l3_u2+"\n"
                                    +"L3_U3 Password: " + l3_u3+"\n"
                                    +"L3_U4 Password: " + l3_u4+"\n"
                                    +"L4_U1 Password: " + l4_u1+"\n"
                                    +"L4_U2 Password: " + l4_u2+"\n"
                                    +"L4_U3 Password: " + l4_u3+"\n"
                                    +"L4_U4 Password: " + l4_u4+"\n"

                    );

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
