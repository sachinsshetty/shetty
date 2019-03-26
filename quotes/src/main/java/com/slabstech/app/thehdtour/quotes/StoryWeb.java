package com.slabstech.app.thehdtour.quotes;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StoryWeb extends Activity {
	/** Called when the activity is first created. */

	private ListView mList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.northlist);

		String txsrc = "http://tourbuddy.2itb.com/north.txt";
		// textMsg = (TextView)findViewById(R.id.textmsg);
		String stringText = "";
		URL textUrl;
		try {
			textUrl = new URL(txsrc);
			BufferedReader bufferReader = new BufferedReader(
					new InputStreamReader(textUrl.openStream()));
			String StringBuffer;

			while ((StringBuffer = bufferReader.readLine()) != null) {
				stringText += StringBuffer;
			}
			bufferReader.close();

			// textMsg.setText(stringText);
		} catch (MalformedURLException e) {
			e.printStackTrace(); // textMsg.setText(e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			// textMsg.setText(e.toString());
			//
			// txp.setText("Internet not connected");
		}

		final String north[] = stringText.split("-");

		mList = (ListView) findViewById(R.id.list);
		mList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, north));

	//	mList.setBackgroundResource(R.color.blue);

		mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String val = "northpl/" + north[position];


				
				String txsrc = "http://tourbuddy.2itb.com/" + val + ".txt";
				String txtv = "";
				
				URL textUrl;
				try {
					textUrl = new URL(txsrc);
					BufferedReader bufferReader = new BufferedReader(
							new InputStreamReader(textUrl.openStream()));
					String StringBuffer;

					while ((StringBuffer = bufferReader.readLine()) != null) {
					txtv += StringBuffer;
					}
					bufferReader.close();



				} catch (MalformedURLException e) {
					e.printStackTrace();

				} catch (IOException e) {
					e.printStackTrace();
				}


				OutputStream os;
				try {
					os = openFileOutput( "temp.txt", MODE_WORLD_READABLE);
					OutputStreamWriter osw = new OutputStreamWriter(os);
					osw.write(txtv);
					osw.close();
					

				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
				String par=north[position];
				Bundle bundle1 = new Bundle();
				bundle1.putString("param1", par);
				String saveVal="yes";
				bundle1.putString("saved", saveVal);
				Intent myintent = new Intent();
				myintent.setClass(getApplicationContext(), AudioPage.class);
				myintent.putExtras(bundle1);
				startActivityForResult(myintent, 0);
				setResult(RESULT_OK, myintent);
				finish();

			}
		});

	}
}