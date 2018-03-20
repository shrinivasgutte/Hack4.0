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

public class layout_find_hospital extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Name,Address,ContactNo


    private final String[] HospitalID;
    private final String[] Name;
    private final String[] Address;
    private final String[] ContactNo;
    private final String[] EmergencyNo;
    private final String[] WorkingHours;





    public layout_find_hospital(Activity context, String[]HospitalID, String[]Name, String[]Address, String[]ContactNo, String[]EmergencyNo, String[] WorkingHours){
        super(context, R.layout.layout_recent_hospital,HospitalID);

        this.context=context;


       this.HospitalID=HospitalID;
       this.EmergencyNo=EmergencyNo;
       this.Name=Name;
       this.Address=Address;
       this.ContactNo=ContactNo;
       this.WorkingHours=WorkingHours;



   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_recent_hospital, null, true);

        TextView Qualificatio=(TextView)rowView.findViewById(R.id.medical_name);
        Qualificatio.setText(Name[position]);

        TextView date=(TextView)rowView.findViewById(R.id.casep_date);
        TextView datetitle=(TextView)rowView.findViewById(R.id.datetitle);
        datetitle.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);
        TextView emergencyno=(TextView)rowView.findViewById(R.id.emergencyno);
        emergencyno.setText(EmergencyNo[position]);

        TextView report_date=(TextView)rowView.findViewById(R.id.address);
        report_date.setText(Address[position]);
        TextView contactno=(TextView)rowView.findViewById(R.id.contactno);
        contactno.setText(ContactNo[position]);

       /*





        report_date.setText(Address[position]);
        */








        return rowView;
    }
}
