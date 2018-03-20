package com.techiessquad.mrtn.doctor;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.techiessquad.mrtn.doctor.PatientProfileDitails.HomeTab_1;
import com.techiessquad.mrtn.doctor.PatientProfileDitails.HomeTab_2;
import com.techiessquad.mrtn.doctor.PatientProfileDitails.HomeTab_3;
import com.techiessquad.mrtn.doctor.PatientProfileDitails.Patient_Offline_Data;
import com.techiessquad.mrtn.doctor.Request_Send.Request_Send_Form;

import java.io.InputStream;

public class PatientProfile extends AppCompatActivity {

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    Context context=this;
    public static CircularImageView image;
    public static int position;
    public static TextView tvname,tvdob,tvbg,tvemail;


    CollapsingToolbarLayout toolbar_layout;
    AppBarLayout appBarLayout;
    RelativeLayout rl;
FloatingActionButton fab_caepaper,fab_report,fab_medical,fab_update;
    Fragment CasePaperPatientProfile,ReportPatientProfile,MedicinePatientProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        try {
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setOffscreenPageLimit(4);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            setTitle("");
            profile_init();
            final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            Toast.makeText(this, "In PP", Toast.LENGTH_SHORT).show();
            toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
            fab_caepaper=(FloatingActionButton)findViewById(R.id.fab_casepaper);
            fab_report=(FloatingActionButton)findViewById(R.id.fab_report);
            fab_medical=(FloatingActionButton)findViewById(R.id.fab_medical);
            fab_update=(FloatingActionButton)findViewById(R.id.fab_update);


            appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                @Override
                public void onStateChanged(AppBarLayout appBarLayout, State state) {
                    if (state == State.EXPANDED) {
                        rl.setVisibility(View.VISIBLE);
                        image.setVisibility(View.VISIBLE);
                        toolbar_layout.setTitleEnabled(false);
                        //setTitle(" ");
//                    hello.setDisplayShowTitleEnabled(false);
                        //requestWindowFeature(Window.FEATURE_NO_TITLE);
                    } else if (state == State.COLLAPSED) {
                        rl.setVisibility(View.INVISIBLE);
                        image.setVisibility(View.INVISIBLE);
                        toolbar_layout.setTitleEnabled(true);
                        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
                        // hello.setDisplayShowTitleEnabled(true);
                        //setTitle(PD.Name);
                    }
                }
            });

            if (Patient.NFCID != null) {
              //  PD = new NFCPatientProfile_get(Patient.NFCID, context);
            } else if (Patient.Username != null) {
            //   PD = new NFCPatientProfile_get(context, Patient.Username);
            } else {
            //    PD = new NFCPatientProfile_get(1, context);
            }
            Toast.makeText(context, "DOne", Toast.LENGTH_SHORT).show();


       /* ReportPatientProfile= new ReportPatientProfile();
        MedicinePatientProfile=new MedicinePatientProfile();*/





            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    // Toast.makeText(getApplicationContext(),"Pos onTabSelected: "+tab.getPosition(),Toast.LENGTH_SHORT).show();
                    if (tab.getPosition() == 0) {
                        //mOptionsMenu.getItem(1).setVisible(false);
                        State_and_City.STR_reportsUploadFLG="Casepaper";
                      //  fab.setImageResource(R.mipmap.ic_casepaper);
                    } else if (tab.getPosition() == 1) {
                        State_and_City.STR_reportsUploadFLG="Reports";
                      //  fab.setImageResource(R.mipmap.ic_report);
                    }else if (tab.getPosition() == 2) {
                        State_and_City.STR_reportsUploadFLG="Medical";
                      //  fab.setImageResource(R.mipmap.ic_medical);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            //casepaper
            fab_caepaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    State_and_City.casepaperFLG = 0;
                    Intent   s = new Intent(getApplicationContext(), UploadCasePaper.class);
                    startActivity(s);
                }
            });
            //report
            fab_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    State_and_City.casepaperFLG = 0;

                        Intent  s1 = new Intent(getApplicationContext(), UploadReport.class);
                        startActivity(s1);
                }
            });
            //medical
            fab_medical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    State_and_City.casepaperFLG = 0;
                    Intent   s2= new Intent(getApplicationContext(), UploadMedicine.class);
                    startActivity(s2);
                }
            });
            //update
            fab_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    State_and_City.casepaperFLG = 0;
                    Intent i = new Intent(getApplicationContext(),Patient_Offline_Data.class);
                    startActivity(i);

                }
            });


        }catch (Exception s){
            Toast.makeText(context, "D onc "+s, Toast.LENGTH_SHORT).show();
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    HomeTab_1 tab1 = new HomeTab_1();
                    return tab1;
                case 1:
                    HomeTab_2 tab2 = new HomeTab_2();
                    return tab2;
                case 2:
                    HomeTab_3 tab3 = new HomeTab_3();
                    return tab3;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        CircularImageView bmImage;

        public DownloadImageTask(CircularImageView bmImage) {
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
            bmImage.setImageBitmap(result);
            //Toast.makeText(demo.this, "Error="+res, Toast.LENGTH_SHORT).show();
        }
    }




    public void profile_init(){




        image = (CircularImageView) findViewById(R.id.image);
        tvname = (TextView) findViewById(R.id.name);
        tvbg = (TextView) findViewById(R.id.bg);
        tvdob = (TextView) findViewById(R.id.dob);
        tvemail = (TextView) findViewById(R.id.email);
        rl = (RelativeLayout) findViewById(R.id.layout);
     //   fab = (FloatingActionButton) findViewById(R.id.fab);



        PatientProfile.tvemail.setText(Patient.Email);
        PatientProfile.tvdob.setText(Patient.DOB);
        PatientProfile.tvbg.setText(Patient.BloodGRP);
        PatientProfile.tvname.setText(Patient.Name);

        new DownloadImageTask((CircularImageView) findViewById(R.id.image))
                .execute(Strings.url+"User/"+State_and_City.ScannedPatientID+"/" +State_and_City.ScannedPatientID+".jpg");
    }

}

