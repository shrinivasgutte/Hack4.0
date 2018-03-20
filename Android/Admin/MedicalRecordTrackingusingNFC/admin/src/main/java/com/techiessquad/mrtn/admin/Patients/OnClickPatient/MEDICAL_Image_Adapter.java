package com.techiessquad.mrtn.admin.Patients.OnClickPatient;

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

import com.techiessquad.mrtn.admin.Patients.PatientTab_1;
import com.techiessquad.mrtn.admin.R;
import com.techiessquad.mrtn.admin.State_and_City;

import java.io.InputStream;

/**
 * Created by ajits on 17-12-2017.
 */

public class MEDICAL_Image_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] pdfName;


   public MEDICAL_Image_Adapter(Activity context, String[]pdfName){
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

        new DownloadImageTask((ImageView) rowView.findViewById(R.id.cp_img))
                .execute(State_and_City.url_patient_report+ PatientTab_1.MemberID_Temp+"/Medical/"+HomeTab_3.Date_Temp.replaceAll("\\W","") + "/"+pdfName[position].replace(" ", "%20"));






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
