package shetty.people.slabstech.com.shetty.quotes;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationReciever extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		TextView tx = (TextView) findViewById(R.id.tex);
		// ///////////
		Bundle bundle = this.getIntent().getExtras();
		String para = bundle.getString("param1");

	
		
				
		tx.setText(para);
		showNotification();
		
		
		
		

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

	
}