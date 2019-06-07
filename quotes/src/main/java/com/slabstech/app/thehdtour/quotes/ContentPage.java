package com.slabstech.app.thehdtour.quotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;


public class ContentPage extends Activity {
    /**
     * Called when the activity is first created.
     */
    private String[] pageList = null;
    private String[] fileList = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_page);


        //TextView titleTextView = findViewById(R.id.title);
        //TextView info = findViewById(R.id.info);

        //setAdditionalLayout();
        Bundle bundle = this.getIntent().getExtras();

        int placeId = bundle.getInt("keyId");
        String pageType = bundle.getString("pageType");
        this.setTitle(pageType);
        String titleText = null;

        titleText = getPageTitle(pageType, placeId);
        //titleTextView.setText(titleText);

        String speakValue = null;

        speakValue = getPageContent(placeId);
        //info.setText(speakValue);

        ListView mList;

        mList = (ListView) findViewById(R.id.list);

        String[]content = getResources().getStringArray(R.array.partnerList);
        mList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, content));



    }

    private String getPageContent(int placeId) {
        android.content.res.Resources res = getResources();
        String fileName = null ;

        fileName = fileList[placeId];
        String speakValue = null;

        speakValue = res.getString(res.getIdentifier( fileName, "string", getPackageName()));
        return speakValue;

    }

    private String getPageTitle(String pageType, int placeId) {
        android.content.res.Resources res = getResources();

        if (pageType.equalsIgnoreCase("Partner")) {
            pageList = res.getStringArray(R.array.partnerList);
            fileList = res.getStringArray(R.array.partnerFiles);
        } else {

            pageList = res.getStringArray(R.array.experienceList);
            fileList = res.getStringArray(R.array.experienceFiles);

        }
        String titleText = null;

        titleText = pageList[placeId];
        return titleText ;
    }

    /*
    private void setAdditionalLayout() {

        GridView gridView = findViewById(R.id.grid_view);

        Boolean gridDisplay = false;

        if(gridDisplay){
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

        }
    }
    */

}