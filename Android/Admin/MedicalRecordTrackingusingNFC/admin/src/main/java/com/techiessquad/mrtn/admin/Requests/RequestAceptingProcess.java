package com.techiessquad.mrtn.admin.Requests;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techiessquad.mrtn.admin.R;
import com.techiessquad.mrtn.admin.State_and_City;

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

public class RequestAceptingProcess extends AppCompatActivity {

    TextView value_hint,desc,doctor_name,patient_name,old_value,table_name,column_name,p_id;
    Button btn_update;

    String receivedValue;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_acepting_process);

        try {
                    doctor_name=(TextView)findViewById(R.id.doctor_name);
            patient_name=(TextView)findViewById(R.id.patient_name);
            desc=(TextView)findViewById(R.id.desc);
            table_name=(TextView)findViewById(R.id.table_name);
            value_hint=(TextView)findViewById(R.id.vlaue_hint);
            column_name=(TextView)findViewById(R.id.column_name);
                    p_id=(TextView)findViewById(R.id.p_id);
            btn_update =(Button)findViewById(R.id.btn_update) ;

                    value_hint.setText("New "+layout_pending.column_name+" :");
                    desc.setText(layout_pending.description);
                    doctor_name.setText(layout_pending.senderID);
                    patient_name.setText(layout_pending.patientID);
                    table_name.setText(layout_pending.table_name);
                    column_name.setText(layout_pending.column_name);
                    p_id.setText(layout_pending.column_value);

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new backgroundProcessClass().execute();

                }
            });

        }catch (Exception s){
            Toast.makeText(this, "oncc "+s, Toast.LENGTH_SHORT).show();
        }

    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4 send server $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            try {


            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected Void doInBackground(String... params) {


            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(State_and_City.url + "RequestAcepting.php");

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);


            pairs.add(new BasicNameValuePair("tableName", layout_pending.table_name));
            pairs.add(new BasicNameValuePair("columnName",layout_pending.column_name));
            pairs.add(new BasicNameValuePair("value", layout_pending.column_value));
            pairs.add(new BasicNameValuePair("whereNext", layout_pending.patientID));
            pairs.add(new BasicNameValuePair("PatientIDColumnName", layout_pending.PatientIDColumnName_temp));
            pairs.add(new BasicNameValuePair("ReqID", layout_pending.req_id));


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

        @Override
        protected void onPostExecute(Void aVoid) {
            try {




                Toast.makeText(context, "recive + "+receivedValue, Toast.LENGTH_SHORT).show();
                if (receivedValue.contains("success")) {
                    Intent s =new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(s);
                    finish();
                }

            } catch (Exception e) {

                //  Toast.makeText(context, "Error_last:"+e, Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }
    }

}
