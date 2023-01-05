package com.example.pmbmahasiswa;

//import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Simpan_data extends Activity {
	
	EditText nama, alamat, nomor_handphone;
	Button simpan_data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simpan_data);
		
		nama = (EditText) findViewById(R.id.nama);
		alamat = (EditText) findViewById(R.id.alamat);
		nomor_handphone = (EditText) findViewById(R.id.nomor_handphone);
		
		simpan_data = (Button) findViewById(R.id.simpan_data);
		simpan_data.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				if (nama.getText().toString().length() == 0) {
					Toast.makeText(Simpan_data.this, "Nama wajib diisi", Toast.LENGTH_SHORT).show();
				}
				
				if (alamat.getText().toString().length() == 0) {
					Toast.makeText(Simpan_data.this, "Alamat wajib diisi", Toast.LENGTH_SHORT).show();
				}
				
				if (nomor_handphone.getText().toString().length() == 0) {
					Toast.makeText(Simpan_data.this, "Nomor handphone wajib diisi", Toast.LENGTH_SHORT).show();
				}
				
				String url = new Configurasi().baseUrl()+"simpan.php";
				
				StringRequest stringRequest = new StringRequest(Method.POST, url, new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						
						try {
							JSONObject jsonObject = new JSONObject(response);
							String status = jsonObject.getString("status");
							
							if (status.equals("Data berhasil disimpan")) {
								
								AlertDialog tampil= new AlertDialog.Builder(Simpan_data.this).create();
								tampil.setTitle("Sukses");
								tampil.setMessage("Data Berhasil Disimpan");
								tampil.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										
										dialog.cancel();
										
									}
									
								});
								tampil.show();
							}
							
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
									
						Toast.makeText(Simpan_data.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();	
					}
					
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Toast.makeText(Simpan_data.this, "Terjadi kesalahan data", Toast.LENGTH_SHORT).show();	
					}
					
				}){
					protected Map<String, String> getParams() throws AuthFailureError {
						HashMap<String,String> form = new HashMap<String,String>();
						form.put("nama", nama.getText().toString());
						form.put("alamat", alamat.getText().toString());
						form.put("nomor_handphone", nomor_handphone.getText().toString());
						return form;
						
					}
				};
				
				RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
				requestQueue.add(stringRequest);
				
			}
		});
		
	}
}
