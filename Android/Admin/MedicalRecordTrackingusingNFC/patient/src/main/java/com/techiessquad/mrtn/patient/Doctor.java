package com.techiessquad.mrtn.patient;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

private Spinner a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // addItemsOnSpinner2();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();



        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < host.getTabWidget().getChildCount(); i++) {
                 //   host.getTabWidget().getChildAt(i)
                 //           .setBackgroundColor(Color.parseColor("#d64e7ee9")); // unselected

                }

             //   host.getTabWidget().getChildAt(host.getCurrentTab())
             //           .setBackgroundColor(Color.parseColor("#FF4E7EE9")); // selected

            }
        });

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Recent Doctor");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Find Doctor");
        host.addTab(spec);


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
        getMenuInflater().inflate(R.menu.doctor, menu);
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
            final Dialog dialog = new Dialog(Doctor.this);
            // Include dialog.xml file
            dialog.setContentView(R.layout.dialog_city_filter);
            // Set dialog title
            dialog.setTitle("Custom Dialog");


            // set values for custom dialog components - text, image and button
             a = (Spinner) dialog.findViewById(R.id.a);
           // text.setText("Custom dialog Android example.");
            b  = (Spinner) dialog.findViewById(R.id.b);
          //  image.setImageResource(R.drawable.image0);

            List<String> list = new ArrayList<String>();
            list.add("-- Select State --");
            list.add("Andhra Pradesh (AP)");
            list.add("Arunachal Pradesh (AR)");
            list.add("Assam (AS)");
            list.add("Bihar (BR)");
            list.add("Chhattisgarh (CG)");
            list.add("Goa (GA)");
            list.add("Gujarat (GJ)");
            list.add("Haryana (HR)");
            list.add("Himachal Pradesh (HP)");
            list.add("Jammu and Kashmir (JK)");
            list.add("Jharkhand (JH)");
            list.add("Karnataka (KA)");
            list.add("Kerala (KL)");
            list.add("Madhya Pradesh (MP)");
            list.add("Maharashtra (MH)");
            list.add("Manipur (MN)");
            list.add("Meghalaya (ML)");
            list.add("Mizoram (MZ)");
            list.add("Nagaland (NL)");
            list.add("Odisha(OR)");
            list.add("Punjab (PB)");
            list.add("Rajasthan (RJ)");
            list.add("Sikkim (SK)");
            list.add("Tamil Nadu (TN)");
            list.add("Telangana (TS)");
            list.add("Tripura (TR)");
            list.add("Uttar Pradesh (UP)");
            list.add("Uttarakhand (UK)");
            list.add("West Bengal (WB)");

            List<String> AndhraPradesh_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Adilabad");list.add("Anantapur");list.add("Chittoor");list.add("Eluru");list.add("Guntur");list.add("Hyderabad");list.add("Kadapa");list.add("Kakinada");list.add("Karimnagar");list.add("Khammam");list.add("Krishna");list.add("Kurnool");list.add("Mahbubnagar");list.add("Medak");list.add("Nalgonda");list.add("Nellore");list.add("Nizamabad");list.add("Ongole");list.add("Srikakulam");list.add("Visakhapatnam");list.add("Vizianagaram");list.add("Warangal");

            List<String> ArunachalPradesh_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Anjaw");list.add("Changlang");list.add("Dibang Valley");list.add("East Siang");list.add("Kurung Kumey");list.add("Lohit");list.add("Lower Dibang Valley");list.add("Lower Subansiri");list.add("Papum Pare");list.add("Tawang");list.add("Tirap");list.add("Upper Siang");list.add("Upper Subansiri");list.add("West Kameng");list.add("West Siang");

            List<String> Assam_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Baksa");list.add("Barpeta");list.add("Bongaigaon");list.add("Cachar");list.add("Chirang");list.add("Darrang");list.add("Dhemaji");list.add("Dima Hasao");list.add("Dhubri");list.add("Dibrugarh");list.add("Goalpara");list.add("Golaghat");list.add("Hailakandi");list.add("Jorhat");list.add("Kamrup");list.add("Kamrup Metropolitan");list.add("Karbi Anglong");list.add("Karimganj");list.add("Kokrajhar");list.add("Lakhimpur");list.add("Marigaon");list.add("Nagaon");list.add("Nalbari");list.add("Sibsagar");list.add("Sonitpur");list.add("Tinsukia");list.add("Udalguri");

            List<String> Bihar_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Araria");list.add("Arwal");list.add("Aurangabad");list.add("Banka");list.add("Begusarai");list.add("Bhagalpur");list.add("Bhojpur");list.add("Buxar");list.add("Darbhanga");list.add("East Champaran");list.add("Gaya");list.add("Gopalganj");list.add("Jamui");list.add("Jehanabad");list.add("Kaimur");list.add("Katihar");list.add("Khagaria");list.add("Kishanganj");list.add("Lakhisarai");list.add("Madhepura");list.add("Madhubani");list.add("Munger");list.add("Muzaffarpur");list.add("Nalanda");list.add("Nawada");list.add("Patna");list.add("Purnia");list.add("Rohtas");list.add("Saharsa");list.add("Samastipur");list.add("Saran");list.add("Sheikhpura");list.add("Sheohar");list.add("Sitamarhi");list.add("Siwan");list.add("Supaul");list.add("Vaishali");list.add("West Champaran");

            List<String> Chhattisgarh_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Bastar");list.add("Bijapur");list.add("Bilaspur");list.add("Dantewada");list.add("Dhamtari");list.add("Durg");list.add("Jashpur");list.add("Janjgir-Champa");list.add("Korba");list.add("Koriya");list.add("Kanker");list.add("Kabirdham (Kawardha)");list.add("Mahasamund");list.add("Narayanpur");list.add("Raigarh");list.add("Rajnandgaon");list.add("Raipur");list.add("Surguja");

            List<String> Goa_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("North Goa");list.add("South Goa");

            List<String> Gujarat_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Ahmedabad");list.add("Amreli district");list.add("Anand");list.add("Banaskantha");list.add("Bharuch");list.add("Bhavnagar");list.add("Dahod");list.add("The Dangs");list.add("Gandhinagar");list.add("Jamnagar");list.add("Junagadh");list.add("Kutch");list.add("Kheda");list.add("Mehsana");list.add("Narmada");list.add("Navsari");list.add("Patan");list.add("Panchmahal");list.add("Porbandar");list.add("Rajkot");list.add("Sabarkantha");list.add("Surendranagar");list.add("Surat");list.add("Vyara");list.add("Vadodara");list.add("Valsad");

            List<String> Haryana_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Ambala");list.add("Bhiwani");list.add("Faridabad");list.add("Fatehabad");list.add("Gurgaon");list.add("Hissar");list.add("Jhajjar");list.add("Jind");list.add("Karnal");list.add("Kaithal");list.add("Kurukshetra");list.add("Mahendragarh");list.add("Mewat");list.add("Palwal");list.add("Panchkula");list.add("Panipat");list.add("Rewari");list.add("Rohtak");list.add("Sirsa");list.add("Sonipat");list.add("Yamuna Nagar");

            List<String> HimachalPradesh_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Bilaspur");list.add("Chamba");list.add("Hamirpur");list.add("Kangra");list.add("Kinnaur");list.add("Kullu");list.add("Lahaul and Spiti");list.add("Mandi");list.add("Shimla");list.add("Sirmaur");list.add("Solan");list.add("Una");

            List<String> JammuAndKashmir_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Anantnag");list.add("Badgam");list.add("Bandipora");list.add("Baramulla");list.add("Doda");list.add("Ganderbal");list.add("Jammu");list.add("Kargil");list.add("Kathua");list.add("Kishtwar");list.add("Kupwara");list.add("Kulgam");list.add("Poonch");list.add("Pulwama");list.add("Rajauri");list.add("Ramban");list.add("Reasi");list.add("Samba");list.add("Shopian");list.add("Srinagar");list.add("Udhampur");

            List<String> Jharkhand_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Bokaro");list.add("Chatra");list.add("Deoghar");list.add("Dhanbad");list.add("Dumka");list.add("East Singhbhum");list.add("Garhwa");list.add("Gumla");list.add("Hazaribag");list.add("Jamtara");list.add("Khunti");list.add("Koderma");list.add("Latehar");list.add("Lohardaga");list.add("Pakur");list.add("Ramgarh");list.add("Ranchi");list.add("Sahibganj");list.add("Seraikela Kharsawan");list.add("Simdega");list.add("West Singhbhum");

            List<String> Karnataka_list = new ArrayList<String>();
            list.add("-- Select City --");
            list.add("Bagalkot");
            list.add("Bangalore Rural");
            list.add("Bangalore Urban");
            list.add("Belgaum");
            list.add("Bellary");
            list.add("Bidar");
            list.add("Bijapur");
            list.add("Chamarajnagar");
            list.add("Chikkamagaluru");
            list.add("Chikkaballapur");
            list.add("Chitradurga");
            list.add("Davanagere");
            list.add("Dharwad");
            list.add("Dakshina Kannada");
            list.add("Gadag");
            list.add("Gulbarga");
            list.add("Hassan");
            list.add("Haveri district");
            list.add("Kodagu");
            list.add("Kolar");
            list.add("Koppal");
            list.add("Mandya");
            list.add("Mysore");
            list.add("Raichur");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");
            list.add("");






            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            a.setAdapter(dataAdapter);
            b.setAdapter(dataAdapter);

            dialog.show();
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

        } else if (id == R.id.nav_labs) {
            Intent l =new Intent(getApplicationContext(),Labs.class);
            startActivity(l);
            finish();
        } else if (id == R.id.nav_medicals) {

        } else if (id == R.id.nav_hospitals) {

        } else if (id == R.id.nav_family_sharing) {
            Intent f =new Intent(getApplicationContext(),Family_Sharing.class);
            startActivity(f);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {






    }

}
