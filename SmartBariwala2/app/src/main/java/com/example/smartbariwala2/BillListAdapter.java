package com.example.smartbariwala2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class BillListAdapter extends ArrayAdapter<OwnersAddBillConstractor> {

    private Activity context;
    private List<OwnersAddBillConstractor> studentList;

    public BillListAdapter(Activity context, List<OwnersAddBillConstractor> studentList) {
        super(context, R.layout.billlistview, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.billlistview,null,true);

        OwnersAddBillConstractor student = studentList.get(position);

        TextView t1 = view.findViewById(R.id.monthText);
        TextView t2 = view.findViewById(R.id.dateText);
        TextView t3 = view.findViewById(R.id.hoseText);
        TextView t4 = view.findViewById(R.id.gasText);
        TextView t5 = view.findViewById(R.id.electronicsText);
        TextView t6 = view.findViewById(R.id.otherText);


        t1.setText("Unit: "+student.getMonth());
        t2.setText("Date: "+student.getDate());
        t3.setText("House Bill: "+student.getHouseBill());
        t4.setText("Gas Bill: "+student.getGassBill());
        t5.setText("Electronic Bill: "+student.getGassBill());
        t6.setText("Other Bills: "+student.getOtherBill());


        return view;
    }
}
