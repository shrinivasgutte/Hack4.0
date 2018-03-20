package com.techiessquad.mrtn.admin.Pharmacy;

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

public class layout_all_pharmacy extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Address,casep_Date,HospitalName


    private final String[] Name;
    private final String[] MedicalID;
    private final String[] ContactNo;
    private final String[] Address;
   /* private final String[] Speciality;
    private final String[] casep_Date;
    private final String[] HospitalName;*/



   public layout_all_pharmacy(Activity context, String[]Name, String[]MedicalID, String[]ContactNo, String[]Address){
        super(context, R.layout.layout_all_pharmacy,Name);

        this.context=context;


       this.Name=Name;
       this.MedicalID=MedicalID;
       this.ContactNo=ContactNo;
       this.Address=Address;
     /*  this.Speciality=Speciality;
       this.casep_Date=casep_Date;
       this.HospitalName=HospitalName;
*/


   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_all_pharmacy, null, true);

        TextView lab_name=(TextView)rowView.findViewById(R.id.pharmacy_name);
        lab_name.setText(Name[position]);

        TextView lab_type=(TextView)rowView.findViewById(R.id.pharmacy_contactno);
        lab_type.setText("ContactNo: "+ContactNo[position]);

        TextView lab_address=(TextView)rowView.findViewById(R.id.pharmacy_address);
        lab_address.setText("Address: "+Address[position]);



        return rowView;
    }
}
