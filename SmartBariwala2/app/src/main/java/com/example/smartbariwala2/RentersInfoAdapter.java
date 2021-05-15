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

public class RentersInfoAdapter extends ArrayAdapter<RentersAddInfoConstractor> {

    private Activity context;
    private List<RentersAddInfoConstractor> studentList;

    public RentersInfoAdapter(Activity context, List<RentersAddInfoConstractor> studentList) {
        super(context, R.layout.rentersinfo, studentList);
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.rentersinfo,null,true);

        RentersAddInfoConstractor student = studentList.get(position);

        TextView t1 = view.findViewById(R.id.dateText);
        TextView t2 = view.findViewById(R.id.descriptionText);
        TextView t3 = view.findViewById(R.id.familyText);
        TextView t4 = view.findViewById(R.id.moblieText);
        TextView t5 = view.findViewById(R.id.nameText);
        TextView t6 = view.findViewById(R.id.unitText);


        t1.setText("Unit: "+student.getDate());
        t2.setText("Description: "+student.getDescription());
        t3.setText("Family: "+student.getFamily());
        t4.setText("Mobile: "+student.getMobile());
        t5.setText("Name: "+student.getName());
        t6.setText("Unit: "+student.getUnit());


        return view;
    }
}
