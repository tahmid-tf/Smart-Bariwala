package com.example.smartbariwala2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private EditText email,password;
    private Button signIn,renters,toletAdds;
    private TextView signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Exit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        image = (ImageView) findViewById(R.id.imageId);
        email = (EditText) findViewById(R.id.emailId);
        password = (EditText) findViewById(R.id.passwordId);
        signIn = (Button) findViewById(R.id.signin);
        toletAdds = (Button) findViewById(R.id.toletAdds);
        renters = (Button) findViewById(R.id.renters);
        signUp = (TextView) findViewById(R.id.signUpText);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        renters.setOnClickListener(this);
        toletAdds.setOnClickListener(this);

        if (mAuth.getCurrentUser() != null){

            finish();
            Intent intent = new Intent(getApplicationContext(),OwnersMainGridPage.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.signin) {
            signInCode();
        }

        if (v.getId() == R.id.signUpText) {
            signUpCode();
        }

        if (v.getId() == R.id.renters) {
            renterCode();
        }

        if (v.getId() == R.id.toletAdds) {

            Intent intent = new Intent(getApplicationContext(),ShowTolet.class);
            startActivity(intent);
        }

    }


    private void renterCode() {
        Intent intent = new Intent(getApplicationContext(),RenterActivity.class);
        startActivity(intent);
    }


    private void signUpCode() {
        Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
        startActivity(intent);
    }




    private void signInCode() {

            String email1 = email.getText().toString().trim();
            String password1 = password.getText().toString().trim();

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

        if (password1.length() < 5){

            password.setError("Password length should be more than five");
            password.requestFocus();
            return;
        }

            mAuth.signInWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();

                        finish();
                        Intent intent = new Intent(getApplicationContext(),OwnersMainGridPage.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_SHORT).show();

                    }
                }
            });

    }
}
