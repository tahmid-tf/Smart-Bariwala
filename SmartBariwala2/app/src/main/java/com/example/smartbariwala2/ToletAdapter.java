package com.example.smartbariwala2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class ToletAdapter extends ArrayAdapter<ToLetConstractor> {

    private Activity context;
    private List<ToLetConstractor> studentList;

    public ToletAdapter(Activity context, List<ToLetConstractor> studentList) {
        super(context, R.layout.sample_layout, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.sample_layout,null,true);

        ToLetConstractor student = studentList.get(position);

        TextView t1 = view.findViewById(R.id.dateTextViewId);
        TextView t2 = view.findViewById(R.id.ownersNameTextViewId);
        TextView t3 = view.findViewById(R.id.ownersHomeInfoTextViewId);
        TextView t4 = view.findViewById(R.id.ownersAddressTextViewId);
        TextView t5 = view.findViewById(R.id.ownersLocationTextViewId);

        t1.setText(student.getDate());
        t2.setText(student.getOwnersName());
        t3.setText(student.getOwnersHomeInfo());
        t4.setText(student.getOwnersAddress());
        t5.setText(student.getOwnersLocation());

        return view;
    }
}
