package com.slabstech.app.thehdtour.quotes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomePage extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);

		Button bStart = (Button) findViewById(R.id.start);
		bStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
			AlertDialog.Builder builder=new AlertDialog.Builder(HomePage.this);
			builder.setMessage(" Are you sure you want to exit ?");
			builder.setCancelable(false);

			builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface arg0, int arg1) {

				HomePage.this.finish();
				}
			});


			builder.setNegativeButton("No",new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int arg1) {
				dialog.cancel();

				}
			});

			AlertDialog alert=builder.create();
			alert.show();

	}

}