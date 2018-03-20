package com.techiessquad.mrtn.doctor.Request_Send;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.techiessquad.mrtn.doctor.R;


/**
 * Created by ajits on 17-12-2017.
 */

public class layout_rejected extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] RequestID;
    private final String[] PatientID;
    private final String[] Description;
    private final String[] Accept;
    private final String[] ColumnName;
    private final String[] ColumnValue;
    private final String[] Type;


   public layout_rejected(Activity context, String[]RequestID, String[]PatientID, String[]Description, String[]Accept, String[]ColumnName, String[]ColumnValue, String[]Type){
        super(context, R.layout.rejected_tab3,RequestID);

       this.context=context;
       this.RequestID=RequestID;
       this.PatientID=PatientID;
       this.Description=Description;
       this.Accept=Accept;
       this.ColumnName=ColumnName;
       this.ColumnValue=ColumnValue;
       this.Type=Type;
   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_rejected_tab3, null, true);

        TextView description_TYPE=(TextView)rowView.findViewById(R.id.req_id);
        description_TYPE.setText("#reqno/00"+RequestID[position]);

        TextView description_date=(TextView)rowView.findViewById(R.id.desc_id);
        description_date.setText(Description[position]);

        return rowView;
    }
}
