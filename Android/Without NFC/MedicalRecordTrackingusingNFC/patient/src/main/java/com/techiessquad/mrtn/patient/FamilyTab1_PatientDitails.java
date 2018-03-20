package com.techiessquad.mrtn.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class FamilyTab1_PatientDitails extends AppCompatActivity {

    static String familyPatientid,familyPatientName;
    TextView relation,member_name,address,member_phno,gender,member_email,member_blood,dob,users_type;
    Button seemore_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_tab1__patient_ditails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            setTitle("Meeted Doctor");
            seemore_btn =(Button)findViewById(R.id.seemore_btn);
            relation =(TextView)findViewById(R.id.relation);
            member_name =(TextView)findViewById(R.id.member_name);
            member_phno =(TextView)findViewById(R.id.member_phno);
            member_email =(TextView)findViewById(R.id.member_email);
            member_blood =(TextView)findViewById(R.id.member_blood);
            dob =(TextView)findViewById(R.id.dob);
            gender =(TextView)findViewById(R.id.gender);
            address =(TextView)findViewById(R.id.address);
            users_type =(TextView)findViewById(R.id.users_type);

            member_name.setText(FamilyTab_1.m_first_name_Temp+" "+FamilyTab_1.m_mid_name_Temp+" "+FamilyTab_1.m_Last_name_Temp);
            users_type.setText(FamilyTab_1.users_Type_Temp);
            relation.setText(FamilyTab_1.Relation_Type_Temp);
            member_phno.setText(FamilyTab_1.m_ContactNo_Temp);
            member_email.setText(FamilyTab_1.m_Email_Temp);
            member_blood.setText(FamilyTab_1.m_BloodGrp_Temp);
            dob.setText(FamilyTab_1.m_DOB_Temp);
            gender.setText(FamilyTab_1.m_Gender_Temp);
            address.setText(FamilyTab_1.m_Address_Temp);

            new DownloadImageTask((CircleImageView) findViewById(R.id.profile_image))
                    .execute(State_and_City.url_patient_report+FamilyTab_1.MemberID_Temp+"/"+FamilyTab_1.MemberID_Temp);

            seemore_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    familyPatientid=FamilyTab_1.MemberID_Temp;
                    familyPatientName=FamilyTab_1.m_first_name_Temp+" "+FamilyTab_1.m_mid_name_Temp+" "+FamilyTab_1.m_Last_name_Temp;
                    State_and_City.userFamilyPatientFLG=1;
                    Toast.makeText(FamilyTab1_PatientDitails.this, "n "+familyPatientName, Toast.LENGTH_SHORT).show();
                    Intent f = new Intent(getApplicationContext(),FamilyPatient_MedicalReoprt_Ditails.class);
                    startActivity(f);
                }
            });
        }catch (Exception s){
            Toast.makeText(this, "listClick 2: "+s, Toast.LENGTH_SHORT).show();
        }


    }
    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {

//loop and download image and put it in this list
            //  imagesList.add(result);
            bmImage.setImageBitmap(result);
            //Toast.makeText(demo.this, "Error="+res, Toast.LENGTH_SHORT).show();
        }
    }

}
