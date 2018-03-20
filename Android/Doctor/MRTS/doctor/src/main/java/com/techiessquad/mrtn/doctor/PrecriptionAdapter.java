package com.techiessquad.mrtn.doctor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PrecriptionAdapter extends ArrayAdapter<String> {

   // ,PrescriptionID, PrescPicture,Date,D_Last_name,D_first_name,D_mid_name
    
    private final Activity context;

    private final String[] DoctorID;
    private final String[] PrescriptionID;
    private final String[] PrescPicture;
    private final String[] Date;
    private final String[] DName;



   public PrecriptionAdapter(Activity context, String[]DoctorID, String[]PrescriptionID, String[]PrescPicture, String[]Date, String[] DName){
        super(context, R.layout.layout_medical_pris,PrescriptionID);

       this.context=context;
       this.DoctorID=DoctorID;
       this.PrescriptionID=PrescriptionID;
       this.PrescPicture=PrescPicture;
       this.Date=Date;
       this.DName=DName;

   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_medical_pris, null, true);
        TextView Prescriptiont_ID=(TextView)rowView.findViewById(R.id.prescriptionID);
        Prescriptiont_ID.setText(PrescriptionID[position]);
        TextView Prescriptiont_date=(TextView)rowView.findViewById(R.id.prescription_date);
        Prescriptiont_date.setText(Date[position]);
        TextView doctor_name=(TextView)rowView.findViewById(R.id.doctor_name);
        doctor_name.setText(DName[position]);
        return rowView;
    }
}
