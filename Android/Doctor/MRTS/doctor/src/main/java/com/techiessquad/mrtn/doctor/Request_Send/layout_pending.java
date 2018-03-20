package com.techiessquad.mrtn.doctor.Request_Send;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.techiessquad.mrtn.doctor.R;
import com.techiessquad.mrtn.doctor.Strings;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajits on 17-12-2017.
 */

public class layout_pending extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] RequestID;
    private final String[] DoctorID;
    private final String[] PatientID;
    private final String[] Description;
    private final String[] Accept;
    private final String[] PatientIDColumnName;
    private final String[] ColumnName;
    private final String[] ColumnValue;
    private final String[] Type;
    static Button reject,accept;
    static String requestid, receivedValue;
    static String senderID,patientID,req_id,column_name,table_name,description,column_value,PatientIDColumnName_temp;

    private ProgressDialog progress;

   public layout_pending(Activity context, String[]RequestID, String[]DoctorID, String[] PatientID, String[]Description, String[]Accept, String[]ColumnName, String[]ColumnValue, String[]Type, String[] PatientIDColumnName){
        super(context, R.layout.pending_tab1,RequestID);

        this.context=context;
       this.RequestID=RequestID;
       this.DoctorID=DoctorID;
       this.PatientIDColumnName=PatientIDColumnName;
       this.PatientID=PatientID;
       this.Description=Description;
       this.Accept=Accept;
       this.ColumnName=ColumnName;
       this.ColumnValue=ColumnValue;
       this.Type=Type;
   }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.layout_pending_tab1, null, true);

        TextView description_TYPE=(TextView)rowView.findViewById(R.id.req_id);
        description_TYPE.setText("#reqno/00"+RequestID[position]);

        TextView description_date=(TextView)rowView.findViewById(R.id.desc_id);
        description_date.setText(Description[position]);

        reject = (Button)rowView.findViewById(R.id.reject_btn);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestid = RequestID[position];
                new backgroundProcessClass().execute();
            }
        });

        accept = (Button)rowView.findViewById(R.id.accept_btn);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestid = RequestID[position];
                senderID=DoctorID[position];
                patientID=PatientID[position];
                column_name=ColumnName[position];
                table_name=Type[position];
              description=Description[position];
              column_value=ColumnValue[position];
                PatientIDColumnName_temp=PatientIDColumnName[position];
                req_id=RequestID[position];

              //  Intent i = new Intent(context.getApplicationContext(), RequestAceptingProcess.class);
              //  context.startActivity(i);
            }
        });
        return rowView;
    }


    private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {


            progress = new ProgressDialog(context);
            progress.setMessage("Wait...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(false);
            progress.setProgress(0);
            progress.setCancelable(false);
            progress.show();// To start


            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                //Toast.makeText(context, "recievedValue: "+receivedValue, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context.getApplicationContext(), HomeActivity.class);
                context.startActivity(i);

                progress.dismiss();//To Stop


            }catch (Exception e)
            {
                Toast.makeText(context, "Error: "+e, Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }


        @Override
        protected Void doInBackground(String... params) {

            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(Strings.url+"Admin/request_rejecting.php");
            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("rej", requestid));


            try {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            } catch (Exception ex) {

            }
            //Perform HTTP Request
            try {
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                receivedValue = client.execute(post, responseHandler);


            } catch (Exception ex) {

            }
            return null;
        }
    }

}