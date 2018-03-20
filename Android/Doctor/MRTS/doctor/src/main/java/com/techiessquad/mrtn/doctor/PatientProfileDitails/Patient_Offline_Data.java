package com.techiessquad.mrtn.doctor.PatientProfileDitails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.techiessquad.mrtn.doctor.R;
import com.techiessquad.mrtn.doctor.State_and_City;

public class Patient_Offline_Data extends AppCompatActivity {

    TextView p_id,p_name,c_no1,c_no2,bloodgroup,age,gender,alergy,body,diseases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__offline__data);
        try{
                    p_id=(TextView)findViewById(R.id.p_id);
                    p_name=(TextView)findViewById(R.id.p_name);
                    c_no1=(TextView)findViewById(R.id.c_no1);
                    c_no2=(TextView)findViewById(R.id.c_no2);
                    bloodgroup=(TextView)findViewById(R.id.bloodgroup);
                    age=(TextView)findViewById(R.id.age);
                    gender=(TextView)findViewById(R.id.gender);
                    alergy=(TextView)findViewById(R.id.allergies);
                    body=(TextView)findViewById(R.id.body);
                            diseases=(TextView)findViewById(R.id.diseases);


            String[] words= State_and_City.OfflineData.split(":");
            p_id.setText(words[0]);
            p_name.setText(words[1]);
                    c_no1.setText(words[2]);
                    c_no2.setText(words[3]);
                    bloodgroup.setText(words[4]);
                    age.setText(words[5]);
                    gender.setText(words[6]);
                    alergy.setText(words[7]);
                    body.setText(words[8]);
                    diseases.setText(words[9]);

        }catch (Exception s){
            Toast.makeText(this, "onc "+s, Toast.LENGTH_SHORT).show();
        }
    }
}
