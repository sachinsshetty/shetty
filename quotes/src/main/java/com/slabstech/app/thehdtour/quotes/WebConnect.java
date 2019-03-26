package com.slabstech.app.thehdtour.quotes;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;


public class WebConnect extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	String txsrc4;
	private ListView mList;
	String val[]=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(getBaseContext(), "checking internet connection..", Toast.LENGTH_SHORT).show();
		
		
		setContentView(R.layout.gotoweb);

		Button bweb = (Button) findViewById(R.id.webser);
		bweb.setOnClickListener(this);
		
		TextView alert=(TextView) findViewById(R.id.alert);

		txsrc4 = "http://tourbuddy.2itb.com/west.txt";
		
		URL textUrl;
		try {
			
	/////////////////////////////////////////
			
			textUrl = new URL(txsrc4);
			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(textUrl.openStream()));
			String StringBuffer;

		//	while ((StringBuffer = bufferReader.readLine()) != null) {
			//	String stringText = StringBuffer;
		//	}
			//bufferReader.close();

			
			
			/////////////////////////////////
	
		
	
		} catch (MalformedURLException e2) {
		
			
			alert.setText("Internet not connected");
						
						bweb.setEnabled(false);
					
			e2.printStackTrace();
		} catch (IOException e) {
			
			alert.setText("Internet not connected");
			
			bweb.setEnabled(false);
		
					
			e.printStackTrace();
		}
		
		
		
		InputStream inputStream = null;
		
		try {
			inputStream = openFileInput("list.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ByteArrayOutputStream outp = new ByteArrayOutputStream();
		int i;
		try {
			i = inputStream.read();
			while (i != -1) {
				outp.write(i);
				i = inputStream.read();
			}
			inputStream.close();
		}

		catch (IOException e) {
			e.printStackTrace();

		}

		String listVal=(String)outp.toString();
		
		
		if(listVal.equals(null))
		{
			
		}else
		{
			
		 val=listVal.split("@");
		 mList = (ListView) findViewById(R.id.list12);

			registerForContextMenu(mList);
			mList.setAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, val));
		}
	
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				
				

				if(position == 0)
				{
					Toast.makeText(getBaseContext(), "please select a valid city", Toast.LENGTH_SHORT).show();
				}
				
			if(position != 0){
				String placeName="";
				
					
					placeName=val[position];
					
					Bundle bundle1 = new Bundle();
					bundle1.putString("param1", placeName);
					String saveVal="no";
					bundle1.putString("saved", saveVal);
					Intent myintent = new Intent();
					myintent.setClass(getApplicationContext(), AudioPage.class);
					myintent.putExtras(bundle1);
					startActivityForResult(myintent, 0);
					Toast.makeText(getBaseContext(), "loading information from internet", Toast.LENGTH_SHORT).show();
					setResult(RESULT_OK, myintent);
					finish();
					
			}
			
				
				
			}});
		
		
			
			
		

	}

	@Override
	public void onClick(View v) {

		/*
		switch (v.getId()) {
		case R.id.webser:
		{
			AlertDialog.Builder builder=new AlertDialog.Builder(WebConnect.this);
			builder.setMessage(" Check places in the Internet ?");
			builder.setCancelable(false);
			
			builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					Intent myintent = new Intent();
					myintent.setClass(getApplicationContext(), WebPlaces.class);
							
					
					startActivity(myintent);
			
				}
			});
			
			
			builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int arg1) {
				dialog.cancel();
					
				}
			});
			
			
			AlertDialog alert=builder.create();
			alert.show();}
					
			
			
			
			break;
		}
		*/
	}
	

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
	
		super.onCreateContextMenu(menu, v, menuInfo);
		  MenuInflater inf=getMenuInflater();
		   inf.inflate(R.menu.savedmenu, menu);
	}
	
	 @Override
	    public boolean onContextItemSelected(MenuItem item) {
	    
	    	
	    	if(item.getItemId()== R.id.delete)
	    	{
	    		Toast.makeText(getBaseContext(), "delete",Toast.LENGTH_SHORT).show();
	    	}
	    	
	    	return super.onContextItemSelected(item);
	    }
	
}