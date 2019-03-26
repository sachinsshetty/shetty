package com.slabstech.app.thehdtour.quotes;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class WebPlaces extends TabActivity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.websr);

	
		SetupTabs();

		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.webmenu, menu);
		
		
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
if(item.getItemId()==R.id.webback)
{
	startActivity(new Intent(WebPlaces.this, Places.class ));
}
		
		return super.onOptionsItemSelected(item);
	}
	void SetupTabs() {

		TabHost host = getTabHost();

		TabHost.TabSpec spe1 = host.newTabSpec("Stories");

		spe1.setIndicator("Stories");
		Intent in1 = new Intent(this, StoryWeb.class);
		
		spe1.setContent(in1);


		host.addTab(spe1);
	}

}