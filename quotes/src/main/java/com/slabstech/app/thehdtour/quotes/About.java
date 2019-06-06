package com.slabstech.app.thehdtour.quotes;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class About extends Activity {
    private ListView mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutlist);

        mList = (ListView) findViewById(R.id.list);

        String item[] = {"About the App", "Help", "Contact Us", "Designers"};

        mList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, item));

        mList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {

                    Dialog d = new Dialog(About.this);
                    d.setContentView(R.layout.dial_about);
                    d.setTitle("About the App");
                    d.show();

                } else if (position == 1) {
                    Dialog d = new Dialog(About.this);
                    d.setContentView(R.layout.dial_help);
                    d.setTitle("Help");
                    d.show();

                } else if (position == 2) {

                    Dialog d = new Dialog(About.this);
                    d.setContentView(R.layout.dial_contact);
                    d.setTitle("Contact Us");
                    d.show();


                } else if (position == 3) {

                    Dialog d = new Dialog(About.this);
                    d.setContentView(R.layout.dial_design);
                    d.setTitle("Designers");
                    d.show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        if (item.getItemId() == R.id.help_lang) {
            Dialog d = new Dialog(About.this);
            d.setContentView(R.layout.dial_lang);
            d.setTitle("changing the language");
            d.show();


        } else if (item.getItemId() == R.id.help_speed) {
            Dialog d = new Dialog(About.this);
            d.setContentView(R.layout.dial_speed);
            d.setTitle("changing the speed");
            d.show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.helpmenu, menu);


        return super.onCreateOptionsMenu(menu);
    }
}

        

