package com.techiessquad.mrtn.doctor.Request_Send;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.techiessquad.mrtn.doctor.Patient;
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
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Request_Send_Form extends AppCompatActivity {

    CircleImageView profile_image;
    TextView p_name,p_id,old_value;
    EditText desc,new_value;
    private Spinner TableName,ColumnNmae;
    Button update;
    int spinnerFLG=0;
    String[] Str_ColumnNames;
    public    List<String> column_name;

    JSONArray peoples = null;
    public static int cnt, cnt1;
    String selectTable,selectColumn,WhereCol_Name,currnetInfo;

    public static String myJSON;
    private static final String TAG_RESULTS="result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request__send__form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            profile_image = (CircleImageView)findViewById(R.id.profile_image);
            p_name=(TextView)findViewById(R.id.p_name);
            p_id=(TextView)findViewById(R.id.p_id);
            old_value=(TextView)findViewById(R.id.old_value);
            desc=(EditText)findViewById(R.id.desc);
            new_value=(EditText)findViewById(R.id.new_value);
            TableName=(Spinner)findViewById(R.id.a);
            ColumnNmae=(Spinner)findViewById(R.id.b);
            update=(Button)findViewById(R.id.btn_update);
            column_name = new ArrayList<String>();
            column_name.add("-- Select column --");
            p_name.setText(Patient.Name);
            p_id.setText(Patient.ID);

           new State_and_City();

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Request_Send_Form.this,
                    android.R.layout.simple_spinner_item, State_and_City.table_name);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            TableName.setAdapter(dataAdapter);



            TableName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectTable = (String) parent.getItemAtPosition(position);

                    if(selectTable.equals("-- Select Table --")){

                    } else  if(!selectTable.equals("-- Select Table --")) {
                        spinnerFLG=1;
                        if(selectTable.equals("doctor"))
                            WhereCol_Name="DoctorID";
                        else
                            WhereCol_Name="ID";
                        column_name.clear();
                        column_name.add("-- Select column --");
                        getData();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            ColumnNmae.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectColumn = (String) parent.getItemAtPosition(position);

                    if(selectTable.equals("-- Select column --")){

                    } else  if(!selectColumn.equals("-- Select column --")) {
                        spinnerFLG=2;
                        getData();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception s){
            Toast.makeText(this, "update r"+s, Toast.LENGTH_SHORT).show();
        }
    }

    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

                if(spinnerFLG==1)
                httppost = new HttpPost(Strings.url + "doctor/column_list.php?t_name="+selectTable);
                else if(spinnerFLG==2)
                httppost = new HttpPost(Strings.url + "doctor/select_current_info.php?Col_name="+selectColumn+"&t_name="+selectTable+"&W_colName="+WhereCol_Name+"&p_id="+Patient.ID);

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
                   // Toast.makeText(Request_Send_Form.this, "myJ "+myJSON, Toast.LENGTH_SHORT).show();
                    showList();

                }catch (Exception f){

                    Toast.makeText(getApplicationContext(), "$ Check Network Connection" +
                            "", Toast.LENGTH_SHORT).show();
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

            Str_ColumnNames = new String[cnt];






            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");

                if(spinnerFLG==1) {
                    Str_ColumnNames[cnt1] = c.getString("Str_ColumnNames");
                    column_name.add(Str_ColumnNames[cnt1]);
                }else if(spinnerFLG==2){
                    currnetInfo= c.getString("Str_ColumnNames");
                }


                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
            //  setTitle("sfse1");

            // state = findViewById(R.id.a);


            if(spinnerFLG==1) {

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, column_name);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                ColumnNmae.setAdapter(dataAdapter);

            }else if(spinnerFLG==2){
              old_value.setText(currnetInfo);
            }





            //  layout_all_doctor r = new layout_all_doctor(getActivity(), D_Last_name, D_first_name,D_mid_name,Qualification,Speciality,dr_Address,HospitalName,DoctorID);
            // listViewReport.setAdapter(r);



            if(cnt==0) {
                Toast.makeText(getApplicationContext(), "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

