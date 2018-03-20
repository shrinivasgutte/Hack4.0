package com.techiessquad.mrtn.doctor;

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

public class layout_recent_patient extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,Email,FirstName,HospitalName


    private final String[] LastName;
    private final String[] Contactno;
    private final String[] m_mid_name;
    private final String[] Email;
    private final String[] FirstName;
    private  final  String[] ID;
  


   public layout_recent_patient(Activity context, String[]LastName, String[]Contactno, String[]m_mid_name, String[]Email, String[]FirstName, String[] ID){
        super(context, R.layout.layout_recent_patient,LastName);

        this.context=context;


       this.LastName=LastName;
       this.Contactno=Contactno;
       this.m_mid_name=m_mid_name;
       this.Email=Email;
       this.FirstName=FirstName;
       this.ID=ID;
 



   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_recent_patient, null, true);

        TextView Emails=(TextView)rowView.findViewById(R.id.quali);
        Emails.setText(Email[position]);


        TextView report_name=(TextView)rowView.findViewById(R.id.dhospital_name);
        report_name.setText(Contactno[position]);

        TextView doctor_name=(TextView)rowView.findViewById(R.id.doctor_name);
        doctor_name.setText(LastName[position]+" "+Contactno[position]+" "+m_mid_name[position]+" ");

      //  new DownloadImageTask((ImageView)rowView.findViewById(R.id.profile_image))
       //         .execute("http://192.168.0.104/phrs/uploads/" + et.getText()+".jpg");
        new DownloadImageTask((CircleImageView) rowView.findViewById(R.id.profile_image))
                .execute(State_and_City.url_patient_report+ID[position]+"/"+ID[position]);





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
