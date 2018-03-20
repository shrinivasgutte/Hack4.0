package com.techiessquad.mrtn.patient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientProfile extends AppCompatActivity {


    CircleImageView profile_image;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    final Context context = this;
   
    Menu mOptionsMenu;
    int snavkbarFLG=0;
    public static String myJSON;
    
    public static String patientID[],LastName[],FirstName[],MiddleName[],Address[],Gender[],DOB[],BloodGrp[],RegTimestamp[],Email[],AadharNo[],State[],City[],Allergies[],ContactNo[];

    TextView p_name,p_id,contact_no,email,dob,address,aadhar_no, gender, regdate, blood, city, state, allergies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            setTitle("Emergency");
            profile_image =(CircleImageView)findViewById(R.id.profile_image);


            p_name =(TextView)findViewById(R.id.p_name);
            p_id =(TextView)findViewById(R.id.p_id);
            contact_no =(TextView)findViewById(R.id.contact_no);
            email =(TextView)findViewById(R.id.email);
            dob =(TextView)findViewById(R.id.dob);
            address =(TextView)findViewById(R.id.address);

            aadhar_no =(TextView)findViewById(R.id.aadhar_no);
                    gender =(TextView)findViewById(R.id.gender);
                    regdate =(TextView)findViewById(R.id.regdate);
                    blood =(TextView)findViewById(R.id.blood);
                    city =(TextView)findViewById(R.id.city);
                    state =(TextView)findViewById(R.id.state);
                    allergies =(TextView)findViewById(R.id.allergies);
getData();
         /*   p_name.setText(Home.PatientName_Temp);
            p_id.setText(Home.PatientContactNo);
            contact_no.setText(Home.contact_no_Temp);
            email.setText(Home.Date_Temp);
            dob.setText(Home.SenderName_Temp);
            address.setText(Home.SenderNo_Temp);
*/


        }catch (Exception s){
            Toast.makeText(this, "listClick 2: "+s, Toast.LENGTH_SHORT).show();
        }

    }

    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());


                httppost = new HttpPost(State_and_City.url + "patientProfile.php?p_id=" + State_and_City.patientNameSend_server.replace(" ", "%20"));

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
                    showList();

                }catch (Exception f){

                    Toast.makeText(getApplicationContext(), "error recive ::"+f, Toast.LENGTH_SHORT).show();
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
            cnt1=0;


            for(int i=0;i<peoples.length();i++){
                cnt++;
            }

            patientID = new String[cnt];
            LastName = new String[cnt];
            FirstName = new String[cnt];
            MiddleName = new String[cnt];
            Address = new String[cnt];
            Gender = new String[cnt];
            DOB = new String[cnt];
            BloodGrp = new String[cnt];
            RegTimestamp = new String[cnt];
            Email = new String[cnt];
            AadharNo = new String[cnt];

            State = new String[cnt];
            City = new String[cnt];
            Allergies = new String[cnt];
            ContactNo = new String[cnt];


            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


                patientID[cnt1] = c.getString("patientID");
                LastName[cnt1] = c.getString("LastName");
                FirstName[cnt1] = c.getString("FirstName");
                MiddleName[cnt1] = c.getString("MiddleName");
                Address[cnt1] = c.getString("Address");
                Gender[cnt1] = c.getString("Gender");
                DOB[cnt1] = c.getString("DOB");
                BloodGrp[cnt1] = c.getString("BloodGrp");
                RegTimestamp[cnt1] = c.getString("RegTimestamp");
                Email[cnt1] = c.getString("Email");
                AadharNo[cnt1] = c.getString("AadharNo");
                State[cnt1] = c.getString("State");
                City[cnt1] = c.getString("City");
                Allergies[cnt1] = c.getString("Allergies");
                ContactNo[cnt1] = c.getString("ContactNo");
                cnt1++;
            }
            p_name.setText(FirstName[0]+" "+MiddleName[0]+" "+ LastName[0]);
            p_id.setText(patientID[0]);
            contact_no.setText(ContactNo[0]);
            email .setText(Email[0]);
            dob .setText(DOB[0]);
            address .setText(Address[0]);

            aadhar_no.setText(AadharNo[0]);
            gender.setText(Gender[0]);
            regdate.setText(RegTimestamp[0]);
            blood.setText(BloodGrp[0]);
            city.setText(City[0]);
            state.setText(State[0]);
            allergies .setText(Allergies[0]);


            if(cnt==0) {
                Toast.makeText(getApplicationContext(), "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

