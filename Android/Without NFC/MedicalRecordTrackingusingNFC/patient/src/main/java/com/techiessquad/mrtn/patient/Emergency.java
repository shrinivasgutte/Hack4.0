package com.techiessquad.mrtn.patient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Emergency extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    LinearLayout profile_clicked;
    CircleImageView profile_image;
    LinearLayout layouts;
    TextView p_name,p_no,location,time,helper_name,helper_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            setTitle("Emergency");
            profile_image =(CircleImageView)findViewById(R.id.profile_image);

            layouts = (LinearLayout)findViewById(R.id.layouts);
            p_name =(TextView)findViewById(R.id.p_name);
            p_no =(TextView)findViewById(R.id.p_no);
            location =(TextView)findViewById(R.id.location);
            time =(TextView)findViewById(R.id.time);
            helper_name =(TextView)findViewById(R.id.helper_name);
            helper_no =(TextView)findViewById(R.id.helper_no);
           // PatientName_Temp,PatientContactNo,Location_Temp,Date_Temp,SenderName_Temp,SenderNo_Temp;
            p_name.setText(Home.PatientName_Temp);
            p_no.setText(Home.PatientContactNo);
            location.setText(Home.Location_Temp);
            time.setText(Home.Date_Temp);
            helper_name.setText(Home.SenderName_Temp);
            helper_no.setText(Home.SenderNo_Temp);



            if(State_and_City.emergncyFlg!=2){
                layouts.setVisibility(View.INVISIBLE);
            }else {
                layouts.setVisibility(View.VISIBLE);
                State_and_City.alertNotiStopFLG = 1;
            }
        }catch (Exception s){
            Toast.makeText(this, "listClick 2: "+s, Toast.LENGTH_SHORT).show();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         //    Intent i = new Intent(getApplicationContext(),Emergency_Send.class);
         //    startActivity(i);
                Toast.makeText(Emergency.this, "Feature is not available!!", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        profile_clicked = (LinearLayout)hView.findViewById(R.id.profile_clicked) ;
        navigationView.setNavigationItemSelectedListener(this);

        TextView name = (TextView)hView.findViewById(R.id.name);
        TextView email = (TextView)hView.findViewById(R.id.email);
        name.setText(State_and_City.FirstName+" "+State_and_City.MiddleName+" "+State_and_City.LastName);
        email.setText(State_and_City.Email);
        new DownloadImageTask((ImageView) hView.findViewById(R.id.profile_image))
                .execute(State_and_City.url+"Images/"+State_and_City.patientNameSend_server + ".jpg");
        profile_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PatientProfile.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.emergency, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent f =new Intent(getApplicationContext(),Home.class);
            startActivity(f);
        } else if (id == R.id.nav_doctor) {
            Intent d =new Intent(getApplicationContext(),Doctor.class);
            startActivity(d);

        } else if (id == R.id.nav_labs) {
            Intent l =new Intent(getApplicationContext(),Labs.class);
            startActivity(l);

        } else if (id == R.id.nav_medicals) {
            Intent ds =new Intent(getApplicationContext(),Medical.class);
            startActivity(ds);
        } else if (id == R.id.nav_hospitals) {
            Intent ds =new Intent(getApplicationContext(),Hospitals.class);
            startActivity(ds);
        } else if (id == R.id.nav_family_sharing) {
            Intent f =new Intent(getApplicationContext(),Family_Sharing.class);
            startActivity(f);

        }else if (id == R.id.emergency) {


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //image download start
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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
            bmImage.setImageBitmap(result);
            //Toast.makeText(demo.this, "Error="+res, Toast.LENGTH_SHORT).show();
        }
    }
    //img end
}
