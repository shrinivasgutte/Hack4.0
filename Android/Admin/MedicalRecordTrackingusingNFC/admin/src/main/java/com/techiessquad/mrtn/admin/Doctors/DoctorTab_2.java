package com.techiessquad.mrtn.admin.Doctors;

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

public class DoctorTab_2 extends Fragment{


    /*tab 2*/  public static String DoctorID_Temp, Qualification_Temp, Speciality_Temp, HospitalID_Temp, HospitalName_Temp, H_Address_Temp, H_ContactNo_Temp, H_EmergencyNo_Temp, H_WorkingHours_Temp, D_Last_name_Temp, D_first_name_Temp, D_mid_name_Temp, casep_Date_Temp, D_ContactNo_Temp, D_Gender_Temp, D_Email_Temp;


    static ListView listViewReport;
      private ProgressDialog progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.doctor_tab2, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Doctor.mOptionsMenu.getItem(1).setVisible(true);
        try {
            listViewReport = (ListView) view.findViewById(R.id.listViewReport);

         //   getData();
            try {

                listViewReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        //  TextView textView = (TextView) v.findViewById(R.id.fname);
                        //  send_ditails= textView.getText().toString();

                        DoctorID_Temp =  Doctor.DoctorID[position];
                        Qualification_Temp =  Doctor.Qualification[position];
                        Speciality_Temp =  Doctor. Speciality[position];
                        HospitalID_Temp =  Doctor. HospitalID[position];
                        HospitalName_Temp =  Doctor.HospitalName[position];
                        H_Address_Temp =  Doctor. H_Address[position];
                        H_ContactNo_Temp =  Doctor. H_ContactNo[position];
                        H_EmergencyNo_Temp =  Doctor. H_EmergencyNo[position];
                        H_WorkingHours_Temp =  Doctor.  H_WorkingHours[position];
                        D_Last_name_Temp =  Doctor.  D_Last_name[position];
                        D_first_name_Temp =  Doctor.D_first_name[position];
                        D_mid_name_Temp =  Doctor.D_mid_name[position];
                        D_ContactNo_Temp =  Doctor.D_ContactNo[position];

                        D_Gender_Temp =  Doctor.D_Gender[position];
                        D_Email_Temp =  Doctor.D_Email[position];



                        Intent s = new Intent(getActivity(),DoctorTab1_recentDitails.class);
                        startActivity(s);
                    }
                });
            }catch (Exception s){
                Toast.makeText(getActivity(), "listClick : "+s, Toast.LENGTH_SHORT).show();
            }

        }catch (Exception s){
            Toast.makeText(getActivity(), "drtab : "+s, Toast.LENGTH_SHORT).show();
        }
    }



}
