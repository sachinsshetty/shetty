package com.slabstech.app.thehdtour.quotes;


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

		String type = getIntent().getExtras().getString("page");
		
		SetupTabs(type);
	}

	
	public void cancelNotification(int notificationId){

		 

        if (Context.NOTIFICATION_SERVICE!=null) {

            String ns = Context.NOTIFICATION_SERVICE;

            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);

            nMgr.cancel(notificationId);

        }

    }

	void SetupTabs(String type) {

		TabHost host = getTabHost();

		TabHost.TabSpec spec1 = host.newTabSpec("experience");
		Intent in1 = new Intent(this, Narration.class);
		spec1.setIndicator("Experience");
		spec1.setContent(in1);

		TabHost.TabSpec spec2 = host.newTabSpec("partner");
		Intent in2 = new Intent(this, Partner.class);

		spec2.setIndicator("Partners");
		spec2.setContent(in2);

		if(type.equalsIgnoreCase("experience"))
			host.addTab(spec1);
		else
			host.addTab(spec2);

	}

}