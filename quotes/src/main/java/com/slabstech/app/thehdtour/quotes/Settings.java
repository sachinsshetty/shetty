package com.slabstech.app.thehdtour.quotes;
import android.app.Dialog;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Toast;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class Settings extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final int HELP_SPEED = 8888;
    private static final int HELP_LANG = 7777;
    private static final int LANGUAGE_MENU = 0;
    private static final int SPEED_MENU = 1000;
    private static final int SAVED_MENU = 1500;
    private ArrayList<Locale> availableLocales = null;

    private TextToSpeech Tts = null;
    String saveFileName = null;


    String infoText = null;
    String value = null;
    String saved = null;

    String speakValue = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_screen);
        WebView webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ti.to/the-hd-tour/hd-limited-edition-march");

        /*String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
        mywebview.loadData(data, "text/html", "UTF-8"); */

        //mywebview.loadUrl("file:///android_asset/myresource.html");



        availableLocales = new ArrayList<Locale>();

        Tts = new TextToSpeech(this, this);

        /*buttonSpeak.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                speakInfo(placeId);
            }

        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tts.stop();

            }
        });

        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.audiomenu, menu);

        String menutitle = "";


        if (saved.equals("yes")) {
            SubMenu savedMenu = menu.addSubMenu(0, SAVED_MENU, Menu.NONE, "save");


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


        if (itemId == SAVED_MENU) {


            OutputStream os;
            try {
                os = openFileOutput(saveFileName + ".txt", MODE_WORLD_READABLE);
                OutputStreamWriter osw = new OutputStreamWriter(os);

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
            } catch (IOException e) {
                e.printStackTrace();

            }

            String listVal = (String) outp.toString();


            OutputStream osi;
            try {
                osi = openFileOutput("list.txt", MODE_WORLD_READABLE);
                OutputStreamWriter osw = new OutputStreamWriter(osi);

                osw.write(listVal + "@" + saveFileName);


                osw.close();


            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (itemId == R.id.back) {
            startActivity(new Intent(getApplicationContext(), Places.class));
        } else if (itemId > SPEED_MENU) {
            setTextToSpeechRate(itemId - SPEED_MENU - 1);
        } else if (itemId > LANGUAGE_MENU && itemId < SPEED_MENU) {
            setTextToSpeechLocale(itemId - LANGUAGE_MENU - 1);
        }

        return result;
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        boolean result = true;

        long id = info.id - Menu.FIRST;

        if (item.getItemId() == HELP_LANG) {

            Dialog d = new Dialog(Settings.this);

            d.setContentView(R.layout.dial_lang);
            d.setTitle("Change Language");
            d.show();

        } else if (item.getItemId() == HELP_SPEED) {
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

    private void speakInfo(int city) {
        // TODO Auto-generated method stub
        Tts.speak(speakValue, TextToSpeech.QUEUE_FLUSH, // Drop all pending
                // entries in the
                // playback queue.
                null);
    }

}