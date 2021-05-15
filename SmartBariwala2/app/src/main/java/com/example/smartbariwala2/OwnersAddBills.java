package com.example.smartbariwala2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OwnersAddBills extends AppCompatActivity implements View.OnClickListener {

    EditText ownresNumber,rentersPassword,houseEditText,gassEditText,electricEditText,otherEditText,dateSet,month;
    Spinner monthSpinner;
    Button submitBill,spinnerButton,dateSetButton;
    DatabaseReference databaseReference;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_add_bills);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ownresNumber = (EditText) findViewById(R.id.ownresNumber);
        houseEditText = (EditText) findViewById(R.id.houseEditText);
        gassEditText = (EditText) findViewById(R.id.gassEditText);
        electricEditText = (EditText) findViewById(R.id.electricEditText);
        otherEditText = (EditText) findViewById(R.id.otherEditText);
        month = (EditText) findViewById(R.id.month);
        dateSet = (EditText) findViewById(R.id.dateSet);
        monthSpinner = (Spinner) findViewById(R.id.monthSpinner);
        submitBill = (Button) findViewById(R.id.submitBill);
        spinnerButton = (Button) findViewById(R.id.spinnerButton);
        dateSetButton = (Button) findViewById(R.id.dateSetButton);
        searchView = (SearchView) findViewById(R.id.searchFlat);

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
    public void onClick(View v) {

        if (v.getId() == R.id.submitBill){

            submit();

        }

        if (v.getId() == R.id.spinnerButton){

            final String value = monthSpinner.getSelectedItem().toString().toLowerCase();
            month.setText(value);


        }

        if (v.getId() == R.id.dateSetButton){

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
