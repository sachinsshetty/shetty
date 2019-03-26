package com.slabstech.app.thehdtour.quotes;




import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;




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
		Button bAbout = (Button) findViewById(R.id.about);
		Button bSchedule = (Button) findViewById(R.id.schedule);

		bStart.setOnClickListener(this);

		bAbout.setOnClickListener(this);

		bSchedule.setOnClickListener(this);
		
		/*
		InputStream inputStream = null;
	
		try {
			inputStream = openFileInput("list.txt");
			
			inputStream.available();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			  OutputStream osi;
				try {
					osi = openFileOutput("list.txt",MODE_WORLD_READABLE);
					OutputStreamWriter osw=new OutputStreamWriter(osi);
			  
			//		osw.write("");
					
					
			   osw.close();
			    	
			        
				} catch (FileNotFoundException ea) {
					
					e.printStackTrace();
				} catch (IOException ea) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		} 
		*/
		
		
		
		
		
		

	}

	
	@SuppressLint("NewApi")
	public void showNotification() {

		// define sound URI, the sound to be played when there's a notification

		Uri soundUri = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		// intent triggered, you can add other intent for other actions

		// Intent intent = new Intent(MainActivity.this,
		// NotificationReceiver.class);

		Intent intent = new Intent(getApplicationContext(), Places.class);
		PendingIntent pIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, intent, 0);

		// this is it, we'll build the notification!

		// in the addAction method, if you don't want any icon, just set the
		// first param to 0

		Notification mNotification = new Notification.Builder(this)

		.setContentTitle("New Post!")

		.setContentText("Here's an awesome update for you!")

		.setSmallIcon(R.drawable.a0)

		.setContentIntent(pIntent)

		.setSound(soundUri)

		.addAction(R.drawable.a0, "View", pIntent)

		// .addAction(R.drawable.ninja, "View", pIntent)

		.addAction(0, "Remind", pIntent)

		.build();
		mNotification.flags |= Notification.FLAG_AUTO_CANCEL;

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// If you want to hide the notification after it was selected, do the
		// code below

		// myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

		notificationManager.notify(0, mNotification);

	}

	
    public void cancelNotification(int notificationId){

	 

	        if (Context.NOTIFICATION_SERVICE!=null) {

	            String ns = Context.NOTIFICATION_SERVICE;

	            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);

	            nMgr.cancel(notificationId);

	        }

	    }
	
	@Override
	public void onClick(View v)
	{

	}
	/*
	{

	
		switch (v.getId()) {
		case R.id.start:
	

			
			
			Intent myintent1 = new Intent();
			
			//Notification code added heres
			myintent1.setClass(getApplicationContext(), Places.class);
			startActivity(myintent1);
	//		showNotification();
			break;

		case R.id.about:
			
//			cancelNotification(0);

			Intent myintent2 = new Intent();
			myintent2.setClass(getApplicationContext(), About.class);
			startActivity(myintent2);
		
			break;

		case R.id.schedule:
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



			Intent myintent3 = new Intent();
			myintent3.setClass(getApplicationContext(), About.class);
			startActivity(myintent3);
		
			
		}
			break;

		}

	}
	*/
}