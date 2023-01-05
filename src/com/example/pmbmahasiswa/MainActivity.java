package com.example.pmbmahasiswa;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

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
