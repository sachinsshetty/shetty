package com.slabstech.app.thehdtour.quotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import java.io.*;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;


public class AudioPage extends Activity {
    /**
     * Called when the activity is first created.
     */

    String saveFileName = null;


    String infoText = null;
    String value = null;
    String saved = null;

    String speakValue = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();

        final int placeId = bundle.getInt("keyId");
        String pageType = bundle.getString("pageType");
        saved = bundle.getString("saved");

        setContentView(R.layout.audiopage);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        gridView.setVisibility(View.GONE);
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });

        android.content.res.Resources res = getResources();

        String[] pageList = null;
        String[] fileName = null;

        if (pageType.equalsIgnoreCase("Partner")) {
            pageList = res.getStringArray(R.array.partnerList);
            fileName = res.getStringArray(R.array.partnerFiles);
        } else {

            pageList = res.getStringArray(R.array.experienceList);
            fileName = res.getStringArray(R.array.experienceFiles);

        }


        saveFileName = pageList[placeId];
        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(pageList[placeId]);

        infoText = pageList[placeId];


        Button buttonSpeak = (Button) findViewById(R.id.speak);
        Button buttonStop = (Button) findViewById(R.id.stop);


        Boolean voiceOver = false;
        if (voiceOver) {
            buttonSpeak.setVisibility(View.VISIBLE);
            buttonStop.setVisibility(View.VISIBLE);

        }

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
        } catch (IOException e) {
            e.printStackTrace();

        }

        speakValue = (String) outp.toString();

        TextView info = (TextView) findViewById(R.id.info);
        info.setText(speakValue);
    }
}