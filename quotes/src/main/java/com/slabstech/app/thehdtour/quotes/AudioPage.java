package com.slabstech.app.thehdtour.quotes;




import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AudioPage extends Activity implements OnClickListener,
		TextToSpeech.OnInitListener {
	/** Called when the activity is first created. */
	String word = null;
	int story = 0;
	private TextToSpeech Tts = null;

	private static final int HELP_SPEED =8888;
	private static final int HELP_LANG = 7777;
	private static final int LANGUAGE_MENU = 0;
	private static final int SPEED_MENU = 1000;
	private static final int SAVED_MENU = 1500;
	private ArrayList<Locale> availableLocales = null;

	String cityName="";
	String txt = null;
	String value = null;
	String saved="";
	String spkval;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		availableLocales = new ArrayList<Locale>();
		Tts = new TextToSpeech(this, this // TextToSpeech.OnInitListener
		);
		Bundle bundle = this.getIntent().getExtras();

		final int placeId = bundle.getInt("keyId");


		String pageType = bundle.getString("pageType");

		//cityName=param1;
		
		saved=bundle.getString("saved");
		setContentView(R.layout.audiopage);


		TextView tx = (TextView) findViewById(R.id.title);

		android.content.res.Resources res = getResources();

		String[] pageList = null ;
		String[] fileName = null ;



		if(pageType.equalsIgnoreCase("Partner")) {
			pageList = res.getStringArray(R.array.partnerList);
			fileName = res.getStringArray(R.array.partnerFiles);
		}else {

			pageList = res.getStringArray(R.array.experienceList);
			fileName = res.getStringArray(R.array.experienceFiles);

		}

		txt = pageList[placeId];

		tx.setText(pageList[placeId]);


		Button b1 = (Button) findViewById(R.id.speak);
		Button b2 = (Button) findViewById(R.id.stop);
		
		word = pageList[placeId];
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				speak1(placeId);
			}

		});
	
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Tts.stop();
				
			}
		});

		InputStream inputStream = null;


		ByteArrayOutputStream outp = new ByteArrayOutputStream();
		int i;

		try {
			inputStream = getResources().openRawResource(
					getResources().getIdentifier(fileName[placeId],
							"raw", getPackageName()));

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

		spkval = (String) outp.toString();

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.audiomenu, menu);
		
		String menutitle = "";

		
		if(saved.equals("yes")){
		SubMenu savedMenu=menu.addSubMenu(0,SAVED_MENU,Menu.NONE,"save");
		

		}
		
		
		SubMenu lanugageMenu = menu.addSubMenu(0, LANGUAGE_MENU, Menu.NONE,
				"Language");

		lanugageMenu.setHeaderTitle("Select Language");

		for (int index = 0; index < availableLocales.size(); ++index) {
			menutitle = availableLocales.get(index).getDisplayLanguage() + " ("
					+ availableLocales.get(index).getDisplayCountry() + ")";

			lanugageMenu
					.add(0, LANGUAGE_MENU + index + 1, Menu.NONE, menutitle);
		}

		SubMenu speedMenu = menu.addSubMenu(0, SPEED_MENU, Menu.NONE, "Speed");

		speedMenu.add(0, SPEED_MENU + 1, Menu.NONE, "Very Slow");
		speedMenu.add(0, SPEED_MENU + 2, Menu.NONE, "Slow");
		speedMenu.add(0, SPEED_MENU + 3, Menu.NONE, "Normal");
		speedMenu.add(0, SPEED_MENU + 4, Menu.NONE, "Fast");
		speedMenu.add(0, SPEED_MENU + 5, Menu.NONE, "Very Fast");
		
		
		return result;
	}

	public void onClick(View v) {


	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = true;
		int itemId = item.getItemId();

		
		
	if(itemId ==SAVED_MENU){ 
			
			
			  OutputStream os;
				try {
					os = openFileOutput(cityName+".txt",MODE_WORLD_READABLE);
					OutputStreamWriter osw=new OutputStreamWriter(os);
			   
					osw.write(value);
			    	osw.close();
			    	
			        
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
				
			
				  OutputStream osi;
					try {
						osi = openFileOutput("list.txt",MODE_WORLD_READABLE);
						OutputStreamWriter osw=new OutputStreamWriter(osi);
				  
						osw.write(listVal+"@"+cityName);
						
						
				   osw.close();
				    	
				        
					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
					
							
					
				

			
			
		}else
			if (itemId== R.id.back){
				startActivity(new Intent(getApplicationContext(), Places.class));
			}
			else if (itemId > SPEED_MENU) {
			setTextToSpeechRate(itemId - SPEED_MENU - 1);
		} else if (itemId > LANGUAGE_MENU && itemId < SPEED_MENU) {
			setTextToSpeechLocale(itemId - LANGUAGE_MENU - 1);
		}

		return result;
	}

	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		boolean result = true;

		long id = info.id - Menu.FIRST;

		if(item.getItemId()== HELP_LANG)
		{
		
			Dialog d=new Dialog(AudioPage.this);
			
			d.setContentView(R.layout.dial_lang);
			d.setTitle("Change Language");
			d.show();
			
		}else if(item.getItemId() == HELP_SPEED)
		{
			Toast.makeText(getBaseContext(), "speed", Toast.LENGTH_SHORT).show();
		} 
		
		Log.i("TTSDemo", "MenuItem Selectd: " + id);

		return result;
	}

	@Override
	protected void onDestroy() {
		Tts.shutdown();

		super.onDestroy();
	}

	private void setTextToSpeechRate(int index) {
		float rate = (float) 1.0;

		switch (index) {
		case 0:
			rate = (float) 0.1;
			break;
		case 1:
			rate = (float) 0.5;
			break;
		case 2:
			rate = (float) 1.0;
			break;
		case 3:
			rate = (float) 1.5;
			break;
		case 4:
			rate = (float) 2.0;
			break;
		}

		Log.i("TTSDemo", "SpeechRate: " + rate + "(" + index + ")");

		Tts.setSpeechRate(rate);
	}

	private void setTextToSpeechLocale(int index) {
		Tts.setLanguage(availableLocales.get(index));

		Log.i("TTSDemo", "Language: "
				+ availableLocales.get(index).getDisplayLanguage() + " ("
				+ availableLocales.get(index).getDisplayCountry() + ")");
	}

	private void EnumerateAvailableLanguages() {
		Locale locales[] = Locale.getAvailableLocales();

		for (int index = 0; index < locales.length; ++index) {
			if (TextToSpeech.LANG_COUNTRY_AVAILABLE == Tts
					.isLanguageAvailable(locales[index])) {
				Log.i("TTSDemo", locales[index].getDisplayLanguage() + " ("
						+ locales[index].getDisplayCountry() + ")");

				availableLocales.add(locales[index]);
			}
		}
	}

	public void onInit(int status) {

		boolean isAvailable = (TextToSpeech.SUCCESS == status);

		if (isAvailable) {
			EnumerateAvailableLanguages();

			// tts.setOnUtteranceCompletedListener(onUtteranceCompleted);

			// ((Button)findViewById(R.id.button_speak)).setEnabled(true);
		}
	}

	private void speak1(int city) {
		// TODO Auto-generated method stub
		TextView info = (TextView) findViewById(R.id.info);
		info.setText(spkval);
		Tts.speak(spkval, TextToSpeech.QUEUE_FLUSH, // Drop all pending
														// entries in the
														// playback queue.
				null);
	}

}