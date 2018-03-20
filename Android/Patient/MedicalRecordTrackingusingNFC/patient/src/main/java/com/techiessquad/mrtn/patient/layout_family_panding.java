package com.techiessquad.mrtn.patient;

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

public class layout_family_panding extends ArrayAdapter<String> {

    private final Activity context;

    String patientNameSend_server;

    private final String[] m_Last_name;
    private final String[] m_first_name;
    private final String[] m_mid_name;
    private final String[] m_ContactNo;
    private final String[] MemberID;

    HttpPost post;
    static Button reject,accept;
    int btnflg=0;
    static String requestid, sadd, suname, spass, weburl, receivedValue;

    private ProgressDialog progress;

    public layout_family_panding(Activity context, String[]m_Last_name, String[]m_first_name, String[]m_mid_name, String[]m_ContactNo, String[]MemberID, String patientNameSend_server){
        super(context, R.layout.layout_panding_family,m_Last_name);

       this.context=context;
       this.m_Last_name=m_Last_name;
       this.m_first_name=m_first_name;
       this.m_mid_name=m_mid_name;
       this.m_ContactNo=m_ContactNo;
       this.MemberID=MemberID;

       this.patientNameSend_server=patientNameSend_server;
   }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        final View rowView=inflater.inflate(R.layout.layout_panding_family, null, true);

        TextView user_name=(TextView)rowView.findViewById(R.id.user_name);
        user_name.setText(m_Last_name[position]+" "+m_first_name[position]+" "+m_mid_name[position]+" ");

        TextView contact_no=(TextView)rowView.findViewById(R.id.contact_no);
        contact_no.setText(m_ContactNo[position]);

       // TextView users_type=(TextView)rowView.findViewById(R.id.users_type);
       // users_type.setText(users_Type[position]);

        reject = (Button)rowView.findViewById(R.id.reject_btn);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestid = MemberID[position];
                btnflg=1;
                new backgroundProcessClass().execute();
            }
        });
        accept = (Button)rowView.findViewById(R.id.accept_btn);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestid = MemberID[position];
                btnflg=2;
                new backgroundProcessClass().execute();
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
                Intent i = new Intent(context.getApplicationContext(), Family_Sharing.class);
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



                    if(btnflg==1) {
                        post = new HttpPost(State_and_City.url + "request_rejecting.php");
                    }else if(btnflg==2) {
                        post = new HttpPost(State_and_City.url + "request_accepting.php");
                    }
            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("rej", requestid));
            pairs.add(new BasicNameValuePair("p_id", patientNameSend_server));


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