package com.techiessquad.mrtn.doctor;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddPatient extends AppCompatActivity {

     DatePickerDialog datePickerDialog;
    CircularImageView profile_image_view;
    ImageButton profile_image_button;
    MaterialEditText ET_Aadhar_NO,ET_First_Name,ET_Middle_Name,ET_Last_Name,ET_Address,ET_Contact_NO,ET_UserName,ET_Password,ET_Verify_Password,ET_Email_ID,ET_DOB,ET_Allergies,ET_Speciality,ET_CertificateNo;
    String String_Allergies,String_Aadhar_NO,String_First_Name,String_Middle_Name,String_Last_Name,String_Address,String_Contact_NO,String_Password,String_Email_ID,String_DOB,String_Gender,String_Username,String_City,String_State, String_BloodGroup,String_NFC,String_ID;
    private ProgressDialog progress;
    String receivedValue;
    Calendar DOB;
    Boolean NFC_AVAILABLE=true;

    //City & STate
    Spinner bloodgroup,hospitalspnir,qualification;
    private Spinner state,city;
    State_and_City sc;
    public static String SelectedState="";
    //End City & State
    private DatePicker datePicker;


    private int year, month, day;

    //NFC
    IntentFilter[] intentFiltersArray;
    String[][] techListsArray;
    PendingIntent pendingIntent;
    NfcAdapter mNfcAdapter;
    private static final String TAG_RESULTS="result";
    public static String NFCID,Data;
    //End NFC

    //Image
    private Boolean upflag = false;
    private Uri selectedImage = null;
    private Bitmap bitmap, bitmapRotate;
    private ProgressDialog pDialog;
    String imagepath = "";
    String fname="";
    File file;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "AddPatient";
    //End Image

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            setTitle("Add PatientFragment");

            //Init
            sc = new State_and_City();

            hospitalspnir = findViewById(R.id.h);

            profile_image_button = (ImageButton) findViewById(R.id.profile_image_button);
            profile_image_view = (CircularImageView) findViewById(R.id.profile_image_view);
            final RadioGroup Gender = (RadioGroup) findViewById(R.id.Gender);


            //Temp Declaration
        /*String_BloodGroup="B+";
        String_State="Maharastra";
        String_City="Pune";*/
            //End Temp Declaration

            //Spinner
            try {

                bloodgroup = (Spinner) findViewById(R.id.bloodgroup);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.bloodgroups, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bloodgroup.setAdapter(adapter);

                //EditTexts
                ET_Allergies = (MaterialEditText) findViewById(R.id.Allergies);
                ET_Aadhar_NO = (MaterialEditText) findViewById(R.id.ET_Aadhar_NO);
                ET_First_Name = (MaterialEditText) findViewById(R.id.ET_First_Name);
                ET_Middle_Name = (MaterialEditText) findViewById(R.id.ET_Middle_Name);
                ET_Last_Name = (MaterialEditText) findViewById(R.id.ET_Last_Name);
                ET_Address = (MaterialEditText) findViewById(R.id.ET_Address);
                ET_Contact_NO = (MaterialEditText) findViewById(R.id.ET_Contact_NO);
                ET_Password = (MaterialEditText) findViewById(R.id.ET_Password);
                ET_Verify_Password = (MaterialEditText) findViewById(R.id.ET_Verify_Password);
                ET_Email_ID = (MaterialEditText) findViewById(R.id.ET_Email_ID);
                ET_DOB = (MaterialEditText) findViewById(R.id.ET_DOB);
                ET_UserName = (MaterialEditText) findViewById(R.id.ET_username);
                ET_Speciality  = (MaterialEditText) findViewById(R.id.ET_Speciality);
                ET_CertificateNo = (MaterialEditText) findViewById(R.id.ET_CertificateNo);

                //......................................................................  State & City Start ...............................................................................................................

                state = findViewById(R.id.a);

                city = findViewById(R.id.b);
                //  image.setImageResource(R.drawable.image0);
                qualification = (Spinner) findViewById(R.id.c);

                if(State_and_City.Dr_Ragi_FLG!=1){
                    ET_Speciality.setVisibility(View.INVISIBLE);
                    ET_CertificateNo.setVisibility(View.INVISIBLE);
                    qualification.setVisibility(View.INVISIBLE);
                }else {
                    ET_Speciality.setVisibility(View.VISIBLE);
                    ET_CertificateNo.setVisibility(View.VISIBLE);
                    qualification.setVisibility(View.VISIBLE);
                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, State_and_City.list);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                state.setAdapter(dataAdapter);

                dataAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, State_and_City.city_list);
                city.setAdapter(dataAdapter);

//................................city working ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,

                state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selected_State = (String) parent.getItemAtPosition(position);
                        SelectedState = selected_State;
                        String_State = SelectedState;
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
                        String_City = selected_City;


                      /*  setTitle("City : "+selected_City);
                        if(SelectedState.equals("-- Select State --")|| SelectedCity.equals("-- Select City --")){
                        } else  if(!SelectedState.equals("-- Select State --")&& !SelectedCity.equals("-- Select City --")) {
                           // getData();
                        }*/
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
                bloodgroup = (Spinner) findViewById(R.id.bloodgroup);
                dataAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, State_and_City.bloodgroup);
               // bloodgroup.setAdapter(dataAdapter);


