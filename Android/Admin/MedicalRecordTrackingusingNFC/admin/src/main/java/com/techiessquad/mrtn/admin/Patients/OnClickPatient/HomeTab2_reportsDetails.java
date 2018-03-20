package com.techiessquad.mrtn.admin.Patients.OnClickPatient;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.techiessquad.mrtn.admin.ImageOverlayView;
import com.techiessquad.mrtn.admin.Patients.PatientTab_1;
import com.techiessquad.mrtn.admin.R;
import com.techiessquad.mrtn.admin.State_and_City;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeTab2_reportsDetails extends AppCompatActivity {
    public static String CP_ImageName[],pdfUrl[];
    private ArrayList<String> files_on_server = new ArrayList<>();

    private ImageOverlayView overlayView;

    GridView grid;
    private ProgressDialog progress;
    final Context context = this;
    public static String myJSON;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    public  static  int cnt,cnt1;
    TextView description_type,description_date,lab_name,doctor_name,description,report_name;
    //imgbrowser
    public  String[] posters;
    String[] descriptions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tab2_reports_ditails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            setTitle("Reports");
                toolbar = (Toolbar)findViewById(R.id.toolbar);

            grid =(GridView)findViewById(R.id.grid);
            getData();

        description_type =(TextView)findViewById(R.id.description_type);
                description_date =(TextView)findViewById(R.id.description_date);
            lab_name =(TextView)findViewById(R.id.lab_name);
                doctor_name =(TextView)findViewById(R.id.doctor_name);
            description =(TextView)findViewById(R.id.description);
            report_name =(TextView)findViewById(R.id.report_name);


            description_type.setText(HomeTab_2.reportType_Temp);
            lab_name.setText(HomeTab_2.reportLabName_Temp);
                doctor_name.setText(HomeTab_2.reportDoctorNameF_Temp+" "+HomeTab_2.reportDoctorNameM_Temp+" "+HomeTab_2.reportDoctorNameL_Temp);
                description.setText(HomeTab_2.reportDescription_Temp);
                description_date.setText(HomeTab_2.reportDate_Temp);
            report_name.setText(HomeTab_2.reportName_Temp);
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    SimpleDraweeView drawee = (SimpleDraweeView) v.findViewById(R.id.description_type);
                    // initDrawee(drawee, position);
                    //   send_ditails= textView.getText().toString();

                    //   init(position , v);


                    showPicker(position);
                 }
            });
        }catch (Exception s){
            Toast.makeText(this, "listClick 2: "+s, Toast.LENGTH_SHORT).show();
        }

    }

    protected void showPicker(int startPosition) {
        ImageViewer.Builder builder = new ImageViewer.Builder<>(this, posters)
      //  new ImageViewer.Builder<>(this, posters)
                .setStartPosition(startPosition)
                .setImageChangeListener(getImageChangeListener())


                .setContainerPadding(this, R.dimen.padding)
                .setImageMargin(this, R.dimen.image_margin)

                .setOverlayView(overlayView);


        if (1==1) {
          //  overlayView = new ImageOverlayView(this);
            overlayView =new ImageOverlayView(this);
            builder.setOverlayView(overlayView);
            builder.setImageChangeListener(getImageChangeListener());
        }
                builder.show();



    }

    private ImageViewer.OnImageChangeListener getImageChangeListener() {
        return new ImageViewer.OnImageChangeListener() {
            @Override
            public void onImageChange(int position) {
                String url = posters[position];
                overlayView.setShareText(url);
                overlayView.setDescription(descriptions[position]);
            }
        };
    }




    //#######################################################################################3 grt data $$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    public void getData(){
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());

             //   HttpPost httppost = new HttpPost(State_and_City.url_patient_report+"Images/"+ PatientTab_1.MemberID_Temp+"/Reports/"+HomeTab_2.reportDate_Temp.replace("/", "")+"/script.php?list_files");
                HttpPost httppost = new HttpPost(State_and_City.url_patient_report+"script.php?p_id="+ PatientTab_1.MemberID_Temp+"&report_type=Reports&date="+HomeTab_2.reportDate_Temp.replaceAll("\\W",""));

                /* HttpPost httppost = new HttpPost("http://192.168.0.104/Paper/"+
                        Url.scheme.replace(" ","%20")+"/"+
                        Url.diploma_g_dipartment.replace(" ","%20")+"/"+
                        Url.diploma_g_sem.replace(" ","%20")+"/"+
                        subjectNameSend_server.replace(" ","%20")+"/script.php?list_files&p_id=");
*/
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
                    Toast.makeText(context, "dd", Toast.LENGTH_SHORT).show();
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
                    State_and_City.homeTabFlg=2;
                    // Toast.makeText(context, "k "+myJSON, Toast.LENGTH_SHORT).show();
                    showList();

                }catch (Exception f){

                    Toast.makeText(getApplicationContext(), "error recive ::"+f, Toast.LENGTH_SHORT).show();
                }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
    }
    protected void showList(){
        try {
            cnt=0;
            JSONArray array = new JSONArray(myJSON);
            for (int i = 0; i <array.length(); i++){
                cnt++;
            }
            CP_ImageName = new String[cnt];
            posters = new String[cnt];
            descriptions = new String[cnt];
            for (int i = 0; i <array.length(); i++){
                String file_name = array.getString(i);
                CP_ImageName[i]=file_name;
 //(State_and_City.url_patient_report+"Images/script.php?p_id="+ PatientTab_1.MemberID_Temp+"&report_type=Reports&date="+HomeTab_2.reportDate_Temp.replace("/", ""));

                posters[i]=State_and_City.url_patient_report+ PatientTab_1.MemberID_Temp+"/Reports/"+HomeTab_2.reportDate_Temp.replaceAll("\\W","")+"/"+file_name;
                descriptions[i]= "Casepaper : "+HomeTab_2.reportDate_Temp+"\n"+CP_ImageName[i];
                if(files_on_server.indexOf(file_name) == -1)
                    files_on_server.add(file_name);
            }
            /*
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            cnt=0;
            cnt1=0;


            for(int i=0;i<peoples.length();i++){
                cnt++;
            }
            CP_ImageName = new String[cnt];
            pdfUrl = new String[cnt];


            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);

                CP_ImageName[cnt1]=c.getString("CP_ImageName");
                pdfUrl[cnt1]=c.getString("pdfUrl");
                cnt1++;
            }*/


            Reports_Image_Adapter a = new Reports_Image_Adapter(this,CP_ImageName);
            grid.setAdapter(a);

            if(cnt==0) {
                Toast.makeText(this, "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
