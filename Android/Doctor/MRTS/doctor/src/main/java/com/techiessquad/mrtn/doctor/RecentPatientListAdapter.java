package com.techiessquad.mrtn.doctor;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

public class RecentPatientListAdapter extends ArrayAdapter<String> {

	private final Activity context;

	private final String[] name,username,dob,mobile;

	public RecentPatientListAdapter(Activity context, String[] name, String[] username, String[] dob, String[] mobile){
		super(context, R.layout.list_recent_patient,name);
		this.context=context;
		this.name=name;
		this.username=username;
		this.dob=dob;
		this.mobile=mobile;
	}
	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.list_recent_patient, null, true);
		CircularImageView image=(CircularImageView)rowView.findViewById(R.id.image);
		TextView Name=(TextView)rowView.findViewById(R.id.Name);
		Name.setText(name[position]);
		TextView Username=(TextView)rowView.findViewById(R.id.username);
		Username.setText(username[position]);
		TextView DOB=(TextView)rowView.findViewById(R.id.DOB);
		DOB.setText(dob[position]);
		TextView Mobile=(TextView)rowView.findViewById(R.id.Mobile);
		Mobile.setText(this.mobile[position]);
		return rowView;
	}
}
