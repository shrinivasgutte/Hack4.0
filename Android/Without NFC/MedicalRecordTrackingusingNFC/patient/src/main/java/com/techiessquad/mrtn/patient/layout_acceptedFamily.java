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

public class layout_acceptedFamily extends ArrayAdapter<String> {

    private final Activity context;

   // D_Last_name, D_first_name,m_ContactNo,MemberID,HospitalName
    
   
    private final String[] m_Last_name;
    private final String[] m_first_name;
    private final String[] m_mid_name;
    private final String[] m_ContactNo;
    private final String[] MemberID;




   public layout_acceptedFamily(Activity context, String[]m_Last_name, String[]m_first_name, String[]m_mid_name, String[]m_ContactNo, String[]MemberID){
        super(context, R.layout.layout_accepted_family,m_Last_name);

        this.context=context;


       this.m_Last_name=m_Last_name;
       this.m_first_name=m_first_name;
       this.m_mid_name=m_mid_name;
       this.m_ContactNo=m_ContactNo;
       this.MemberID=MemberID;



   }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_accepted_family, null, true);

        TextView user_name=(TextView)rowView.findViewById(R.id.user_name);
        user_name.setText(m_Last_name[position]+" "+m_first_name[position]+" "+m_mid_name[position]+" ");

        TextView contact_no=(TextView)rowView.findViewById(R.id.contact_no);
        contact_no.setText(m_ContactNo[position]);


        new DownloadImageTask((CircleImageView) rowView.findViewById(R.id.profile_image))
                .execute(State_and_City.url_patient_report+MemberID[position]+"/"+MemberID[position]);




        return rowView;
    }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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

//loop and download image and put it in this list
            //  imagesList.add(result);
            bmImage.setImageBitmap(result);
            //Toast.makeText(demo.this, "Error="+res, Toast.LENGTH_SHORT).show();
        }
    }
}
