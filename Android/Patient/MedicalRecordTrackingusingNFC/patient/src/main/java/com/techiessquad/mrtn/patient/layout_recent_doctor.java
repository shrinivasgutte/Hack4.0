package com.techiessquad.mrtn.patient;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ajits on 17-12-2017.
 */

public class layout_recent_doctor extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Qualification,casep_Date,HospitalName
    
   
    private final String[] doctorNameL;
    private final String[] doctorNameF;
    private final String[] doctorNameM;
    private final String[] Qualification;
    private final String[] casep_Date;
    private final String[] HospitalName;
    private final String[] DoctorID;



   public layout_recent_doctor(Activity context, String[]doctorNameL, String[]doctorNameF, String[]doctorNameM, String[]Qualification, String[]casep_Date, String[]HospitalName, String[] DoctorID){
        super(context, R.layout.layout_recent_doctor,doctorNameL);

        this.context=context;


       this.doctorNameL=doctorNameL;
       this.doctorNameF=doctorNameF;
       this.doctorNameM=doctorNameM;
       this.Qualification=Qualification;
       this.casep_Date=casep_Date;
       this.HospitalName=HospitalName;
       this.DoctorID=DoctorID;



   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_recent_doctor, null, true);

        TextView Qualificatio=(TextView)rowView.findViewById(R.id.quali);
        Qualificatio.setText(Qualification[position]);

        TextView report_date=(TextView)rowView.findViewById(R.id.casep_date);
        report_date.setText(casep_Date[position]);

        TextView report_name=(TextView)rowView.findViewById(R.id.dhospital_name);
        report_name.setText(HospitalName[position]);

        TextView doctor_name=(TextView)rowView.findViewById(R.id.doctor_name);
        doctor_name.setText(doctorNameL[position]+" "+doctorNameF[position]+" "+doctorNameM[position]+" ");

      //  new DownloadImageTask((ImageView)rowView.findViewById(R.id.profile_image))
       //         .execute("http://192.168.0.104/phrs/uploads/" + et.getText()+".jpg");
        new DownloadImageTask((CircleImageView) rowView.findViewById(R.id.profile_image))
                .execute(State_and_City.url_patient_report+DoctorID[position]+"/"+DoctorID[position]);





        return rowView;
    }

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
}
