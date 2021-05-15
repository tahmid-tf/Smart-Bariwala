package com.example.smartbariwala2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class OwnersMainPage extends AppCompatActivity implements View.OnClickListener {

    Button addBills,showBills,emargencyMessage1,tolet,showRentersComplain,clearRentersComplain;
    FirebaseAuth mAuth;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_main_page);

        addBills = (Button) findViewById(R.id.addBills);
        showBills = (Button) findViewById(R.id.showBills);
        emargencyMessage1 = (Button) findViewById(R.id.emargencyMessage);
        tolet = (Button) findViewById(R.id.tolet);
        showRentersComplain = (Button) findViewById(R.id.showRentersComplain);
        clearRentersComplain = (Button) findViewById(R.id.clearRentersComplain);

        addBills.setOnClickListener(this);
        showBills.setOnClickListener(this);
        emargencyMessage1.setOnClickListener(this);
        tolet.setOnClickListener(this);
        showRentersComplain.setOnClickListener(this);
        clearRentersComplain.setOnClickListener(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.signOut){

            mAuth.signOut();
            Toast.makeText(getApplicationContext(),"Successfully Signed Out",Toast.LENGTH_SHORT).show();


            finish();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.showBills){

          Intent intent = new Intent(getApplicationContext(),OwnersShowBills.class);
          startActivity(intent);

        }

        if(v.getId() == R.id.addBills){
            Intent intent = new Intent(getApplicationContext(),SecurityPass2.class);
            startActivity(intent);
        }


        if (v.getId() == R.id.emargencyMessage){
            Toast.makeText(getApplicationContext(),"Under Maintanance",Toast.LENGTH_SHORT).show();

        }

        if (v.getId() == R.id.tolet){
            Intent intent = new Intent(getApplicationContext(),OwnersAddTolet.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.showRentersComplain){
            Intent intent = new Intent(getApplicationContext(),ShowComplainSecurityPass.class);
            startActivity(intent);

        }

        if (v.getId() == R.id.clearRentersComplain){

            builder = new AlertDialog.Builder(this);

            builder.setIcon(R.drawable.ic_warning_black_24dp);
            builder.setTitle("Warning");
            builder.setMessage("Are you sure?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    Intent intent = new Intent(getApplicationContext(),ClearComplainSecurityPass.class);
                    startActivity(intent);

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
}
