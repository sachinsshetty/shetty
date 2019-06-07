package com.slabstech.app.thehdtour.quotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class Narration extends Activity {


    String val[] = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.narration);

        pageSetup();
        //onlinePageSetup();

    }
/*
    private void onlinePageSetup() {

        ListView onLineList = (ListView) findViewById(R.id.online_list);

        String[] onlinePageList = null;

        String txsrc4;

        txsrc4 = "https://thehdtour.com/partners/";

        URL textUrl;
        try {


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


        //    alert.setText("Internet not connected");

        //    bweb.setEnabled(false);

            e2.printStackTrace();
        } catch (IOException e) {

         //   alert.setText("Internet not connected");

         //   bweb.setEnabled(false);


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


        if (listVal.equals(null)) {

        } else {

            val = listVal.split("@");

            registerForContextMenu(onLineList);
            onLineList.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, val));
        }

        onLineList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {


                if (position == 0) {
                    Toast.makeText(getBaseContext(), "please select a valid city", Toast.LENGTH_SHORT).show();
                }

                if (position != 0) {
                    String placeName = "";


                    placeName = val[position];

                    Bundle bundle1 = new Bundle();
                    bundle1.putString("param1", placeName);
                    String saveVal = "no";
                    bundle1.putString("saved", saveVal);
                    Intent myintent = new Intent();
                    myintent.setClass(getApplicationContext(), AudioPage.class);
                    myintent.putExtras(bundle1);
                    startActivityForResult(myintent, 0);
                    Toast.makeText(getBaseContext(), "loading information from internet", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK, myintent);
                    finish();

                }


            }
        });



    }
*/
    private void pageSetup() {

        ListView mList;

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