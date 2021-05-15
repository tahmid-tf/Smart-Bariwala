package com.example.smartbariwala2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class OwnersSetupRentersPass extends AppCompatActivity{

    EditText l1u1,l1u2,l1u3,l1u4,l2u1,l2u2,l2u3,l2u4,l3u1,l3u2,l3u3,l3u4,l4u1,l4u2,l4u3,l4u4;
    DatabaseReference databaseReference;
    Button passSetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_setup_renters_pass);

        Random random = new Random();

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("number");

        passSetButton = (Button) findViewById(R.id.passSetButton);

        l1u1 = (EditText) findViewById(R.id.l1u1);
        l1u2 = (EditText) findViewById(R.id.l1u2);
        l1u3 = (EditText) findViewById(R.id.l1u3);
        l1u4 = (EditText) findViewById(R.id.l1u4);

        l2u1 = (EditText) findViewById(R.id.l2u1);
        l2u2 = (EditText) findViewById(R.id.l2u2);
        l2u3 = (EditText) findViewById(R.id.l2u3);
        l2u4 = (EditText) findViewById(R.id.l2u4);

        l3u1 = (EditText) findViewById(R.id.l3u1);
        l3u2 = (EditText) findViewById(R.id.l3u2);
        l3u3 = (EditText) findViewById(R.id.l3u3);
        l3u4 = (EditText) findViewById(R.id.l3u4);

        l4u1 = (EditText) findViewById(R.id.l4u1);
        l4u2 = (EditText) findViewById(R.id.l4u2);
        l4u3 = (EditText) findViewById(R.id.l4u3);
        l4u4 = (EditText) findViewById(R.id.l4u4);

        String random1 = String.format("%04d", random.nextInt(10000));
        String random2 = String.format("%04d", random.nextInt(10000));
        String random3 = String.format("%04d", random.nextInt(10000));
        String random4 = String.format("%04d", random.nextInt(10000));

        String random5 = String.format("%04d", random.nextInt(10000));
        String random6 = String.format("%04d", random.nextInt(10000));
        String random7 = String.format("%04d", random.nextInt(10000));
        String random8 = String.format("%04d", random.nextInt(10000));

        String random9 = String.format("%04d", random.nextInt(10000));
        String random10 = String.format("%04d", random.nextInt(10000));
        String random11 = String.format("%04d", random.nextInt(10000));
        String random12 = String.format("%04d", random.nextInt(10000));

        String random13 = String.format("%04d", random.nextInt(10000));
        String random14 = String.format("%04d", random.nextInt(10000));
        String random15 = String.format("%04d", random.nextInt(10000));
        String random16 = String.format("%04d", random.nextInt(10000));

        l1u1.setText(random1);
        l1u2.setText(random2);
        l1u3.setText(random3);
        l1u4.setText(random4);

        l2u1.setText(random5);
        l2u2.setText(random6);
        l2u3.setText(random7);
        l2u4.setText(random8);

        l3u1.setText(random9);
        l3u2.setText(random10);
        l3u3.setText(random11);
        l3u4.setText(random12);

        l4u1.setText(random13);
        l4u2.setText(random14);
        l4u3.setText(random15);
        l4u4.setText(random16);

        databaseReference = FirebaseDatabase.getInstance().getReference(value);


        final String l1u1s = l1u1.getText().toString().trim();
        final String l1u2s = l1u2.getText().toString().trim();
        final String l1u3s = l1u3.getText().toString().trim();
        final String l1u4s = l1u4.getText().toString().trim();

        final String l2u1s = l2u1.getText().toString().trim();
        final String l2u2s = l2u2.getText().toString().trim();
        final String l2u3s = l2u3.getText().toString().trim();
        final String l2u4s = l2u4.getText().toString().trim();

        final String l3u1s = l3u1.getText().toString().trim();
        final String l3u2s = l3u2.getText().toString().trim();
        final String l3u3s = l3u3.getText().toString().trim();
        final String l3u4s = l3u4.getText().toString().trim();

        final String l4u1s = l4u1.getText().toString().trim();
        final String l4u2s = l4u2.getText().toString().trim();
        final String l4u3s = l4u3.getText().toString().trim();
        final String l4u4s = l4u4.getText().toString().trim();



        passSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SetupUnitPassContstractor contstractor = new SetupUnitPassContstractor(l1u1s,l1u2s,l1u3s,l1u4s,l2u1s,l2u2s,l2u3s,l2u4s,l3u1s,l3u2s,l3u3s,l3u4s,l4u1s,l4u2s,l4u3s,l4u4s);
                databaseReference.child("unit-password").setValue(contstractor);

                finish();

                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.pass,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.pass)
        {

            Bundle bundle = getIntent().getExtras();
            String value = bundle.getString("number");

            Intent intent = new Intent(getApplicationContext(),ShowAllPasswords.class);
            intent.putExtra("number",value);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
