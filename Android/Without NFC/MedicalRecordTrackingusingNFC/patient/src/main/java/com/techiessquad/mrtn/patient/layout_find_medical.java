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

public class layout_find_medical extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Name,Address,ContactNo


    private final String[] MedicalID;
    private final String[] Name;
    private final String[] Address;
    private final String[] ContactNo;
    private final String[] WorkerID;




    public layout_find_medical(Activity context, String[]MedicalID, String[]Name, String[]Address, String[]ContactNo, String[]WorkerID){
        super(context, R.layout.layout_recent_medical,MedicalID);

        this.context=context;


       this.MedicalID=MedicalID;
       this.WorkerID=WorkerID;
       this.Name=Name;
       this.Address=Address;
       this.ContactNo=ContactNo;




   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_recent_medical, null, true);

        TextView Qualificatio=(TextView)rowView.findViewById(R.id.medical_name);
        Qualificatio.setText(Name[position]);

        TextView date=(TextView)rowView.findViewById(R.id.casep_date);
        TextView datetitle=(TextView)rowView.findViewById(R.id.datetitle);
        datetitle.setVisibility(View.INVISIBLE);
        date.setVisibility(View.INVISIBLE);

        TextView report_date=(TextView)rowView.findViewById(R.id.address);
        report_date.setText(Address[position]);

        TextView contactno=(TextView)rowView.findViewById(R.id.contactno);
        contactno.setText(ContactNo[position]);








        return rowView;
    }
}