//......................................................................  State & City End ...............................................................................................................


            } catch (Exception ex) {
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                finish();
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.NFC) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "NFC", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.NFC}, 0);
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BIND_NFC_SERVICE) !=
                    PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BIND_NFC_SERVICE}, 0);
            //NFC Adapter
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

            //End Init

            //NFC Program
            if (mNfcAdapter == null) {
                NFC_AVAILABLE = false;
                Toast.makeText(this, "NFC Not Available Cannot Register PatientFragment", Toast.LENGTH_SHORT).show();
                finish();
            } else {
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
            //End NFC Program

            //Listeners
            try {

                bloodgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String_BloodGroup = (String) parent.getItemAtPosition(position);
                      /*  setTitle("City : "+selected_City);
                        if(SelectedState.equals("-- Select State --")|| SelectedCity.equals("-- Select City --")){
                        } else  if(!SelectedState.equals("-- Select State --")&& !SelectedCity.equals("-- Select City --")) {
                           // getData();
                        }*/
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                    }
                });
            } catch (Exception ex) {
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            }

            Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (Gender.getCheckedRadioButtonId() == R.id.GMale) {
                        String_Gender = "Male";
                    } else if (Gender.getCheckedRadioButtonId() == R.id.GFemale) {
                        String_Gender = "Female";
                    }
                }
            });

            //End

            //Dialogs
            try {
             /*   datePickerDialog = new DatePickerDialog(this);
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        ET_DOB.setText("" + dayOfMonth + "/" + month + "/" + year);
                    }
                });
                //End Dialogs
*/
                //OnTouchListener
                ET_DOB.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        showDialog(999);
                        return false;
                    }
                });


            }catch (Exception a){
                Toast.makeText(this, "dateP err "+a, Toast.LENGTH_SHORT).show();
            }
            //End OnTouchListener

            //OnClickListener
            profile_image_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new MaterialDialog.Builder(AddPatient.this)
                            .title("Profile Picture")
                            .items("Select From Gallery", "Take from Camera")
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                    if (which == 0) {
                                        openImageChooser();
                                    } else {
                                        Toast.makeText(AddPatient.this, "Not Yet implemented", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .show();
                }
            });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reg();
                }
            });
                    /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            //End OnClickListener
        }catch (Exception d){
            Toast.makeText(this, "addp "+d, Toast.LENGTH_SHORT).show();
        }
    }

    //Aj DatePicker start
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        ET_DOB.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
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

    @Override
    protected void onResume() {
        super.onResume();
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
    }

    @Override
    protected void onPause() {
        super.onPause();
            mNfcAdapter.disableForegroundDispatch(this);

    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tagFromIntent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            Tag myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage msg = (NdefMessage) rawMessages[0];
            Data = new String(msg.getRecords()[0].getPayload());
            NFCID = bytesToHex(myTag.getId());
            String_NFC=NFCID;
            Toast.makeText(this, "ID : "+NFCID, Toast.LENGTH_SHORT).show();
        }
    }
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {


            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);

                    Log.i(TAG, "Image Path : " + path);
                    // Set the image in ImageView
                    profile_image_view.setImageURI(selectedImageUri);
                }
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    //    Saving file to the mobile internal memory
    private void saveFile(Bitmap sourceUri, File destination) {
        if (destination.exists()) destination.delete();
        try {
            FileOutputStream out = new FileOutputStream(destination);
            sourceUri.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

            new DoFileUpload().execute();

        } catch (Exception e) {
            Toast.makeText(this, "Error 1="+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    boolean ver()
    {
        try
        {


            if((String_Aadhar_NO=ET_Aadhar_NO.getText().toString())==""||String_Aadhar_NO.length()!=12)
            {
                ET_Aadhar_NO.setError("Enter Valid Aadhar Number !");
                return false;
            }
            else if((String_First_Name=ET_First_Name.getText().toString())=="")
            {
                ET_First_Name.setError("Enter First Name !");
                return false;
            }
            else if((String_Middle_Name=ET_Middle_Name.getText().toString())=="")
            {
                ET_Middle_Name.setError("Enter Middle Name !");
                return false;
            }
            else if((String_Last_Name=ET_Last_Name.getText().toString())=="")
            {
                ET_Last_Name.setError("Enter Last Name !");
                return false;
            }
            else if((String_Contact_NO=ET_Contact_NO.getText().toString())=="")
            {
                ET_Contact_NO.setError("Enter Contact Number !");
                return false;
            }
            else if((String_Address=ET_Address.getText().toString())=="")
            {
                ET_Address.setError("Enter Address !");
                return false;
            }
            else if((String_Password=ET_Password.getText().toString())=="")
            {
                ET_Password.setError("Enter Password !");
                return false;
            }
            else if(String_Password==ET_Verify_Password.getText().toString())
            {
                ET_Verify_Password.setError("Password Dosnt Match !");
                return false;
            }
            else if((String_Email_ID=ET_Email_ID.getText().toString())=="")
            {
                ET_Email_ID.setError("Enter Email ID !");
                return false;
            }
            else if((String_DOB=ET_DOB.getText().toString())=="")
            {
                ET_DOB.setError("Select DOB !");
                return false;
            }
            else if((String_Username=ET_UserName.getText().toString())=="")
            {
                ET_UserName.setError("Enter Username !");
                return false;
            }
            else if((String_Allergies=ET_Allergies.getText().toString())=="")
            {
                ET_Allergies.setError("Enter Allergies !");
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    void reg()
    {
        if(ver())
        {
            new backgroundProcessClass().execute("");


        }
    }
    //Registration
    private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(AddPatient.this);
            progress.setMessage("Wait...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(false);
            progress.setProgress(0);
            progress.setCancelable(false);
            progress.show();// To start
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(receivedValue.contains("Error"))
            {
                Toast.makeText(AddPatient.this, "Error Registration Failed !\nError : "+receivedValue, Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("Success"))
            {
                String_ID=receivedValue.substring(10);
                Toast.makeText(AddPatient.this, String_ID, Toast.LENGTH_SHORT).show();
                fname=String_ID+".jpg";
                String root = getApplicationContext().getFilesDir().toString();
                File myDir = new File(root + "/androidlift");
                myDir.mkdirs();
                imagepath = root + "/androidlift/" + fname;
                file = new File(myDir, fname);
                upflag = true;
                bitmapRotate = ((BitmapDrawable)profile_image_view.getDrawable()).getBitmap();
                saveFile(bitmapRotate, file);
                Toast.makeText(AddPatient.this, "Registration Success", Toast.LENGTH_SHORT).show();
            }

            progress.dismiss();
            //Toast.makeText(AddPatient.this, receivedValue, Toast.LENGTH_SHORT).show();
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {
            HttpClient client;
            HttpPost post;
            client = new DefaultHttpClient();
            post = new HttpPost(State_and_City.url_admin+"PatientRegistration.php");

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
            try
            {


                pairs.add(new BasicNameValuePair("FirstName",String_First_Name));
                pairs.add(new BasicNameValuePair("MiddleName",String_Middle_Name));
                pairs.add(new BasicNameValuePair("LastName",String_Last_Name));
                pairs.add(new BasicNameValuePair("Address",String_Address));
                pairs.add(new BasicNameValuePair("ContactNo",String_Contact_NO));
                pairs.add(new BasicNameValuePair("Gender",String_Gender));
                pairs.add(new BasicNameValuePair("DOB",String_DOB));
                pairs.add(new BasicNameValuePair("BloodGrp", String_BloodGroup));
                pairs.add(new BasicNameValuePair("UserName",String_Username));
                pairs.add(new BasicNameValuePair("Password",String_Password));
                pairs.add(new BasicNameValuePair("NfcID",String_NFC));
                pairs.add(new BasicNameValuePair("Email",String_Email_ID));
                pairs.add(new BasicNameValuePair("AadharNo",String_Aadhar_NO));
                pairs.add(new BasicNameValuePair("State",String_State));
                pairs.add(new BasicNameValuePair("City",String_City));
                pairs.add(new BasicNameValuePair("Allergies",String_Allergies));
            }
            catch (Exception ex)
            {

            }
            try {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            } catch (Exception ex) {

            }
            try {
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                receivedValue = client.execute(post, responseHandler);
            } catch (Exception ex) {
                Toast.makeText(AddPatient.this, ex.toString(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
    //End Registration

    //Image Upload
    class DoFileUpload extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(AddPatient.this);
            pDialog.setMessage("wait uploading Image..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // Set your file path here
                FileInputStream fstrm = new FileInputStream(imagepath);
                // Set your server page url (and the file title/description)
                HttpFileUpload hfu = new HttpFileUpload(State_and_City.url_admin+"ImageUpload.php?nfc_id=Profile", "ftitle", "fdescription", fname);
                upflag = hfu.Send_Now(fstrm);
            }
            catch (Exception e) {
                // Error: File not found
                Toast.makeText(getApplicationContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }
            if (upflag) {
                Toast.makeText(getApplicationContext(), "Uploading Complete", Toast.LENGTH_LONG).show();
                Intent a=new Intent(getApplicationContext(),Patient.class);
                startActivity(a);
                 finish();

            } else {
                Toast.makeText(getApplicationContext(), "Unfortunately file is not Uploaded..", Toast.LENGTH_LONG).show();
            }
        }
    }
    //End Image Upload

    // HEX to String Converter for NFC
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
    //End HEX to String Converter for NFC
}
