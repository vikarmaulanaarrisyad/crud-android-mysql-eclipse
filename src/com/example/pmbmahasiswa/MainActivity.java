package com.example.pmbmahasiswa;

import java.util.ArrayList;

import com.example.pmbmahasiswa.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ListView listView;
	Adaptor adaptor;
	ArrayList<GetData> model;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView) findViewById(R.id.listView1);
        load_data();
        Adaptor adaptor = new Adaptor(getApplicationContext(), model);
        
        listView.setAdapter(adaptor);
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
    	
    	model = new ArrayList<GetData>();
    	for (int i = 0; i <= 30; i++) {
    		model.add(new GetData("Vikar Maulana " +i, "Bojong", "0878"));
		}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
