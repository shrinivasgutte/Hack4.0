package com.techiessquad.mrtn.patient;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ajits on 17-12-2017.
 */

public class layout_find_lab extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Address,reportDate,HospitalName


    private final String[] LabName;
    private final String[] LabID;
    private final String[] Type;
    private final String[] Address;





   public layout_find_lab(Activity context, String[]LabName, String[]LabID, String[]Type, String[]Address){
        super(context, R.layout.layout_find_doctor,LabName);

        this.context=context;


       this.LabName=LabName;
       this.LabID=LabID;
       this.Type=Type;
       this.Address=Address;





   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_find_doctor, null, true);

        CircleImageView lab_icon =(CircleImageView)rowView.findViewById(R.id.lab_icon);
        lab_icon.setVisibility(View.VISIBLE);

        CircleImageView profile_image =(CircleImageView)rowView.findViewById(R.id.profile_image);
        profile_image.setVisibility(View.INVISIBLE);

        TextView Qualificatio_hint=(TextView)rowView.findViewById(R.id.quali_hint);
        Qualificatio_hint.setText("Lab Type");
        TextView report_name_hint=(TextView)rowView.findViewById(R.id.dhospital_name_hint);
        report_name_hint.setText("Address");

        TextView lab_name=(TextView)rowView.findViewById(R.id.doctor_name);
        lab_name.setText(LabName[position]);


        TextView Type_lab=(TextView)rowView.findViewById(R.id.quali);
        Type_lab.setText(Type[position]);



        TextView lab_Address=(TextView)rowView.findViewById(R.id.dhospital_name);
        lab_Address.setText(Address[position]);

     /*   TextView Lab_name=(TextView)rowView.findViewById(R.id.lab_name);
        Lab_name.setText(LabName[position]);

        TextView report_date=(TextView)rowView.findViewById(R.id.type);
        report_date.setText(Type[position]);


        TextView address=(TextView)rowView.findViewById(R.id.address);
        address.setText(Address[position]);
        */







        return rowView;
    }
}
