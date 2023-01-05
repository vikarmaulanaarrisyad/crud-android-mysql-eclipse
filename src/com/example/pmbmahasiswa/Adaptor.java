package com.example.pmbmahasiswa;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adaptor extends BaseAdapter{
	
	// Variable
	Context context;
	LayoutInflater inflater;
	ArrayList<GetData> model;
	
	
	public Adaptor (Context context, ArrayList<GetData> model)
	{
		inflater = LayoutInflater.from(context);
		this.context = context;
		this.model = model;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return model.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return model.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		TextView nama,alamat,nomor_handphone;
		View view1 = inflater.inflate(R.layout.list_mahasiswa, null);
		if (view1 != null) {
			
			nama = (TextView) view1.findViewById(R.id.nama);
			alamat = (TextView) view1.findViewById(R.id.alamat);
			nomor_handphone = (TextView) view1.findViewById(R.id.nomor_handphone);
			
			
			// Set Value
			nama.setText(model.get(position).getNama());
			alamat.setText(model.get(position).getAlamat());
			nomor_handphone.setText(model.get(position).getNomor_handphone());
		}	
		return view1;
	}

}
