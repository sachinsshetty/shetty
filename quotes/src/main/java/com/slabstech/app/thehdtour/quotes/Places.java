package com.slabstech.app.thehdtour.quotes;


import android.app.NotificationManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Places extends TabActivity {
    /**
     * Called when the activity is first created.
     */

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places);

        String pageType = getIntent().getExtras().getString("pageType");

        setupTabs(pageType);
    }

    void setupTabs(String type) {

        TabHost host = getTabHost();

        Intent in1 = new Intent(this, Narration.class);
        Bundle bundle1 = new Bundle();
        bundle1.putString("pageType", type);
        in1.putExtras(bundle1);

        TabHost.TabSpec spec1 = host.newTabSpec(type);

        spec1.setIndicator(type);
        spec1.setContent(in1);

        host.addTab(spec1);

    }

}