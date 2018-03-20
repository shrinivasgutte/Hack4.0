package com.techiessquad.mrtn.doctor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CasePaperAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] descriptionType;
    private final String[] descriptionDate;
    private final String[] doctorName;
    private final String[] hospitalName;

    public CasePaperAdapter(Activity context, String[] descriptionType,  String[] descriptionDate, String[] doctorName,String[] hospitalName){
        super(context, R.layout.layout_casepaper,descriptionType);

        this.context=context;
        this.descriptionType=descriptionType;
        this.descriptionDate=descriptionDate;
        this.doctorName=doctorName;
        this.hospitalName=hospitalName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_casepaper, null, true);
        TextView description_TYPE=(TextView)rowView.findViewById(R.id.description_type);
        description_TYPE.setText(descriptionType[position]);
        TextView description_date=(TextView)rowView.findViewById(R.id.description_date);
        description_date.setText(descriptionDate[position]);
        TextView doctor_name=(TextView)rowView.findViewById(R.id.doctor_name);
        doctor_name.setText(doctorName[position]);
        TextView hospital_name=(TextView)rowView.findViewById(R.id.hospital_name);
        hospital_name.setText(hospitalName[position]);
        return rowView;
    }
}
