package com.techiessquad.mrtn.admin.Labs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techiessquad.mrtn.admin.R;

/**
 * Created by ajits on 17-12-2017.
 */

public class layout_all_labs extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,LabAddress,casep_Date,HospitalName
    
   
    private final String[] LabName;
    private final String[] LabID;
    private final String[] LabType;
    private final String[] LabAddress;
   /* private final String[] Speciality;
    private final String[] casep_Date;
    private final String[] HospitalName;*/



   public layout_all_labs(Activity context, String[]LabName, String[]LabID, String[]LabType, String[]LabAddress){
        super(context, R.layout.layout_all_labs,LabName);

        this.context=context;


       this.LabName=LabName;
       this.LabID=LabID;
       this.LabType=LabType;
       this.LabAddress=LabAddress;
     /*  this.Speciality=Speciality;
       this.casep_Date=casep_Date;
       this.HospitalName=HospitalName;
*/


   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_all_labs, null, true);

        TextView lab_name=(TextView)rowView.findViewById(R.id.lab_name);
        lab_name.setText(LabName[position]);

        TextView lab_type=(TextView)rowView.findViewById(R.id.lab_type);
        lab_type.setText(LabType[position]);

        TextView lab_address=(TextView)rowView.findViewById(R.id.lab_address);
        lab_address.setText(LabAddress[position]);



        return rowView;
    }
}
