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

public class SecurityPassRentersInfoHistory extends AppCompatActivity implements View.OnClickListener {

    private EditText ownersNumberSecurityAdd,rentersSetupPasswordAdd;
    private Button proceedToNextPageAdd;
    private DatabaseReference databaseReference;
    private TextView monthTestAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_pass_renters_info_history);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ownersNumberSecurityAdd = (EditText) findViewById(R.id.ownersNumberSecurityRenterF);
        rentersSetupPasswordAdd = (EditText) findViewById(R.id.rentersSetupPasswordRenterF);
        proceedToNextPageAdd = (Button) findViewById(R.id.proceedToNextPageRenterF);
        monthTestAdd = (TextView) findViewById(R.id.monthTestAdd);

        proceedToNextPageAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.proceedToNextPageRenterF){

            securityPass();
        }
    }

    private void securityPass() {

        final String ownersNumberSecurity1 = ownersNumberSecurityAdd.getText().toString();
        final String rentersSetupPassword1 = rentersSetupPasswordAdd.getText().toString();


        if (ownersNumberSecurity1.isEmpty()){
            ownersNumberSecurityAdd.setError("value cannot be empty");
            ownersNumberSecurityAdd.requestFocus();
            return;
        }

        if (ownersNumberSecurity1.length() > 11){
            ownersNumberSecurityAdd.setError("Number length can't be more than 11");
            ownersNumberSecurityAdd.requestFocus();
            return;
        }


        if (rentersSetupPassword1.isEmpty()){
            rentersSetupPasswordAdd.setError("Password field cannot be empty");
            rentersSetupPasswordAdd.requestFocus();
            return;
        }

        if (rentersSetupPassword1.length() != 4){
            rentersSetupPasswordAdd.setError("Max length for renters password is 4");
            rentersSetupPasswordAdd.requestFocus();
            return;
        }


        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(ownersNumberSecurity1).exists()){

                    String rentersPassword1 = dataSnapshot.child(ownersNumberSecurity1).child("security").child("rentersPassword").getValue().toString();
                    String ownersPhoneNumber1 = dataSnapshot.child(ownersNumberSecurity1).child("security").child("ownersPhoneNumber").getValue().toString();

                    if (rentersPassword1.equals(rentersSetupPassword1) && ownersNumberSecurity1.equals(ownersPhoneNumber1)){

                        Intent intent = new Intent(getApplicationContext(),RentersInfoList.class);
                        intent.putExtra("number",ownersNumberSecurity1);
                        startActivity(intent);
                        finish();

                        Toast.makeText(getApplicationContext(),"Logged in",Toast.LENGTH_SHORT).show();


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
