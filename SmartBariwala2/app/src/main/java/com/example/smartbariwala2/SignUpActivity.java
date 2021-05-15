package com.example.smartbariwala2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText email, password,username1,renters;
    private Button signUp;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String user = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = (EditText) findViewById(R.id.emailIdSignUp);
        password = (EditText) findViewById(R.id.passwordIdSignUp);
        username1 = (EditText) findViewById(R.id.userNameSignUp);
        renters = (EditText) findViewById(R.id.userRenterSignUp);
        signUp = (Button) findViewById(R.id.signUpButton);

        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        SignIn();
    }

    private void SignIn() {

        final String email1 = email.getText().toString().trim();
        final String password1 = password.getText().toString().trim();
        final String username = username1.getText().toString().trim();
        final String rentersPassword = renters.getText().toString().trim();

        if (email1.isEmpty()){

            email.setError("Please insert Email");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){

            email.setError("Please insert Email with correct format");
            email.requestFocus();
            return;
        }

        if (password1.isEmpty()){

            password.setError("Please Insert Password");
            password.requestFocus();
            return;
        }

        if (password1.length() < 6){

            password.setError("Password length should be more than five");
            password.requestFocus();
            return;
        }

        if (username.isEmpty()){
            username1.setError("Username field cannot be empty");
            username1.requestFocus();
            return;
        }

        if (username.length() != 11){
            username1.setError("Please insert the correct length of phone number");
            username1.requestFocus();
            return;
        }

        if (rentersPassword.isEmpty()){

            renters.setError("Renters password field cannot set to empty");
            renters.requestFocus();
            return;
        }

        if (rentersPassword.length() > 4){

            renters.setError("Renters password cannnot bigger than length of 4");
            renters.requestFocus();
            return;
        }




        mAuth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    sign();

                }

                else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User Already Registered , cannot proceed again",Toast.LENGTH_SHORT).show();
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"Error" + task.getException(),Toast.LENGTH_SHORT).show();

                    }
                }
            }

            private void sign() {

                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();

                String email2 = email1.replace(".","");
                String email3 = email2.replace("@","");


                databaseReference = FirebaseDatabase.getInstance().getReference(username);
                OwnerConstractor ownerConstractor = new OwnerConstractor(email3,password1,username,rentersPassword);
                databaseReference.child("security").setValue(ownerConstractor);

                finish();

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });
    }
}
