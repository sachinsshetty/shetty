package shetty.people.slabstech.com.shetty.quotes;




import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Locale;



import android.app.Dialog;
import android.app.TabActivity;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class AudioPage extends TabActivity implements OnClickListener,
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
	String spkval[] = null;
	String hosp[];
	String placs[];
	String hotel[];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		availableLocales = new ArrayList<Locale>();
		Tts = new TextToSpeech(this, this // TextToSpeech.OnInitListener
		);
		Bundle bundle = this.getIntent().getExtras();
		String param1 = bundle.getString("param1");
		
		cityName=param1;
		
		saved=bundle.getString("saved");
		setContentView(R.layout.audiopage);
		txt = (String) param1;
		TextView tx = (TextView) findViewById(R.id.title);
		if (param1.equals("one")) {
			story = 1;
			tx.setText("The Lion and the Mouse");

		} else if (param1.equals("two")) {
			story = 2;
			tx.setText("The Goose with the Golden Eggs");
		} else if (param1.equals("three")) {
			story = 3;
			tx.setText("The Hare and the Tortoise");
		} else if (param1.equals("four")) {
			story = 4;
			tx.setText("The Fox and the Stork");
		} else if (param1.equals("five")) {
			story = 5;
			tx.setText("The Monkey and the Dolphin");
		} else if (param1.equals("six")) {
			story = 6;
			tx.setText("Bundle of sticks");
		} else if (param1.equals("seven")) {
			story = 7;
			tx.setText("The Thirsty Crow");
		}  else {
			story = 100;
			tx.setText(param1);
		}

		Button b1 = (Button) findViewById(R.id.speak);
		Button b2 = (Button) findViewById(R.id.stop);
		
		
	
		
		word = param1.toString();
		b1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				speak1(story);
			}

		});
	
		b2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Tts.stop();
				
				

			}
		});
		////////////////

	

		// //////////////////////////

		InputStream inputStream = null;

		switch (story) {
		case 1:
			inputStream = getResources().openRawResource(R.raw.one);
			break;

		case 2:
		inputStream = getResources().openRawResource(R.raw.two);

			break;

		case 3:
			inputStream = getResources().openRawResource(R.raw.three);
			break;

		case 4:
			inputStream = getResources().openRawResource(R.raw.four);
			break;

		case 5:
		inputStream = getResources().openRawResource(R.raw.five);

			break;

		case 6:
			inputStream = getResources().openRawResource(R.raw.six);
			break;

		case 7:
			inputStream = getResources().openRawResource(R.raw.seven);
			break;

				case 100:

			try {
				inputStream = openFileInput("temp.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;
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

		value = (String) outp.toString();

		
		hosp= value.split("&");
		

		placs= value.split("%");
		hotel= value.split("@");
		
		
		spkval = value.split("#");

		
		
		SetupTabs();
		
			}
	
	void SetupTabs() {

		Bundle bundle1 = new Bundle();
		Bundle bundle2 = new Bundle();
		Bundle bundle3 = new Bundle();

		bundle1.putString("param1", hosp[1]);
	bundle2.putString("param1", hotel[1]);
		bundle3.putString("param1",placs[1]);

		TabHost host = getTabHost();
TabHost.TabSpec spec1 = host.newTabSpec("Moral");

		
		Intent in1 = new Intent(this, Info.class);
		spec1.setIndicator("Moral");
		in1.putExtras(bundle1);
		spec1.setContent(in1);

		

		TabHost.TabSpec spec2 = host.newTabSpec("Hotels");
		Intent in2 = new Intent(this, Info.class);
		in2.putExtras(bundle2);
		spec2.setIndicator("Hotels");
		spec2.setContent(in2);

		TabHost.TabSpec spec3 = host.newTabSpec("Places");
		Intent in3 = new Intent(this, Info.class);
		spec3.setIndicator("Places");
		in3.putExtras(bundle3);
		spec3.setContent(in3);


		host.addTab(spec1);
		//host.addTab(spec2);
		//host.addTab(spec3);

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
		info.setText(spkval[0]);
		Tts.speak(spkval[0], TextToSpeech.QUEUE_FLUSH, // Drop all pending
														// entries in the
														// playback queue.
				null);
	}

}