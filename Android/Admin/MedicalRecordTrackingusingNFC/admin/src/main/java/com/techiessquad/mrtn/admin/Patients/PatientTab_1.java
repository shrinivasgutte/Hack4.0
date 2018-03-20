package com.techiessquad.mrtn.admin.Patients;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.techiessquad.mrtn.admin.Patients.OnClickPatient.FamilyPatient_MedicalReport_Details;
import com.techiessquad.mrtn.admin.Patients.OnClickPatient.FamilyTab1_PatientDitails;
import com.techiessquad.mrtn.admin.R;
import com.techiessquad.mrtn.admin.State_and_City;

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

/**
 * Created by ajits on 28-12-2017.
 */

public class PatientTab_1 extends Fragment{




    ListView listViewReport;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    public static String myJSON,patientNameSend_server="11223344";
    public static String  MemberID_Temp,m_Last_name_Temp, m_first_name_Temp, m_mid_name_Temp, m_Address_Temp, m_ContactNo_Temp, m_Gender_Temp, m_Email_Temp;

    /*tab 2*/  public static String MemberID[] ,D_Last_name[],D_first_name[],D_mid_name[],dr_Address[],D_ContactNo[],D_Gender[],D_Email[];
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

            listViewReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //  TextView textView = (TextView) v.findViewById(R.id.fname);
                    //  send_ditails= textView.getText().toString();
                    // public static String D_Last_name[],D_first_name[],D_mid_name[],dr_Address[],D_ContactNo[],D_Gender[],D_Email[];

                   // Relation_Type_Temp =  Relation_Type[position];
                    MemberID_Temp = MemberID[position];
                    m_Last_name_Temp =  D_Last_name[position];
                    m_first_name_Temp =  D_first_name[position];
                    m_mid_name_Temp = D_mid_name[position];
                    m_Address_Temp =  dr_Address[position];
                    m_ContactNo_Temp =  D_ContactNo[position];
                    //users_Type_Temp =  users_Type[position];
                    m_Gender_Temp =   D_Gender[position];
                   // m_DOB_Temp =   m_DOB[position];
                   // m_BloodGrp_Temp = m_BloodGrp[position];
                    m_Email_Temp = D_Email[position];

                    FamilyPatient_MedicalReport_Details.familyPatientName=m_first_name_Temp+" "+m_mid_name_Temp+" "+m_Last_name_Temp;


                    Intent s = new Intent(getActivity(),FamilyTab1_PatientDitails.class);
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


                    httppost = new HttpPost(State_and_City.url + "all_patient.php");

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

                    Toast.makeText(getActivity(), "error recive ::"+f, Toast.LENGTH_SHORT).show();
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


                D_Last_name = new String[cnt];
                D_first_name = new String[cnt];
            MemberID = new String[cnt];
            D_mid_name = new String[cnt];
            dr_Address = new String[cnt];
            D_ContactNo = new String[cnt];
            D_Gender = new String[cnt];
            D_Email = new String[cnt];




            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


                    D_Last_name[cnt1] = c.getString("D_Last_name");
                    D_first_name[cnt1] = c.getString("D_first_name");

                D_mid_name[cnt1] = c.getString("D_mid_name");
                dr_Address[cnt1] = c.getString("dr_Address");
                D_ContactNo[cnt1] = c.getString("D_ContactNo");
                D_Gender[cnt1] = c.getString("D_Gender");
                D_Email[cnt1] = c.getString("D_Email");
                MemberID[cnt1] = c.getString("p_id");

                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
          //  setTitle("sfse1");


                layout_all_patient r = new layout_all_patient(getActivity(), D_Last_name, D_first_name,D_mid_name,D_Email,D_ContactNo,MemberID);
                listViewReport.setAdapter(r);



            if(cnt==0) {
                Toast.makeText(getActivity(), "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
