package com.slabstech.app.thehdtour.quotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class Narration extends Activity {
    private ListView mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.narration);
        mList = (ListView) findViewById(R.id.list);

        final String pageType = getIntent().getExtras().getString("pageType");

        android.content.res.Resources res = getResources();

        String[] pageList = null;

        if (pageType.equalsIgnoreCase("Partner")) {
            pageList = res.getStringArray(R.array.partnerList);
        } else
            pageList = res.getStringArray(R.array.experienceList);

        mList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, pageList));

        mList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Bundle bundle1 = new Bundle();
                String saveVal = "no";
                bundle1.putString("saved", saveVal);

                bundle1.putInt("keyId", position);

                bundle1.putString("pageType", pageType);

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

        

