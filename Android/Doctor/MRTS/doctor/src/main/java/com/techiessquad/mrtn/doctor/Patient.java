package com.techiessquad.mrtn.doctor;

import android.Manifest;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.techiessquad.mrtn.doctor.PatientProfileDitails.Patient_Offline_Data;

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

public class Patient extends AppCompatActivity {
Button Offline;
    public static String myJSON;
 //   private ProgressDialog progress;
    public static int id;
    JSONArray peoples = null;
    int cnt;
    public static String ID,Name,Address,ContactNo,Gender,DOB,BloodGRP,Password,Email,Aadhar,State,City;

    public static String Allergies;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;
ProgressDialog progress;
    IntentFilter[] intentFiltersArray;
    String[][] techListsArray;
    PendingIntent pendingIntent;
    NfcAdapter mNfcAdapter;
    private static final String TAG_RESULTS="result";
    public static String NFCID,Data,Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try{
            Offline= (Button) findViewById(R.id.offline);
            progress=new ProgressDialog(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

            tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    // Toast.makeText(getApplicationContext(),"Pos onTabSelected: "+tab.getPosition(),Toast.LENGTH_SHORT).show();
                    if (tab.getPosition() == 0) {

                        Offline.setVisibility(View.VISIBLE);
                    } else if (tab.getPosition() == 1) {
                        Offline.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
        //teb..........................end
            Offline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Data.equals("")){
                        Toast.makeText(Patient.this, "Scan patient", Toast.LENGTH_SHORT).show();
                    }else {
                       Intent i = new Intent(getApplicationContext(),Patient_Offline_Data.class);
                        startActivity(i);
                    }

                }
            });

       final Intent i =new Intent(this,AddPatient.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
               startActivity(i);
            }
        });
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.NFC)!= PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(this, "NFC", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.NFC},0);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.BIND_NFC_SERVICE)!=
                PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.BIND_NFC_SERVICE},0);
        //NFC Adapter
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        //End Init

        //NFC Program
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC Not Available Cannot Register PatientFragment", Toast.LENGTH_SHORT).show();
            finish();
        }
        else {
            pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            try {
                ndef.addDataType("*/*");
                // /* Handles all MIME based dispatches.                                    You should specify only the ones that you need. */
            } catch (IntentFilter.MalformedMimeTypeException e) {
                throw new RuntimeException("fail", e);
            }
            intentFiltersArray = new IntentFilter[]{ndef,};
            techListsArray = new String[][]{new String[]{NfcF.class.getName()}};
        }
        }catch (Exception f){

            Toast.makeText(getApplicationContext(), "pt onc ::"+f, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mNfcAdapter!=null)
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mNfcAdapter!=null)
        mNfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_patient, menu);
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

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

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
            View rootView = inflater.inflate(R.layout.fragment_patient, container, false);

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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new ScanPatient();
                case 1:
                    return new RecentPatient();
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tagFromIntent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            Tag myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage msg = (NdefMessage) rawMessages[0];
            Data=new String(msg.getRecords()[0].getPayload());
            NFCID=bytesToHex(myTag.getId());
            //Toast.makeText(this, ""+NFCID, Toast.LENGTH_SHORT).show();

            getData();
            progress.setMessage("Verifing...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();
        }
    }

    final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }



    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {
            HttpPost httppost;
            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

              /*  if(Username != null){
                    httppost = new HttpPost(Strings.url + "Doctor/GetPatientDataUsername.php?&id="+Username);
                    //Toast.makeText(PasswordActivity.this, "1 "+url.Url+"login.php?Username="+Username+"&Password="+PasswordS, Toast.LENGTH_LONG).show();
                }else  if(NFCID != null){*/
                    httppost = new HttpPost(Strings.url + "Doctor/GetPatientDataNFC.php?&id="+NFCID);



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
                   // Toast.makeText(Patient.this, "myj "+myJSON, Toast.LENGTH_SHORT).show();
                    showList();

                }catch (Exception f){
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "Go online :sc_p:", Toast.LENGTH_SHORT).show();
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


            for(int i=0;i<peoples.length();i++){
                cnt++;
            }

         /*   LabName = new String[cnt];
            LabID = new String[cnt];
            Type = new String[cnt];
            Address = new String[cnt];
            LabDate= new String[cnt];
*/



            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                //descriptionType[cnt1]=c.getString("descriptionType");


             /*   LabName[cnt1] = c.getString("LabName");
                LabID[cnt1] = c.getString("LabID");
                Type[cnt1] = c.getString("Type");
                Address[cnt1] = c.getString("Address");
                LabDate[cnt1] = c.getString("reportDate");

*/
                Name=c.getString("Name");
                Address=c.getString("Address");
                ContactNo=c.getString("ContactNo");
                Gender=c.getString("Gender");
                DOB=c.getString("DOB");
                BloodGRP=c.getString("BloodGrp");
                Username=c.getString("UserName");
                NFCID=c.getString("NFCID");
                Email=c.getString("Email");
                Aadhar=c.getString("AadharNo");
                State=c.getString("State");
                City=c.getString("City");
                Allergies=c.getString("Allergies");
               State_and_City.ScannedPatientID= id=c.getInt("id");
                ID=(new Integer(id)).toString();
               progress.dismiss();
            }
            // setTitle(patientNameF[0]+" "+patientNameL[0]);
            //  setTitle("sfse1");


          //  Toast.makeText(this, "EPatientID "+EPatientID, Toast.LENGTH_SHORT).show();
            State_and_City.PatientIDstr= String.valueOf(State_and_City.ScannedPatientID);
            Intent in = new Intent(getApplicationContext(),PatientProfile.class);
            startActivity(in);


            if(cnt==0) {
                Toast.makeText(this, "No Records Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(this, "last"+e, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
