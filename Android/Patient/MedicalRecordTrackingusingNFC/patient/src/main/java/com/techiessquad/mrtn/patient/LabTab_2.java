package com.techiessquad.mrtn.patient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ajits on 28-12-2017.
 */

public class LabTab_2 extends Fragment{




    static ListView listViewReport;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lab_tab2, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // Doctor.mOptionsMenu.getItem(1).setVisible(true);
        try {
            listViewReport = (ListView) view.findViewById(R.id.listViewReport);


        }catch (Exception s){
            Toast.makeText(getActivity(), "drtab : "+s, Toast.LENGTH_SHORT).show();
        }
    }

}
