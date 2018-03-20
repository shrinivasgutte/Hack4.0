package com.techiessquad.mrtn.patient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorTab1_recentDitails extends AppCompatActivity {


    TextView doctor_name,doctor_id, speciality,casep_date,quali,workinghrs,doctor_phno,doctor_email,gender,doctor_blood,hospital_name,contactno,emergencyno,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_tab1_recent_ditails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            setTitle("Meeted Doctor");
            doctor_name =(TextView)findViewById(R.id.doctor_name);
            doctor_id =(TextView)findViewById(R.id.doctor_id);
            speciality =(TextView)findViewById(R.id.speciality);
            casep_date =(TextView)findViewById(R.id.casep_date);
            quali =(TextView)findViewById(R.id.quali);
            doctor_phno =(TextView)findViewById(R.id.doctor_phno);
            doctor_email =(TextView)findViewById(R.id.doctor_email);
            gender =(TextView)findViewById(R.id.gender);

            doctor_blood =(TextView)findViewById(R.id.doctor_blood);
            hospital_name =(TextView)findViewById(R.id.hospital_name);
            address =(TextView)findViewById(R.id.address);
            contactno =(TextView)findViewById(R.id.contactno);
            emergencyno =(TextView)findViewById(R.id.emergencyno);
            workinghrs =(TextView)findViewById(R.id.workinghrs);

            doctor_name.setText(DoctorTab_1.D_first_name_Temp+" "+DoctorTab_1.D_mid_name_Temp+" "+DoctorTab_1.D_Last_name_Temp);
            doctor_id.setText("ID : "+DoctorTab_1.DoctorID_Temp);
            speciality.setText(DoctorTab_1.Speciality_Temp);
            casep_date.setText(DoctorTab_1.casep_Date_Temp);
            quali.setText(DoctorTab_1.Qualification_Temp);
            doctor_phno.setText(DoctorTab_1.D_ContactNo_Temp);
            doctor_email.setText(DoctorTab_1.D_Email_Temp);
            gender.setText(DoctorTab_1.D_Gender_Temp);
            hospital_name.setText(DoctorTab_1.HospitalName_Temp);
            address.setText(DoctorTab_1.H_Address_Temp);
            contactno.setText(DoctorTab_1.H_ContactNo_Temp);
            emergencyno.setText(DoctorTab_1.H_EmergencyNo_Temp);
            workinghrs.setText(DoctorTab_1.H_WorkingHours_Temp);

        }catch (Exception s){
            Toast.makeText(this, "listClick 2: "+s, Toast.LENGTH_SHORT).show();
        }


    }
   
}
