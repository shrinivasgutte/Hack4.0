package com.techiessquad.mrtn.admin.Patients;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.techiessquad.mrtn.admin.R;

/**
 * Created by ajits on 28-12-2017.
 */

public class PatientTab_2 extends Fragment{




    static ListView listViewReport;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.patient_tab2, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Doctor.mOptionsMenu.getItem(1).setVisible(true);
        try {
            listViewReport = (ListView) view.findViewById(R.id.listViewReport);

         //   getData();

        }catch (Exception s){
            Toast.makeText(getActivity(), "drtab : "+s, Toast.LENGTH_SHORT).show();
        }
    }





}
