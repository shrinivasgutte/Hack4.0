package com.techiessquad.mrtn.patient;

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
import android.widget.ProgressBar;
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

/**
 * Created by ajits on 28-12-2017.
 */

public class RecentPatient extends Fragment{


    ProgressBar progressBar;

    ListView listViewReport;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    public static String myJSON;
    /*tab 2*/  public static String Relation_Type_Temp, MemberID_Temp, m_Last_name_Temp, m_first_name_Temp, m_mid_name_Temp, m_Address_Temp, m_ContactNo_Temp, users_Type_Temp, m_Gender_Temp, m_DOB_Temp, m_BloodGrp_Temp, m_Email_Temp;
    public static String Relation_Type[],MemberID[],m_Last_name[],m_first_name[],m_mid_name[],m_Address[],m_ContactNo[],users_Type[],m_Gender[],m_DOB[],m_BloodGrp[],m_Email[];
    private ProgressDialog progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.family_tab1, container, false);


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
                   Relation_Type_Temp =  Relation_Type[position];
                    MemberID_Temp = MemberID[position];
                    m_Last_name_Temp =  m_Last_name[position];
                    m_first_name_Temp =  m_first_name[position];
                    m_mid_name_Temp = m_mid_name[position];
                    m_Address_Temp =  m_Address[position];
                    m_ContactNo_Temp =  m_ContactNo[position];
                    users_Type_Temp =  users_Type[position];
                    m_Gender_Temp =   m_Gender[position];
                    m_DOB_Temp =   m_DOB[position];
                    m_BloodGrp_Temp = m_BloodGrp[position];
                    m_Email_Temp = m_Email[position];
                    


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


                    httppost = new HttpPost(State_and_City.url + "family_Accepted.php?p_id=" + State_and_City.patientNameSend_server.replace(" ", "%20"));

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

                Relation_Type = new String[cnt];
                MemberID = new String[cnt];
                m_Last_name = new String[cnt];
                m_first_name = new String[cnt];
                m_mid_name = new String[cnt];
                m_Address = new String[cnt];
                m_ContactNo = new String[cnt];
                users_Type = new String[cnt];
                m_Gender = new String[cnt];
                m_DOB = new String[cnt];
                m_BloodGrp = new String[cnt];

            m_Email = new String[cnt];




            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


                    Relation_Type[cnt1] = c.getString("Relation_Type");
                    MemberID[cnt1] = c.getString("MemberID");
                    m_Last_name[cnt1] = c.getString("m_Last_name");
                    m_first_name[cnt1] = c.getString("m_first_name");
                    m_mid_name[cnt1] = c.getString("m_mid_name");
                    m_Address[cnt1] = c.getString("m_Address");
                    m_ContactNo[cnt1] = c.getString("m_ContactNo");
                    users_Type[cnt1] = c.getString("users_Type");
                    m_Gender[cnt1] = c.getString("m_Gender");
                    m_DOB[cnt1] = c.getString("m_DOB");
                    m_BloodGrp[cnt1] = c.getString("m_BloodGrp");
                m_Email[cnt1] = c.getString("m_Email");


                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
          //  setTitle("sfse1");

            progressBar.setVisibility(View.INVISIBLE);
                layout_acceptedFamily r = new layout_acceptedFamily(getActivity(),m_Last_name,m_first_name,m_mid_name,m_ContactNo,MemberID);
                listViewReport.setAdapter(r);



            if(cnt==0) {
                Toast.makeText(getActivity(), "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
