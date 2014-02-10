package com.example.openglandroidappexample;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button start;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.start);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		start.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
					Class<?> ourClass = Class.forName("com.example.openglandroidappexample." + "GLExample" );
					Intent ourIntent = new Intent(MainActivity.this, ourClass);
					startActivity(ourIntent);
				}
				catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}
		});
		
		return true;
	}
}
