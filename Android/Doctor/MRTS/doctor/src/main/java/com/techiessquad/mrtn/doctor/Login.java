package com.techiessquad.mrtn.doctor;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.NfcF;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

public class Login extends AppCompatActivity {

    Button LoginB;
   static EditText Username,Passwordp;

    IntentFilter[] intentFiltersArray;
    String[][] techListsArray;
    PendingIntent pendingIntent;
    NfcAdapter mNfcAdapter;
    public static String UsernameS;

    private static final String TAG_RESULTS="result";
    public static String IDS,Data;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Passwordp = (EditText) findViewById(R.id.Password);
        LoginB = (Button) findViewById(R.id.login_button);
        Username = (EditText) findViewById(R.id.login_username);
        Fresco.initialize(this);
        LoginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Password.PasswordS=Passwordp.getText().toString();
                if(Username.getText()!=null&&!Password.PasswordS.equals(""))
                {
                    UsernameS=Username.getText().toString();
                    i = new Intent(getApplicationContext(),Password.class);
                    startActivity(i);
                    Username.setText("");
                }
                else
                    Passwordp.setError("Enter Password");

            }
        });
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
          /*  Intent s = new Intent(getApplicationContext(),Login.class);
            startActivity(s);*/
            finish();
            return;
        }
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndef.addDataType("*/*");    /* Handles all MIME based dispatches.
                                       You should specify only the ones that you need. */
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        intentFiltersArray = new IntentFilter[]{ndef,};
        techListsArray = new String[][]{new String[]{NfcF.class.getName()}};
    }
    @Override
    protected void onPause() {
        super.onPause();
        mNfcAdapter.disableForegroundDispatch(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mNfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFiltersArray, techListsArray);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tagFromIntent != null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMessages =intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            Tag myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NdefMessage msg = (NdefMessage) rawMessages[0];
            Data=new String(msg.getRecords()[0].getPayload());
            State_and_City.OfflineData=Data;
            Username.setVisibility(View.INVISIBLE);
         //   Toast.makeText(this, "data "+Data, Toast.LENGTH_LONG).show();
            IDS=bytesToHex(myTag.getId());
            Password.PasswordS=Passwordp.getText().toString();
            if(!Password.PasswordS.equals("")) {
                Username.setVisibility(View.VISIBLE);
                i = new Intent(getApplicationContext(), Password.class);
                startActivity(i);
            }else
                Passwordp.setError("Enter Password");
        }
    }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
