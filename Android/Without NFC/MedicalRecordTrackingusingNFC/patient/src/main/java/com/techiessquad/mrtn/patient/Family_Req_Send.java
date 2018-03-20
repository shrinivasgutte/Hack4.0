package com.techiessquad.mrtn.patient;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Family_Req_Send extends AppCompatActivity {
//get data
public  String EPatientID,Type;
    String receivedValue, clearChat = "no", delete;
    final Context context = this;

    public static int  cnt1;

   // public static String myJSON;

    public static String myJSON,ID;

    JSONArray peoples = null;
    ProgressBar progressBar;


    public  static  int cnt;
    String weburl="http://192.168.0.3/MRTS/";
    //get dataend

    Button LoginB;
    EditText Username,relation;


    String[][] techListsArray;

public String Relation;
    private static final String TAG_RESULTS="result";
    public static String IDS,Data;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__req__send);



        progressBar= (ProgressBar)findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.VISIBLE);


        progressBar.setVisibility(View.INVISIBLE);
        LoginB = (Button) findViewById(R.id.login_button);
        Username = (EditText) findViewById(R.id.login_username);
        relation = (EditText)findViewById(R.id.relation);
        try {
            setTitle("Add User");
        LoginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Username.getText()!=null)
                {
                    url.UsernameS=Username.getText().toString();
                   // i = new Intent(getApplicationContext(),PasswordActivity.class);
                  //  startActivity(i);
                    Toast.makeText(Family_Req_Send.this, "id "+IDS, Toast.LENGTH_SHORT).show();

                    Relation =  relation.getText().toString();
                    if(Relation.equals("")){
                        relation.setError("Enter Relation.");
                    }else {
                        getData();
                      //  Username.setText("");


                          progressBar.setVisibility(View.VISIBLE);


                    }
                }

            }
        });
        }catch (Exception s){
            Toast.makeText(this, "gd 1 "+s, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

                if(Username != null){
                    httppost = new HttpPost(State_and_City.url + "EmergencySend.php?Username=" +url.UsernameS);
                    //Toast.makeText(PasswordActivity.this, "1 "+url.Url+"login.php?Username="+Username+"&Password="+PasswordS, Toast.LENGTH_LONG).show();
                }else  if(IDS != null){
                    httppost = new HttpPost(State_and_City.url + "EmergencySend.php?NFCID=" +IDS);
                }


                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    Toast.makeText(getApplicationContext(), "dd", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    // Oops

                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result){
                try {


                    myJSON=result;
                    Toast.makeText(Family_Req_Send.this, "myj "+myJSON, Toast.LENGTH_SHORT).show();
                    showList();

                }catch (Exception f){

                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Network :1:\n\n", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);   }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            cnt=0;
            cnt1=0;


            for(int i=0;i<peoples.length();i++){
                cnt++;
            }

         /*   LabName = new String[cnt];
            LabID = new String[cnt];
            Type = new String[cnt];
            Address = new String[cnt];
            LabDate= new String[cnt];
*/



            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


             /*   LabName[cnt1] = c.getString("LabName");
                LabID[cnt1] = c.getString("LabID");
                Type[cnt1] = c.getString("Type");
                Address[cnt1] = c.getString("Address");
                LabDate[cnt1] = c.getString("reportDate");

*/


                progressBar.setVisibility(View.INVISIBLE);
             EPatientID = c.getString("ID");
                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
            //  setTitle("sfse1");

            new backgroundProcessClass().execute();
            Toast.makeText(this, "EPatientID "+EPatientID, Toast.LENGTH_SHORT).show();



            if(cnt==0) {
                Toast.makeText(this, "No Records Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this, "last"+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4 send server $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$

    private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            try {

                progressBar.setVisibility(View.VISIBLE);


            } catch (Exception e) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected Void doInBackground(String... params) {


            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(State_and_City.url + "SendFamilyReq.php");

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);


           pairs.add(new BasicNameValuePair("PatientID", EPatientID));
            pairs.add(new BasicNameValuePair("SenderID", State_and_City.patientNameSend_server));
            pairs.add(new BasicNameValuePair("Relation", Relation));


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


                progressBar.setVisibility(View.INVISIBLE);
                clearChat = "no";

                Toast.makeText(context, "recive + "+receivedValue, Toast.LENGTH_SHORT).show();
                if (receivedValue.contains("pas")) {


                } else if (receivedValue.contains("Student")) {

                } else if (receivedValue.contains("fail")) {


                } else if (receivedValue.equals("    ")) {


                }

            } catch (Exception e) {

                //  Toast.makeText(context, "Error_last:"+e, Toast.LENGTH_SHORT).show();
            }
            super.onPostExecute(aVoid);
        }
    }

}


