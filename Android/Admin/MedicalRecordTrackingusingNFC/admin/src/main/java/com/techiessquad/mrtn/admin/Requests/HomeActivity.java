package com.techiessquad.mrtn.admin.Requests;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.techiessquad.mrtn.admin.Doctors.Doctor;
import com.techiessquad.mrtn.admin.Labs.LabsActivity;
import com.techiessquad.mrtn.admin.Patients.PatientActivity;
import com.techiessquad.mrtn.admin.Pharmacy.PharmacyActivity;
import com.techiessquad.mrtn.admin.R;
import com.techiessquad.mrtn.admin.State_and_City;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout homeTabs;
    ViewPager viewPager;
    DrawerLayout drawer;
    CircleImageView profile_image;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Admin Name");

        // getData();
        // setTitle("sfse1");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        Fresco.initialize(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView = navigationView.getHeaderView(0);
       // profile_clicked = (LinearLayout) hView.findViewById(R.id.profile_clicked);
        TextView name = (TextView) hView.findViewById(R.id.name);
        TextView email = (TextView) hView.findViewById(R.id.email);

        Fresco.initialize(this);
        // TextView nav_user = (TextView)hView.findViewById(R.id.nav_doctor);
        // nav_user.setText(user);
        //  names =(TextView)findViewById(R.id.name) ;
        profile_image = (CircleImageView) hView.findViewById(R.id.profile_image);

        //  names.setText("dsf");
        try {
            name.setText(State_and_City.FirstName + " " + State_and_City.MiddleName + " " + State_and_City.LastName);
            email.setText(State_and_City.Email);
            new DownloadImageTask((ImageView) hView.findViewById(R.id.profile_image))
                    .execute(State_and_City.url_patient_report + State_and_City.patientNameSend_server +"/"+ State_and_City.patientNameSend_server +".jpg");


            navigationView.setNavigationItemSelectedListener(this);
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }catch(
    Exception s)

    {
        Toast.makeText(this, "sss " + s, Toast.LENGTH_SHORT).show();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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

        } /*else if (id == R.id.nav_pharmacy) {
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
            View rootView = inflater.inflate(R.layout.content_home, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
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
                    Pending_tab1 tab1 = new Pending_tab1();
                    return tab1;
                case 1:
                    Accepted_tab2 tab2 = new Accepted_tab2();
                    return tab2;
                case 2:
                    Rejected_tab3 tab3 = new Rejected_tab3();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
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




