package com.techiessquad.mrtn.doctor.PatientProfileDitails;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.stfalcon.frescoimageviewer.ImageViewer;
import com.techiessquad.mrtn.doctor.ImageOverlayView;
import com.techiessquad.mrtn.doctor.R;
import com.techiessquad.mrtn.doctor.State_and_City;
import com.techiessquad.mrtn.doctor.UploadCasePaper;


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

//import com.techiessquad.mrtn.admin.ImageBrowser.common.styled.StyledViewActivity;


public class HomeTab_1_CasePaperDitails extends AppCompatActivity {
    public static String CP_ImageName[],pdfUrl[];
    private ArrayList<String> files_on_server = new ArrayList<>();

    private ImageOverlayView overlayView;

    GridView grid;
    ProgressBar progressBar;
    private ProgressDialog progress;
    final Context context = this;
    public static String myJSON;
    private static final String TAG_RESULTS="result";
    JSONArray peoples = null;
    public  static  int cnt,cnt1;
    TextView description_type,description_date,hospital_name,doctor_name,description;
    //imgbrowser
     public  String[] posters;
     String[] descriptions;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_tab_1__case_paper_ditails);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("CasePaper");
        try{
            toolbar = (Toolbar)findViewById(R.id.toolbar);


          //  getSupportActionBar().setBackgroundDrawable(getDrawable(getResources().getColor(R.color.colorPrimary_2)));
            grid =(GridView)findViewById(R.id.grid);
            progressBar= (ProgressBar)findViewById(R.id.progressBar_cyclic);
            progressBar.setVisibility(View.VISIBLE);


        description_type =(TextView)findViewById(R.id.description_type);
                description_date =(TextView)findViewById(R.id.description_date);
                hospital_name =(TextView)findViewById(R.id.hospital_name);
                doctor_name =(TextView)findViewById(R.id.doctor_name);
                description =(TextView)findViewById(R.id.description);

                getData();


                description_type.setText(HomeTab_1.descriptionType_Temp);
                hospital_name.setText(HomeTab_1.hospitalName_Temp);
                doctor_name.setText(HomeTab_1.doctorNameF_Temp+" "+HomeTab_1.doctorNameM_Temp+" "+HomeTab_1.doctorNameL_Temp);
                description.setText(HomeTab_1.description_Temp);
                description_date.setText(HomeTab_1.descriptionDate_Temp);

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
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    State_and_City.casepaperFLG=1;
                    State_and_City.STR_reportsUploadFLG="Casepaper";
                    Intent s = new Intent(getApplicationContext(),UploadCasePaper.class);
                    startActivity(s);
                }
            });
        }catch (Exception s){
            Toast.makeText(this, "listClick : "+s, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.styling_options_menu, menu);
        return super.onCreateOptionsMenu(menu);
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

               // HttpPost httppost = new HttpPost(State_and_City.url_patient_report+"Images/"+ PatientTab_1.MemberID_Temp+"/Casepaper/"+HomeTab_1.descriptionDate_Temp.replace("/", "")+"/script.php?list_files");
                HttpPost httppost = new HttpPost(State_and_City.url_patient_report+"script.php?p_id="+ State_and_City.PatientIDstr+"&report_type=Casepaper&date="+HomeTab_1.descriptionDate_Temp.replaceAll("\\W",""));
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
                //    Toast.makeText(context, "dd", Toast.LENGTH_SHORT).show();
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
                   // State_and_City.homeTabFlg=1;
                    // Toast.makeText(context, "k "+myJSON, Toast.LENGTH_SHORT).show();
                    showList();

                }catch (Exception f){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Network :1:", Toast.LENGTH_SHORT).show();
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

                posters[i]=State_and_City.url_patient_report+ State_and_City.PatientIDstr+"/Casepaper/"+HomeTab_1.descriptionDate_Temp.replaceAll("\\W","")+"/"+file_name;
                descriptions[i]= "Casepaper : "+HomeTab_1.descriptionDate_Temp+"\n"+CP_ImageName[i];
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
            progressBar.setVisibility(View.INVISIBLE);


            Casepaper_Image_Adapter a = new Casepaper_Image_Adapter(this,CP_ImageName);
            grid.setAdapter(a);

            if(cnt==0) {
                Toast.makeText(HomeTab_1_CasePaperDitails.this, "No Products Available", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
  /*  Bitmap[] mThumbIds =new Bitmap[10];//for exapmle 10
    URL url = new URL("Your URL");//image url is stored here
    mThumbIds[0]= BitmapFactory.decodeStream(url.openConnection().getInputStream());//this line help to get the image(for given url) and store it in BitMapArray
*/
}
