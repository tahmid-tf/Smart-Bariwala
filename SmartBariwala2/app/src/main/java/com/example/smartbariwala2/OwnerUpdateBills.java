package com.example.smartbariwala2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnerUpdateBills extends AppCompatActivity implements View.OnClickListener {

    EditText ownresNumber,rentersPassword,houseEditText,gassEditText,electricEditText,otherEditText,dateSet,month;
    Spinner monthSpinner;
    Button submitBill,spinnerButton,dateSetButton;
    DatabaseReference databaseReference;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_update_bills);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ownresNumber = (EditText) findViewById(R.id.ownresNumberUp);
        houseEditText = (EditText) findViewById(R.id.houseEditTextUp);
        gassEditText = (EditText) findViewById(R.id.gassEditTextUp);
        electricEditText = (EditText) findViewById(R.id.electricEditTextUp);
        otherEditText = (EditText) findViewById(R.id.otherEditTextUp);
        month = (EditText) findViewById(R.id.monthUp);
        dateSet = (EditText) findViewById(R.id.dateSetUp);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinnerUp);
        submitBill = (Button) findViewById(R.id.submitBillUp);
        spinnerButton = (Button) findViewById(R.id.spinnerButtonUp);
        dateSetButton = (Button) findViewById(R.id.dateSetButtonUp);
        searchView = (SearchView) findViewById(R.id.searchFlatUp);

        final String months [] = getResources().getStringArray(R.array.months);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_addbills,R.id.spinnerAdapter,months);
        monthSpinner.setAdapter(adapter);

        spinnerButton.setOnClickListener(this);
        submitBill.setOnClickListener(this);
        dateSetButton.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("number");
        ownresNumber.setText(""+value);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.submitBillUp){

            submit();

        }

        if (view.getId() == R.id.spinnerButtonUp){

            final String value = monthSpinner.getSelectedItem().toString().toLowerCase();
            month.setText(value);

            Bundle bundle = getIntent().getExtras();
            String update = bundle.getString("update");
            String number = bundle.getString("number");

            String match = "update";

            if (update.equals(match))
            {
                databaseReference = FirebaseDatabase.getInstance().getReference().child(number);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        if (dataSnapshot.child("bills").child(value).exists()){

                            String month1 = dataSnapshot.child("bills").child(value).child("month").getValue().toString().toUpperCase();
                            String date1 = dataSnapshot.child("bills").child(value).child("date").getValue().toString();
                            String electronicBill1 = dataSnapshot.child("bills").child(value).child("electronicBill").getValue().toString();
                            String gassBill1 = dataSnapshot.child("bills").child(value).child("gassBill").getValue().toString();
                            String otherBill1 = dataSnapshot.child("bills").child(value).child("otherBill").getValue().toString();
                            String houseBill1 = dataSnapshot.child("bills").child(value).child("houseBill").getValue().toString();

                            electricEditText.setText(electronicBill1);
                            gassEditText.setText(gassBill1);
                            otherEditText.setText(otherBill1);
                            houseEditText.setText(houseBill1);
                            dateSet.setText(date1);
                        }

                        else {

                            Toast.makeText(getApplicationContext(),"Bill does not yet exists", Toast.LENGTH_LONG).show();

                            electricEditText.setText(null);
                            gassEditText.setText(null);
                            otherEditText.setText(null);
                            houseEditText.setText(null);
                            dateSet.setText(null);
                        }

                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }

        if (view.getId() == R.id.dateSetButtonUp){

            DatePicker datePicker = new DatePicker(this);
            int currentDate1 = datePicker.getDayOfMonth();
            int currentMonth1 = (datePicker.getMonth() + 1);
            int currentYear1 = datePicker.getYear();

            String currentDate = String.valueOf(currentDate1);
            String currentMonth = String.valueOf(currentMonth1);
            String currentYear = String.valueOf(currentYear1);

            String date = currentDate+"."+currentMonth+"."+currentYear;
            dateSet.setText(date);

        }
    }

    private void submit() {


        String month1 = month.getText().toString().trim().toLowerCase();
        String dateSet1 = dateSet.getText().toString().trim();
        String houseBill = houseEditText.getText().toString().trim();
        String gassBill = gassEditText.getText().toString().trim();
        String electronicBill = electricEditText.getText().toString().trim();
        String otherBill = otherEditText.getText().toString().trim();
        String ownerNumber1 = ownresNumber.getText().toString().trim();

        if (month1.isEmpty()){
            month.setError("Can not be left to empty or set value to 0");
            month.requestFocus();
            return;
        }

        if (dateSet1.isEmpty()){
            dateSet.setError("Can not be left to empty or set value to 0");
            dateSet.requestFocus();
            return;
        }

        if (houseBill.isEmpty()){
            houseEditText.setError("Can not be left to empty or set value to 0");
            houseEditText.requestFocus();
            return;
        }

        if (gassBill.isEmpty()){
            gassEditText.setError("Can not be left to empty or set value to 0");
            gassEditText.requestFocus();
            return;
        }

        if (electronicBill.isEmpty()){
            electricEditText.setError("Can not be left to empty or set value to 0");
            electricEditText.requestFocus();
            return;
        }

        if (otherBill.isEmpty()){
            otherEditText.setError("Can not be left to empty or set value to 0");
            otherEditText.requestFocus();
            return;
        }

        if (ownerNumber1.isEmpty()){
            ownresNumber.setError("Can not be left to empty or set value to 0");
            ownresNumber.requestFocus();
            return;
        }


        databaseReference = FirebaseDatabase.getInstance().getReference(""+ownerNumber1);
        String key = databaseReference.push().getKey();
        OwnersAddBillConstractor ownersAddBillConstractor = new OwnersAddBillConstractor(month1,dateSet1,houseBill,gassBill,electronicBill,otherBill);
        databaseReference.child("bills").child(month1).setValue(ownersAddBillConstractor);

        databaseReference.child("bill-list").child(key).setValue(ownersAddBillConstractor);


    }
}
