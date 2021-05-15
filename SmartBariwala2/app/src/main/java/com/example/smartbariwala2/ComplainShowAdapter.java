package com.example.smartbariwala2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



import java.util.List;

public class ComplainShowAdapter extends ArrayAdapter<RenterComplainAddConstractor> {

    private Activity context;
    private List<RenterComplainAddConstractor> studentList;

    public ComplainShowAdapter(Activity context, List<RenterComplainAddConstractor> studentList) {
        super(context, R.layout.complainlayout, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.complainlayout,null,true);

        RenterComplainAddConstractor constractor = studentList.get(position);

        TextView t1 = view.findViewById(R.id.unitTextViewId);
        TextView t2 = view.findViewById(R.id.dateTextViewId);
        TextView t3 = view.findViewById(R.id.complainTextViewId);


        t1.setText(constractor.getUnit());
        t2.setText(constractor.getDate());
        t3.setText(constractor.getComplain());

        return view;
    }
}
