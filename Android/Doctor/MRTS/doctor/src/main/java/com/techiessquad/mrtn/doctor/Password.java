package com.techiessquad.mrtn.doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techiessquad.mrtn.doctor.PatientProfileDitails.Patient_Offline_Data;
import com.techiessquad.mrtn.doctor.Request_Send.HomeActivity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Password extends AppCompatActivity {
    private ProgressDialog progress;
    EditText Password;
    Button PasswordB;
    String Username,NFCID,Data,Type;
    static  String PasswordS;
    Intent i;

    public static String myJSON,ID;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    public  static  int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        try {
            progress=new ProgressDialog(this);
            Password = (EditText) findViewById(R.id.Password);
            PasswordB = (Button) findViewById(R.id.PasswordB);
                Password.setVisibility(View.INVISIBLE);
                PasswordB.setVisibility(View.INVISIBLE);

            /*    PasswordB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            PasswordS=Password.getText().toString();*/
                    //Toast.makeText(Password.this, "uname : "+Username+" pas : "+PasswordS+" nfc : "+NFCID, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(PasswordActivity.this, "uname : "+Username+" pas : "+Password+" nfc : "+NFCID, Toast.LENGTH_SHORT).show();

                  /*  if(PasswordS.equals(""))
                    Password.setError("Enter Password");
                    else {*/
            progress.setMessage("Verifing...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();
                        getData();

                  /*  }
                }
            });*/


        }catch (Exception s){
            Toast.makeText(this, "p "+s, Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost= new HttpPost(Strings.url+"Doctor/login.php");

                try {


                    if(Username != null){
                        httppost= new HttpPost(Strings.url+"Doctor/login.php?Username="+Username+"&Password="+PasswordS);
                        //Toast.makeText(PasswordActivity.this, "1 "+url.Url+"login.php?Username="+Username+"&Password="+PasswordS, Toast.LENGTH_LONG).show();
                    }else  if(NFCID != null){
                        httppost= new HttpPost(Strings.url+"Doctor/login.php?NFCID="+NFCID+"&Password="+PasswordS);
                    }
                }catch (Exception s){
                    Toast.makeText(Password.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
                }

               /* try {
                    if (Username != "") {
                        List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                        pairs.add(new BasicNameValuePair("ID", NFCID));
                        pairs.add(new BasicNameValuePair("Password", Password.getText().toString()));
                        try {
                            httppost.setEntity(new UrlEncodedFormEntity(pairs));
                        } catch (Exception ex) {
                        }
                    } else if (NFCID != "") {
                        List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
                        pairs.add(new BasicNameValuePair("Username", Username));
                        pairs.add(new BasicNameValuePair("Password", Password.getText().toString()));
                        try {
                            httppost.setEntity(new UrlEncodedFormEntity(pairs));
                        } catch (Exception ex) {
                        }
                    }


                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");
                }catch (Exception s){
                    Toast.makeText(PasswordActivity.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
                }*/


                // Depends on your web service
                try{
                    httppost.setHeader("Content-type", "application/json");
                }catch (Exception s){
                    Toast.makeText(Password.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
                }
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
                try{
                    progress.dismiss();

                    //  Toast.makeText(PasswordActivity.this, result, Toast.LENGTH_SHORT).show();
                    if(result.contains("fail"))
                    {
                        i = new Intent(getApplicationContext(),Login.class);
                        startActivity(i);
                        finish();
                        Login.Passwordp.setError("Please Enter Correct Password");
                        Toast.makeText(Password.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        myJSON=result;
                       //    Toast.makeText(Password.this, "Data : "+result, Toast.LENGTH_SHORT).show();
                        showList();
                    }
                }catch (Exception s){
                    i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(Password.this, "GO Online :Login:", Toast.LENGTH_SHORT).show();
                }
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
            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                cnt++;
            }
            if(cnt==0)
            {
                i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }
//static String FirstName,MiddleName,LastName,Email,patientNameSend_server,User_State,User_City;

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

              ID= State_and_City.staticDR_ID=c.getString("ID");
                Type=   State_and_City. staticDR_Type=c.getString("Type");

                State_and_City.saticDoctorName=c.getString("FirstName")+" "+c.getString("MiddleName")+" "+c.getString("MiddleName");
                State_and_City.staticEmail=c.getString("Email");
                State_and_City.staticState=c.getString("State");
                State_and_City.      staticCity=c.getString("City");
                State_and_City.     staticHospitalID=c.getString("HospitalID");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (Type)
        {
            case "Doctor":

                i = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
                break;
            case  "Admin":
                i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                Toast.makeText(this, "Please use Admin App", Toast.LENGTH_SHORT).show();
                //   i = new Intent(getApplicationContext(),Admin_Activity.class);
                //  startActivity(i);
                //  finish();
                break;
            case "Patient":
                i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
                finish();
                Toast.makeText(this, "Please use Patient App", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Type=null;
        NFCID = Login.IDS;
        Username =Login.UsernameS;
        Data = Login.Data;
    }
}
