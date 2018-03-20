package com.techiessquad.mrtn.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

/**
 * Created by ajits on 28-12-2017.
 */

public class DoctorTab_1 extends Fragment{


    ProgressBar progressBar;


    ListView listViewReport;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    public static String myJSON;
    /*tab 2*/  public static String DoctorID_Temp, Qualification_Temp, Speciality_Temp, HospitalID_Temp, HospitalName_Temp, H_Address_Temp, H_ContactNo_Temp, H_EmergencyNo_Temp, H_WorkingHours_Temp, D_Last_name_Temp, D_first_name_Temp, D_mid_name_Temp, casep_Date_Temp, D_ContactNo_Temp, D_Gender_Temp, D_Email_Temp;
    public static String DoctorID[],Qualification[],Speciality[],HospitalID[],HospitalName[],H_Address[],H_ContactNo[],H_EmergencyNo[],H_WorkingHours[],D_Last_name[],D_first_name[],D_mid_name[],casep_Date[],D_ContactNo[],D_Gender[],D_Email[];
    private ProgressDialog progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doctor_tab1, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  Doctor.mOptionsMenu.getItem(1).setVisible(false);
        listViewReport = (ListView)view.findViewById(R.id.listViewReport);
         getData();
        try {
            progressBar= (ProgressBar)view.findViewById(R.id.progressBar_cyclic);
            progressBar.setVisibility(View.VISIBLE);
            listViewReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //  TextView textView = (TextView) v.findViewById(R.id.fname);
                    //  send_ditails= textView.getText().toString();
                    
                    DoctorID_Temp =  DoctorID[position];
                    Qualification_Temp = Qualification[position];
                    Speciality_Temp =  Speciality[position];
                    HospitalID_Temp =  HospitalID[position];
                    HospitalName_Temp = HospitalName[position];
                    H_Address_Temp =  H_Address[position];
                    H_ContactNo_Temp =  H_ContactNo[position];
                    H_EmergencyNo_Temp =  H_EmergencyNo[position];
                    H_WorkingHours_Temp =   H_WorkingHours[position];
                    D_Last_name_Temp =   D_Last_name[position];
                    D_first_name_Temp = D_first_name[position];
                    D_mid_name_Temp = D_mid_name[position];
                    casep_Date_Temp = casep_Date[position];
                    D_ContactNo_Temp = D_ContactNo[position];

                    D_Gender_Temp = D_Gender[position];
                    D_Email_Temp = D_Email[position];



                    Intent s = new Intent(getActivity(),DoctorTab1_recentDitails.class);
                    startActivity(s);
                }
            });
        }catch (Exception s){
            Toast.makeText(getActivity(), "listClick : "+s, Toast.LENGTH_SHORT).show();
        }
    }




    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());


                    httppost = new HttpPost(State_and_City.url + "recentDoctor.php?p_id=" + State_and_City.patientNameSend_server.replace(" ", "%20"));

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
                    Toast.makeText(getActivity(), "dd", Toast.LENGTH_SHORT).show();
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


                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Network :1:\n\n", Toast.LENGTH_SHORT).show();

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

                DoctorID = new String[cnt];
                Qualification = new String[cnt];
                Speciality = new String[cnt];
                HospitalID = new String[cnt];
                HospitalName = new String[cnt];
                H_Address = new String[cnt];
                H_ContactNo = new String[cnt];
                H_EmergencyNo = new String[cnt];
                H_WorkingHours = new String[cnt];
                D_Last_name = new String[cnt];
                D_first_name = new String[cnt];

            D_mid_name = new String[cnt];
            casep_Date = new String[cnt];
            D_ContactNo = new String[cnt];
            D_Gender = new String[cnt];
            D_Email = new String[cnt];




            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


                    DoctorID[cnt1] = c.getString("DoctorID");
                    Qualification[cnt1] = c.getString("Qualification");
                    Speciality[cnt1] = c.getString("Speciality");
                    HospitalID[cnt1] = c.getString("HospitalID");
                    HospitalName[cnt1] = c.getString("HospitalName");
                    H_Address[cnt1] = c.getString("H_Address");
                    H_ContactNo[cnt1] = c.getString("H_ContactNo");
                    H_EmergencyNo[cnt1] = c.getString("H_EmergencyNo");
                    H_WorkingHours[cnt1] = c.getString("H_WorkingHours");
                    D_Last_name[cnt1] = c.getString("D_Last_name");
                    D_first_name[cnt1] = c.getString("D_first_name");

                D_mid_name[cnt1] = c.getString("D_mid_name");
                casep_Date[cnt1] = c.getString("casep_Date");
                D_ContactNo[cnt1] = c.getString("D_ContactNo");
                D_Gender[cnt1] = c.getString("D_Gender");
                D_Email[cnt1] = c.getString("D_Email");

                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
          //  setTitle("sfse1");

            progressBar.setVisibility(View.INVISIBLE);

                layout_recent_doctor r = new layout_recent_doctor(getActivity(), D_Last_name, D_first_name,D_mid_name,Qualification,casep_Date,HospitalName,DoctorID);
                listViewReport.setAdapter(r);



            if(cnt==0) {
                Toast.makeText(getActivity(), "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
