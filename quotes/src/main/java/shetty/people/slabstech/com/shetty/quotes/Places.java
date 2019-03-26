package shetty.people.slabstech.com.shetty.quotes;


import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Places extends TabActivity {
	/** Called when the activity is first created. */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		
		cancelNotification(0);
		
		
		SetupTabs();
	}

	
	public void cancelNotification(int notificationId){

		 

        if (Context.NOTIFICATION_SERVICE!=null) {

            String ns = Context.NOTIFICATION_SERVICE;

            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);

            nMgr.cancel(notificationId);

        }

    }

	void SetupTabs() {

		TabHost host = getTabHost();

		TabHost.TabSpec spec1 = host.newTabSpec("Narrator");
		Intent in1 = new Intent(this, Narration.class);
		spec1.setIndicator("Story Narrator");
		spec1.setContent(in1);

		TabHost.TabSpec spec2 = host.newTabSpec("Slate");
		Intent in2 = new Intent(this, VSlate.class);

		spec2.setIndicator("Virtual Slate");
		spec2.setContent(in2);

		TabHost.TabSpec spec3 = host.newTabSpec("Pronounciator");
	Intent in3 = new Intent(this, Pronounciator.class);
		spec3.setIndicator("Pronounciator");
		spec3.setContent(in3);


		TabHost.TabSpec spec5 = host.newTabSpec("Websearch");
		Intent in5 = new Intent(this, WebConnect.class);
		spec5.setIndicator("WebSearch");
		spec5.setContent(in5);

		host.addTab(spec2);
		host.addTab(spec1);
		host.addTab(spec3);

		host.addTab(spec5);
	}

}