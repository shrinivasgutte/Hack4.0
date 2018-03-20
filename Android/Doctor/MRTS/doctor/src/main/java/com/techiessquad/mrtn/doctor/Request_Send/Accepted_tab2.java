package com.techiessquad.mrtn.doctor.Request_Send;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.techiessquad.mrtn.doctor.R;
import com.techiessquad.mrtn.doctor.State_and_City;
import com.techiessquad.mrtn.doctor.Strings;

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
 * Created by Hrishikesh on 16-12-2017.
 */

public class Accepted_tab2 extends Fragment {

    ListView lv;
    public Accepted_tab2() {};
    ProgressBar progressBar;
    private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;

    public static String myJSON,patientNameSend_server="11223344";
    /*tab 1*/  public static String RequestID[],PatientID[],DoctorID[],Description[],Accept[],ColumnName[],ColumnValue[],Type[];
      private ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.accepted_tab2, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv = (ListView)view.findViewById(R.id.accepted_listview);
        progressBar= (ProgressBar)view.findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.VISIBLE);

        //Toast.makeText(getActivity(), "in onViewCreated", Toast.LENGTH_SHORT).show();
        getData();
    }




    //####################################################################################### get data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

                    //httppost = new HttpPost(State_and_City.url + "pending.php?p_id=" + patientNameSend_server.replace(" ", "%20"));
                httppost = new HttpPost(Strings.url + "Doctor/accepted.php?p_id="+ State_and_City.staticDR_ID);

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
                 //   Toast.makeText(getActivity(), "dd", Toast.LENGTH_SHORT).show();
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
                   // Toast.makeText(getActivity(), "MyJson: "+myJSON.toString(), Toast.LENGTH_SHORT).show();

                    myJSON=result;
                    showList();

                }catch (Exception f){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Go online :2:", Toast.LENGTH_SHORT).show();
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

                PatientID = new String[cnt];
                RequestID = new String[cnt];
                Type = new String[cnt];
                DoctorID = new String[cnt];
                Description = new String[cnt];
                Accept = new String[cnt];
                ColumnName = new String[cnt];
                ColumnValue = new String[cnt];



            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //RequestID[cnt1]=c.getString("RequestID");

                    PatientID[cnt1] = c.getString("PatientID");
                    RequestID[cnt1] = c.getString("RequestID");
                    Type[cnt1] = c.getString("Type");
                    DoctorID[cnt1] = c.getString("DoctorID");
                    Description[cnt1] = c.getString("Description");
                    Accept[cnt1] = c.getString("Accept");
                    ColumnName[cnt1] = c.getString("ColumnName");
                    ColumnValue[cnt1] = c.getString("ColumnValue");

                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
          //  setTitle("sfse1");
            progressBar.setVisibility(View.INVISIBLE);

                layout_accepted a = new layout_accepted(getActivity(), RequestID, DoctorID, Description, Accept, ColumnName, ColumnValue, Type);
                lv.setAdapter(a);


            if(cnt==0) {
                Toast.makeText(getActivity(), "Nothing to show in Accepted", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
