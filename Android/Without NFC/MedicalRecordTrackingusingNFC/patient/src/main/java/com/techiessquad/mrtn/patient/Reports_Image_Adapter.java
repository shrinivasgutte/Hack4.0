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

/**
 * Created by ajits on 17-12-2017.
 */

public class Reports_Image_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] pdfName;


   public Reports_Image_Adapter(Activity context, String[]pdfName){
        super(context, R.layout.layout_casepaper_image,pdfName);

        this.context=context;
        this.pdfName=pdfName;




    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_casepaper_image, null, true);

        TextView f_name=(TextView)rowView.findViewById(R.id.fname);
        f_name.setText(pdfName[position]);

     //   new DownloadImageTask((ImageView) rowView.findViewById(R.id.cp_img))
      //          .execute(State_and_City.url+"Images/"+State_and_City.patientNameSend_server+"/Reports/"+HomeTab_2.reportDate_Temp.replace("/", "") + "/"+pdfName[position].replace(" ", "%20"));

        new DownloadImageTask((ImageView) rowView.findViewById(R.id.cp_img))
                .execute(State_and_City.url_patient_report+ State_and_City.patientNameSend_server+"/Reports/"+HomeTab_2.reportDate_Temp.replaceAll("\\W","") + "/"+pdfName[position].replace(" ", "%20"));






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
