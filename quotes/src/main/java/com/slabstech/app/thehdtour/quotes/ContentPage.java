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

        //setAdditionalLayout();
        Bundle bundle = this.getIntent().getExtras();

        int placeId = bundle.getInt("keyId");
        String pageType = bundle.getString("pageType");

        String contentTitle = getPageTitle(pageType,placeId);

        TextView titleView = new TextView(this);
        titleView.setText(pageType + " : " + contentTitle);
        titleView.setTextSize(20.0f);
        titleView.setGravity(View.TEXT_ALIGNMENT_GRAVITY);


        String content[] = null;

        content = getPageContent(placeId);

        ListView mList;

        mList = (ListView) findViewById(R.id.list);


        mList.addHeaderView(titleView);
        this.setTitle(pageType);
        mList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, content));

    }

    private String[] getPageContent(int placeId) {
        android.content.res.Resources res = getResources();
        String fileName = null ;

        fileName = fileList[placeId];
        String speakValue[] = null;

        speakValue = res.getStringArray(res.getIdentifier( fileName, "array", getPackageName()));
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


}