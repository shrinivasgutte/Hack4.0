package com.techiessquad.mrtn.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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

public class Family_Sharing extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//get data
 private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;
    int snavkbarFLG=0;
    public static String myJSON;
    /*tab 2*/  public static String Relation_Type[],MemberID[],m_Last_name[],m_first_name[],m_mid_name[],m_ContactNo[],users_Type[];
    private ProgressDialog progress;
 //getData
 CoordinatorLayout coordinatorLayout;
    final Context context = this;
    private Spinner state,city;
   // public static String SelectedState="Maharashtra (MH)",SelectedCity="Pune",TempSelectedCity="";
    State_and_City sc;
    Menu mOptionsMenu;
    int mOptionsMenu_flg=0;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    TabLayout tabLayout;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    LinearLayout profile_clicked;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_family__sharing);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            setTitle("Family Sharing");
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View hView =  navigationView.getHeaderView(0);
            profile_clicked = (LinearLayout)hView.findViewById(R.id.profile_clicked) ;
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
            coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                    .coordinatorLayout);
            sc=new State_and_City();

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(),Family_Req_Send.class);
                    startActivity(i);
                }
            });
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

              navigationView.setNavigationItemSelectedListener(this);


            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            tabLayout = (TabLayout) findViewById(R.id.tabs);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    // Toast.makeText(getApplicationContext(),"Pos onTabSelected: "+tab.getPosition(),Toast.LENGTH_SHORT).show();
                    if (tab.getPosition() == 0) {
                        //mOptionsMenu.getItem(1).setVisible(false);


                    } else if (tab.getPosition() == 1) {
                        getData();
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });



        }catch (Exception s){
            Toast.makeText(getApplicationContext(), "dr : "+s, Toast.LENGTH_SHORT).show();
        }
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
        try {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.doctor, menu);

            mOptionsMenu = menu;

            menu.getItem(1).setVisible(false);

        }catch (Exception s){
            Toast.makeText(getApplicationContext(), "dr : "+s, Toast.LENGTH_SHORT).show();
        }
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
            Intent ss =new Intent(getApplicationContext(),Home.class);
            startActivity(ss);
            finish();
        } else if (id == R.id.nav_doctor) {
            Intent d =new Intent(getApplicationContext(),Doctor.class);
            startActivity(d);
            finish();
        } else if (id == R.id.nav_labs) {
            Intent l =new Intent(getApplicationContext(),Labs.class);
            startActivity(l);
            finish();
        } else if (id == R.id.nav_medicals) {
            Intent ds =new Intent(getApplicationContext(),Medical.class);
            startActivity(ds);
        } else if (id == R.id.nav_hospitals) {
            Intent ds =new Intent(getApplicationContext(),Hospitals.class);
            startActivity(ds);
        } else if (id == R.id.nav_family_sharing) {

        }else if (id == R.id.emergency) {
            Intent f =new Intent(getApplicationContext(),Emergency.class);
            startActivity(f);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.content_doctor, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            //Returning the current tabs
            switch (position) {
                case 0:
                    FamilyTab_1 tab1 = new FamilyTab_1();
                    return tab1;
                case 1:
                    FamilyTab_2 tab2 = new FamilyTab_2();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {

            // Show 3 total pages.
            return 2;
        }
    }
    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());


                httppost = new HttpPost(State_and_City.url + "family_Panding.php?p_id=" + State_and_City.patientNameSend_server.replace(" ", "%20"));


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
                    showList();

                }catch (Exception f){

                    Toast.makeText(getApplicationContext(), "error recive ::"+f, Toast.LENGTH_SHORT).show();
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

                m_ContactNo = new String[cnt];





            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


                    Relation_Type[cnt1] = c.getString("Relation_Type");
                    MemberID[cnt1] = c.getString("MemberID");
                    m_Last_name[cnt1] = c.getString("m_Last_name");

                    m_first_name[cnt1] = c.getString("m_first_name");
                    m_mid_name[cnt1] = c.getString("m_mid_name");
                    m_ContactNo[cnt1] = c.getString("m_ContactNo");




                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
          //  setTitle("sfse1");


                layout_family_panding r = new layout_family_panding(this,m_Last_name,m_first_name,m_mid_name,m_ContactNo,MemberID,State_and_City.patientNameSend_server);
                FamilyTab_2.listViewReport.setAdapter(r);



            if(cnt==0) {
                Toast.makeText(getApplicationContext(), "No Records Available", Toast.LENGTH_LONG).show();
                snavkbarFLG=0;
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "a"+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    State_and_City.userFamilyPatientFLG=0;
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






