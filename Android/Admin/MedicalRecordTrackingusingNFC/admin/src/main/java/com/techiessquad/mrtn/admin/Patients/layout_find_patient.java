package com.techiessquad.mrtn.admin.Patients;

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

public class layout_find_patient extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Qualification,casep_Date,HospitalName


    private final String[] doctorNameL;
    private final String[] doctorNameF;
    private final String[] doctorNameM;
    /*private final String[] Qualification;
    private final String[] Speciality;
    private final String[] casep_Date;
    private final String[] HospitalName;*/



   public layout_find_patient(Activity context, String[]doctorNameL, String[]doctorNameF, String[]doctorNameM){
        super(context, R.layout.layout_find_patient,doctorNameL);

        this.context=context;


       this.doctorNameL=doctorNameL;
       this.doctorNameF=doctorNameF;
       this.doctorNameM=doctorNameM;
       /*this.Qualification=Qualification;
       this.Speciality=Speciality;
       this.casep_Date=casep_Date;
       this.HospitalName=HospitalName;*/



   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_find_patient, null, true);

       /* TextView Qualificatio=(TextView)rowView.findViewById(R.id.quali);
        Qualificatio.setText(Qualification[position]);

        TextView report_date=(TextView)rowView.findViewById(R.id.speciality);
        report_date.setText(Speciality[position]);

        TextView report_name=(TextView)rowView.findViewById(R.id.dhospital_name);
        report_name.setText(HospitalName[position]);*/

        TextView doctor_name=(TextView)rowView.findViewById(R.id.doctor_name);
        doctor_name.setText("Dr. "+doctorNameF[position]+" "+doctorNameM[position]+" "+doctorNameL[position]);


        return rowView;
    }
}
