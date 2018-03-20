package com.techiessquad.mrtn.patient;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ajits on 17-12-2017.
 */

public class layout_recent_lab extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Address,reportDate,HospitalName
    
   
    private final String[] LabName;
    private final String[] LabID;
    private final String[] Type;
    private final String[] Address;
    private final String[] reportDate;




   public layout_recent_lab(Activity context, String[]LabName, String[]LabID, String[]Type, String[]Address, String[]reportDate){
        super(context, R.layout.layout_recent_lab,LabName);

        this.context=context;


       this.LabName=LabName;
       this.LabID=LabID;
       this.Type=Type;
       this.Address=Address;
       this.reportDate=reportDate;




   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_recent_lab, null, true);

        TextView Lab_name=(TextView)rowView.findViewById(R.id.lab_name);
        Lab_name.setText(LabName[position]);

        TextView report_Date=(TextView)rowView.findViewById(R.id.reportDate);
        report_Date.setText(reportDate[position]);

        TextView lab_type=(TextView)rowView.findViewById(R.id.lab_type);
        lab_type.setText(Type[position]);

        TextView address=(TextView)rowView.findViewById(R.id.address);
        address.setText(Address[position]);







        return rowView;
    }
}
