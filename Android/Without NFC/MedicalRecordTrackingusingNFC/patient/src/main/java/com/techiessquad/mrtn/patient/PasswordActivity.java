package com.techiessquad.mrtn.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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

public class PasswordActivity extends AppCompatActivity {
   private ProgressDialog progress;
    EditText Password;
    Button PasswordB;
    String Username,NFCID,Data,Type,PasswordS;
    Intent i;

    public static String myJSON,ID;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    public  static  int cnt;
    String weburl="http://192.168.0.3/MRTS/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        try {
            progress=new ProgressDialog(this);
            Password = (EditText) findViewById(R.id.Password);
            PasswordB = (Button) findViewById(R.id.PasswordB);

            PasswordB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PasswordS=Password.getText().toString();
                    Toast.makeText(PasswordActivity.this, "uname : "+Username+" pas : "+PasswordS+" nfc : "+NFCID, Toast.LENGTH_SHORT).show();
                    // Toast.makeText(PasswordActivity.this, "uname : "+Username+" pas : "+Password+" nfc : "+NFCID, Toast.LENGTH_SHORT).show();

                    if(PasswordS.equals(""))
                        Password.setError("Enter Password");
                    else {
                        getData();
                        progress.setMessage("Verifing...");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progress.setIndeterminate(true);
                        progress.setProgress(0);
                        progress.show();
                    }
                }
            });

        }catch (Exception s){
            Toast.makeText(this, "p "+s, Toast.LENGTH_SHORT).show();
        }
    }

    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost= new HttpPost(weburl+"login.php");

                try {


                    if(Username != null){
                        httppost= new HttpPost(State_and_City.url+"login.php?Username="+Username+"&Password="+PasswordS);
                        //Toast.makeText(PasswordActivity.this, "1 "+url.Url+"login.php?Username="+Username+"&Password="+PasswordS, Toast.LENGTH_LONG).show();
                    }else  if(NFCID != null){
                        httppost= new HttpPost(State_and_City.url+"login.php?NFCID="+NFCID+"&Password="+PasswordS);
                    }
                }catch (Exception s){
                    Toast.makeText(PasswordActivity.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(PasswordActivity.this, "get 1 "+s, Toast.LENGTH_SHORT).show();
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
                progress.dismiss();
                try{
                   

                  //  Toast.makeText(PasswordActivity.this, result, Toast.LENGTH_SHORT).show();
                    if(result.contains("fail"))
                    {
                        Password.setError("Please Enter Correct Password");
                        Toast.makeText(PasswordActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                      //  progress.dismiss();
                        myJSON=result;
                     //   Toast.makeText(PasswordActivity.this, "Data : "+result, Toast.LENGTH_SHORT).show();
                           showList();
                    }
                }catch (Exception s){
                  //  progress.dismiss();
                    Toast.makeText(PasswordActivity.this, "GO Online :Login:", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            }
//static String FirstName,MiddleName,LastName,Email,patientNameSend_server,User_State,User_City;

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                ID=c.getString("ID");
                State_and_City.FirstName=c.getString("FirstName");
                State_and_City.MiddleName=c.getString("MiddleName");
                State_and_City.LastName=c.getString("LastName");
                State_and_City.Email=c.getString("Email");
                State_and_City.patientNameSend_server=c.getString("ID");
                State_and_City.User_State=c.getString("State");
                State_and_City.User_City=c.getString("City");
                Type=c.getString("Type");
                 }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (Type)
        {
            case "Doctor":

                Toast.makeText(this, "pa Doctor", Toast.LENGTH_SHORT).show();
                break;
            case  "Admin":
                Toast.makeText(this, "pa Admin", Toast.LENGTH_SHORT).show();

                //   i = new Intent(getApplicationContext(),Admin_Activity.class);
              //  startActivity(i);
              //  finish();
                break;
            case "Patient":
                i = new Intent(getApplicationContext(),Home.class);
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Type=null;
        NFCID = LoginActivity.IDS;
        Username =url.UsernameS;
        Data = LoginActivity.Data;

    }
}
