package com.example.smartbariwala2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecurityPass extends AppCompatActivity implements View.OnClickListener {

    private EditText ownersNumberSecurity,rentersSetupPassword;
    private Button proceedToNextPage;
    private DatabaseReference databaseReference;
    private TextView monthTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pass);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ownersNumberSecurity = (EditText) findViewById(R.id.ownersNumberSecurity);
        rentersSetupPassword = (EditText) findViewById(R.id.rentersSetupPassword);
        proceedToNextPage = (Button) findViewById(R.id.proceedToNextPage);
        monthTest = (TextView) findViewById(R.id.monthTest);



        proceedToNextPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.proceedToNextPage){
            
            securityPass();
        }
    }

    private void securityPass() {

        final String ownersNumberSecurity1 = ownersNumberSecurity.getText().toString();
        final String rentersSetupPassword1 = rentersSetupPassword.getText().toString();


        if (ownersNumberSecurity1.isEmpty()){
            ownersNumberSecurity.setError("value cannot be empty");
            ownersNumberSecurity.requestFocus();
            return;
        }

        if (rentersSetupPassword1.isEmpty()){
            rentersSetupPassword.setError("Password field cannot be empty");
            rentersSetupPassword.requestFocus();
            return;
        }

        if (rentersSetupPassword1.length() != 4){
            rentersSetupPassword.setError("Max length for renters password is 4");
            rentersSetupPassword.requestFocus();
            return;
        }


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child(ownersNumberSecurity1).child("security").exists())
                {

                    String rentersPassword1 = dataSnapshot.child(ownersNumberSecurity1).child("security").child("rentersPassword").getValue().toString();

                    if (rentersPassword1.equals(rentersSetupPassword1)){

                        Bundle bundle = getIntent().getExtras();
                        String value = bundle.getString("month");
                        monthTest.setText(value);

                        Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(),OwnersShowBillPage.class);
                        intent.putExtra("month1",value);
                        intent.putExtra("ownerNumber",ownersNumberSecurity1);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();

                    }
                }

                else {
                    Toast.makeText(getApplicationContext(),"Wrong Number Inserted",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
