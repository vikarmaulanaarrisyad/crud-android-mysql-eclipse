package com.example.pmbmahasiswa;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pmbmahasiswa.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

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

public class MainActivity extends Activity {
	
	ListView listView;
	ArrayList<GetData> model;
	Adaptor adaptor;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView) findViewById(R.id.listView1);
        load_data();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
        	// Disini Masih Errors, karena pluggin tidak ada
        	
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            	PopupMenu popupMenu = new PopupMenu(getApplicationContext(),view);
            	popupMenu.getMenuInflater().inflate(R.menu.menu_opsi, popupMenu.getMenu());
            	popupMenu.show();	
            	
            	
            	popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem menuItem) {
						// TODO Auto-generated method stub
						if (menuItem.getItemId() == R.id.edit) {
							Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
						} else if (menuItem.getItemId() == R.id.hapus) {
							Toast.makeText(MainActivity.this, "Hapus", Toast.LENGTH_SHORT).show();
						}
						return false;
					}
				});
            }
        });
    }
           
    void load_data() {
    	String url = new Configurasi().baseUrl()+"tampil_data.php";
    
    	StringRequest request = new StringRequest(Method.GET, url, new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				
				try {
					JSONObject jsonObject = new JSONObject(response);
					
					JSONArray jsonArray = jsonObject.getJSONArray("data");
					model = new ArrayList<GetData> ();
					for (int i = 0; i <= jsonArray.length(); i++) {
						
						JSONObject getData = jsonArray.getJSONObject(i);
						model.add(new GetData(
								getData.getString("nama"), 
								getData.getString("alamat"),  
								getData.getString("nomor_handphone") 
							));	
					}
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("ERROR", e.getMessage());
				}
				
		        adaptor = new Adaptor(MainActivity.this, model);
		        listView.setAdapter(adaptor);
			}
    		
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "Terjadi kesalahan data", Toast.LENGTH_SHORT).show();	
			}
			
		});
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(request);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
