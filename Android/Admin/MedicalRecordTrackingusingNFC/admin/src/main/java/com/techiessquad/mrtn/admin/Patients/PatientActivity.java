package com.techiessquad.mrtn.admin.Patients;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.techiessquad.mrtn.admin.Doctors.Doctor;
import com.techiessquad.mrtn.admin.Labs.LabsActivity;
import com.techiessquad.mrtn.admin.Pharmacy.PharmacyActivity;
import com.techiessquad.mrtn.admin.R;
import com.techiessquad.mrtn.admin.Requests.HomeActivity;
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

import static com.techiessquad.mrtn.admin.Patients.PatientTab_1.MemberID;

public class PatientActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//get data
 private static final String TAG_RESULTS = "result";
    JSONArray peoples = null;
    public static int cnt, cnt1;
    int snavkbarFLG=0;
    public static String myJSON,patientNameSend_server="11223344";
    /*tab 2*/  public static String D_Last_name[],D_first_name[],D_mid_name[],dr_Address[],D_ContactNo[],D_Gender[],D_Email[];
    private ProgressDialog progress;
 //getData
 CoordinatorLayout coordinatorLayout;
    final Context context = this;
     FloatingActionButton fab1;
    private Spinner state,city;
    public static String SelectedState="",SelectedCity="";
    State_and_City sc;
    Menu mOptionsMenu;
    int mOptionsMenu_flg=0;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    TabLayout tabLayout;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_patient);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            setTitle("Patients");
            snavkbarFLG=0;
            coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                    .coordinatorLayout);
            sc=new State_and_City();
            State_and_City.Dr_Ragi_FLG=0;
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

              fab1 = (FloatingActionButton) findViewById(R.id.fab1);
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getApplicationContext(),AddPatient.class);
                    startActivity(i);
                   /* Snackbar.make(view, "Intent to Patient regestration layout", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                }
            });

            final FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   /* Snackbar.make(view, "either this button can be search button or to select state and city", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/


                    final Dialog dialog = new Dialog(PatientActivity.this);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.dialog_city_filter);
                    // Set dialog title
                    dialog.setTitle("Custom Dialog");


                    // set values for custom dialog components - text, image and button
                    state = (Spinner) dialog.findViewById(R.id.a);

                    city = (Spinner) dialog.findViewById(R.id.b);
                    //  image.setImageResource(R.drawable.image0);

                    try {
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(PatientActivity.this,
                                android.R.layout.simple_spinner_item, State_and_City.list);

                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        state.setAdapter(dataAdapter);

                        dataAdapter = new ArrayAdapter<String>(PatientActivity.this,
                                android.R.layout.simple_spinner_item, State_and_City.city_list);
                        city.setAdapter(dataAdapter);

                        //................................city working ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

                        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selected_State = (String) parent.getItemAtPosition(position);
                                SelectedState=selected_State;
                                // Notify the selected item text
                                addItemsOnSpinner2(selected_State);
          /*  for(int i=0;i<State_and_City.TempState.length;i++){
                if(selected_State.equals(State_and_City.TempState[i])){
                    Steate_to_city=State_and_City.TempState[i];
                    addItemsOnSpinner2(Steate_to_city);
                    break;
                }  }*/
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selected_City = (String) parent.getItemAtPosition(position);
                                SelectedCity=selected_City;
                                setTitle("City : "+selected_City);
                                if(SelectedState.equals("-- Select State --")|| SelectedCity.equals("-- Select City --"))

                                {
                                } else  if(!SelectedState.equals("-- Select State --")&& !SelectedCity.equals("-- Select City --")) {
                                    getData();
                                }
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                            }
                        });



                        dialog.show();
                    }catch (Exception s){
                        Toast.makeText(PatientActivity.this, "dig "+s, Toast.LENGTH_LONG).show();
                    }
                }
            });

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
                        mOptionsMenu.getItem(1).setVisible(false);
                        fab1.setVisibility(View.VISIBLE);
                        fab2.setVisibility(View.INVISIBLE);
                        setTitle("Patient");

                    } else if (tab.getPosition() == 1) {
                        if(snavkbarFLG==0) {
                            mOptionsMenu.getItem(1).setVisible(false);
                            fab2.setVisibility(View.INVISIBLE);
                            fab2.setVisibility(View.VISIBLE);
                            Toast.makeText(context, "Please select state and city", Toast.LENGTH_SHORT).show();
                            /*Snackbar snackbar = Snackbar
                                    .make(coordinatorLayout, "Select first State and City", Snackbar.LENGTH_LONG);
                            snackbar.show();*/
                        }
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
            Toast.makeText(context, "dr : "+s, Toast.LENGTH_SHORT).show();
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

            menu.getItem(1).setVisible(true);

        }catch (Exception s){
            Toast.makeText(context, "dr : "+s, Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_location) {

            // Create custom dialog object

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_requests) {
            Intent requests = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Requests");

        } else if (id == R.id.nav_doctors) {
            Intent requests = new Intent(getApplicationContext(),Doctor.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Doctors");

        } else if (id == R.id.nav_patients) {
            Intent requests = new Intent(getApplicationContext(), PatientActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Patients");

        } else if (id == R.id.nav_labs) {
            Intent requests = new Intent(getApplicationContext(),LabsActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Labs");

        } else if (id == R.id.nav_pharmacy) {
            Intent requests = new Intent(getApplicationContext(),PharmacyActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Pharmacy");
        }/*else if (id == R.id.nav_labs) {
            Intent requests = new Intent(getApplicationContext(),DoctorsActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Doctors");

        } else if (id == R.id.nav_patients) {
            Intent requests = new Intent(getApplicationContext(),PatientsActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Patients");

        } else if (id == R.id.nav_labs) {
            Intent requests = new Intent(getApplicationContext(),LabsActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Labs");

        } else if (id == R.id.nav_pharmacy) {
            Intent requests = new Intent(getApplicationContext(),PharmacyActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Pharmacy");

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {
            Intent requests = new Intent(getApplicationContext(),FeedbackActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Feedback");

        } else if (id == R.id.nav_faq) {
            Intent requests = new Intent(getApplicationContext(),FAQActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("FAQ");

        }else if (id == R.id.nav_help) {
            Intent requests = new Intent(getApplicationContext(),HelpActivity.class);
            startActivity(requests);
            getSupportActionBar().setTitle("Help");

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void addItemsOnSpinner2(String str) {

        //Toast.makeText(this, "str"+str.replace(' ','_')+"_list", Toast.LENGTH_LONG).show();
        String temp = "State_and_City." + str.replace(' ', '_') + "_list";

        if (str.equals("Andhra Pradesh (AP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Andhra_Pradesh_list);
            city.setAdapter(dataAdapter);

        } else if (str.equals("Assam (AS)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Assam_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Bihar (BR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Bihar_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Chhattisgarh (CG)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Chhattisgarh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Goa (GA)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Goa_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Gujarat (GJ)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Gujarat_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Haryana (HR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Haryana_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Himachal Pradesh (HP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Himachal_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Jammu and Kashmir (JK)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.JammuAnd_Kashmir_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Jharkhand (JH)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Jharkhand_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Karnataka (KA)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Karnataka_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Kerala (KL)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Kerala_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Madhya Pradesh (MP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Madhya_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Maharashtra (MH)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Maharashtra_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Manipur (MN)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Manipur_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Meghalaya (ML)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Meghalaya_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Mizoram (MZ)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Mizoram_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Nagaland (NL)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Nagaland_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Odisha(OR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Odisha_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Punjab (PB)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Punjab_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Pondicherry (PY)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Pondicherry_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Rajasthan (RJ)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Rajasthan_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Sikkim (SK)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Sikkim_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Tamil Nadu (TN)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Tamil_Nadu_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Telangana (TS)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Telangana_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Tripura (TR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Tripura_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Uttar Pradesh (UP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Uttar_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Uttarakhand (UK)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Uttarakhand_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Andhra Pradesh (AP)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Andhra_Pradesh_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("West Bengal (WB)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.West_Bengal_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("-- Select State --")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.city_list);
            city.setAdapter(dataAdapter);
        } else if (str.equals("Arunachal Pradesh (AR)")) {
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, State_and_City.Arunachal_Pradesh_list);
            city.setAdapter(dataAdapter);



        }
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
            View rootView = inflater.inflate(R.layout.content_patient, container, false);

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
                    PatientTab_1 tab1 = new PatientTab_1();
                    return tab1;
                case 1:
                    PatientTab_2 tab2 = new PatientTab_2();
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


                //   httppost = new HttpPost(State_and_City.url + "findDoctor.php?State=Maharashtra%20(MH)&City=Pune" + patientNameSend_server.replace(" ", "%20"));
                 httppost = new HttpPost(State_and_City.url + "findPatient.php?State="+SelectedState.replace(" ", "%20")+"&City="+SelectedCity.replace(" ", "%20"));
                //httppost = new HttpPost(State_and_City.url + "findPatient.php?State=Maharashtra%20(MH)&City=Pune");

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
                    // SelectedState="-- Select State --";
                    SelectedCity="-- Select City --";
                    myJSON=result;
                   // Toast.makeText(context, "MyJson: "+myJSON, Toast.LENGTH_SHORT).show();
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


            D_Last_name = new String[cnt];
            D_first_name = new String[cnt];
            D_mid_name = new String[cnt];
            dr_Address = new String[cnt];
            D_ContactNo = new String[cnt];
            D_Gender = new String[cnt];
            D_Email = new String[cnt];




            for(int i=0;i<peoples.length();i++){//.............................myjsoncha toast khod
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


                    D_Last_name[cnt1] = c.getString("D_Last_name");
                    D_first_name[cnt1] = c.getString("D_first_name");

                D_mid_name[cnt1] = c.getString("D_mid_name");
                dr_Address[cnt1] = c.getString("dr_Address");
                D_ContactNo[cnt1] = c.getString("D_ContactNo");
                D_Gender[cnt1] = c.getString("D_Gender");
                D_Email[cnt1] = c.getString("D_Email");

                cnt1++;
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
            //  setTitle("sfse1");


                layout_all_patient r = new layout_all_patient(this, D_Last_name, D_first_name,D_mid_name,D_Email,D_ContactNo,MemberID);
                PatientTab_2.listViewReport.setAdapter(r);



            if(cnt==0) {
                Toast.makeText(getApplicationContext(), "No Records Available", Toast.LENGTH_LONG).show();
                snavkbarFLG=0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
//phone thew techa sangto