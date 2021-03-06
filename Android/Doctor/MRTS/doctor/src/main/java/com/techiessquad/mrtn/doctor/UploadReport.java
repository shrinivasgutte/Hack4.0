package com.techiessquad.mrtn.doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.techiessquad.mrtn.doctor.PatientProfileDitails.HomeTab2_reportsDetails;
import com.techiessquad.mrtn.doctor.PatientProfileDitails.HomeTab_2;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UploadReport extends AppCompatActivity {


    SubsamplingScaleImageView image;
    MaterialEditText ETDescription,ETtype,ETName;

    private Boolean upflag = false;
    private Uri selectedImage = null;
    private Bitmap bitmap, bitmapRotate;
    private ProgressDialog pDialog;
    String imagepath = "";
    String fname,currentDateTimeString;
    File file;
LinearLayout layouta;

    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "UploadReport";
    String Type,Description,name,receivedValue;
    View views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
try {
    image = (SubsamplingScaleImageView) findViewById(R.id.imageView);
    ETDescription = (MaterialEditText) findViewById(R.id.Description);
    ETtype = (MaterialEditText) findViewById(R.id.type);
    ETName = (MaterialEditText) findViewById(R.id.name);
    layouta = (LinearLayout) findViewById(R.id.layouta);
    TextView casepaperlabel = (TextView) findViewById(R.id.casepaperlabel);
    if (State_and_City.casepaperFLG == 0) {
        Toast.makeText(this, "ffl " + State_and_City.casepaperFLG, Toast.LENGTH_SHORT).show();
        layouta.setVisibility(View.VISIBLE);

        casepaperlabel.setText("Upload for New Operation");
        casepaperlabel.setBackgroundColor(Color.parseColor("#64cf1212"));
    } else if (State_and_City.casepaperFLG == 1) {
        Toast.makeText(this, "ffl " + State_and_City.casepaperFLG, Toast.LENGTH_SHORT).show();
        casepaperlabel.setText("Upload for Current Operation");
        casepaperlabel.setBackgroundColor(Color.parseColor("#6412cf28"));
        layouta.setVisibility(View.INVISIBLE);


    }



    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            try {
            if (State_and_City.casepaperFLG == 0) {
                currentDateTimeString = DateFormat.getDateTimeInstance()
                        .format(new Date());
                name = ETName.getText().toString();
                Type = ETtype.getText().toString();
            } else if (State_and_City.casepaperFLG == 1) {

                currentDateTimeString = HomeTab_2.reportDate_Temp.replaceAll("\\W", "");
            }
            views = view;

            Description = ETDescription.getText().toString();
              saveFile(bitmapRotate, file);
        }catch (Exception f) {
                Toast.makeText(getApplicationContext(), "pl " + f, Toast.LENGTH_SHORT).show();
            }

        }
    });
    openImageChooser();

}catch (Exception f){
    Toast.makeText(this, "pl "+f, Toast.LENGTH_SHORT).show();
}
    }
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select CasePaper"), SELECT_PICTURE);
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
                    //ivImage.setImageURI(selectedImageUri);

                    //Give the file name that u want
                    //fname =et.getText().toString()+".jpg";

                    fname=State_and_City.ScannedPatientID+(Calendar.getInstance().getTimeInMillis())+".jpg";
                    image.setImage(ImageSource.uri(selectedImageUri));
                    String root = getApplicationContext().getFilesDir().toString();
                    File myDir = new File(root + "/androidlift");
                    myDir.mkdirs();
                    imagepath = root + "/androidlift/" + fname;
                    file = new File(myDir, fname);
                    upflag = true;

                    // bitmapRotate = ((BitmapDrawable)ivImage.getDrawable()).getBitmap();
                    //image.viewTOs
                    try
                    {
                        bitmapRotate= MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(this, "EX : "+ex.toString(), Toast.LENGTH_SHORT).show();
                    }

                    //Uri imageUri = data.getData();
                    // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
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
            sourceUri.compress(Bitmap.CompressFormat.JPEG, 60, out);
            out.flush();
            out.close();

            new DoFileUpload().execute();

        } catch (Exception e) {
            Toast.makeText(this, "Error 1="+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class DoFileUpload extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(UploadReport.this);
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
                HttpFileUpload hfu = new HttpFileUpload(Strings.url+"Doctor/ImageUpload.php?nfc_id="+State_and_City.PatientIDstr+"&folder="+State_and_City.STR_reportsUploadFLG+"&date_time="+currentDateTimeString.replaceAll("\\W",""), "ftitle", "fdescription", fname);
                upflag = hfu.Send_Now(fstrm);
            }
            catch (FileNotFoundException e) {
                // Error: File not found
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
                if(State_and_City.casepaperFLG==0) {
                    new backgroundProcessClass().execute();
                }else {
                    Intent s = new Intent(getApplicationContext(),HomeTab2_reportsDetails.class);
                    startActivity(s);
                    finish();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Unfortunately file is not Uploaded..", Toast.LENGTH_LONG).show();
            }
        }
    }

    //ImageUpload End


    private class backgroundProcessClass extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(receivedValue.contains("Error"))
            {
                Toast.makeText(UploadReport.this, "Error Registration Failed !\nError : "+receivedValue, Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("Success"))
            {
                Toast.makeText(UploadReport.this, "Upload Complete", Toast.LENGTH_SHORT).show();
                Intent s = new Intent(getApplicationContext(),PatientProfile.class);
                startActivity(s);
                finish();

            }
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {
            HttpClient client;
            HttpPost post;
            client = new DefaultHttpClient();
            post = new HttpPost(Strings.url+"Doctor/UploadReport.php");

            //temp=params[0];
            List<NameValuePair> pairs = new ArrayList<NameValuePair>(1);
            try
            {
                pairs.add(new BasicNameValuePair("Description",Description));
                pairs.add(new BasicNameValuePair("Type",Type));
                pairs.add(new BasicNameValuePair("name",name));
                pairs.add(new BasicNameValuePair("DoctorID",State_and_City.staticDR_ID));
                pairs.add(new BasicNameValuePair("PatientID",State_and_City.PatientIDstr));
                pairs.add(new BasicNameValuePair("HospitalID",State_and_City.staticHospitalID));
                pairs.add(new BasicNameValuePair("image",fname));
                pairs.add(new BasicNameValuePair("date_time",currentDateTimeString.replaceAll("\\W","")));
                pairs.add(new BasicNameValuePair("LabID","7591536"));

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
                Toast.makeText(UploadReport.this, ex.toString(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
}
