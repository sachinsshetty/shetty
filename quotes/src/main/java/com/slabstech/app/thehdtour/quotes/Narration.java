package com.slabstech.app.thehdtour.quotes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

		String item[]={getString(R.string.exp_rani_chennamma),
				getString(R.string.exp_rock_garden),
				getString(R.string.exp_unkal_lake),
				getString(R.string.exp_sidharudha_matha),
				getString(R.string.exp_nrupatunga_betta),
				getString(R.string.exp_gangubhai_hangal),
				getString(R.string.exp_kltech_water_fountain),
				getString(R.string.exp_durgadbail_mystery),
				getString(R.string.exp_chandramouleshwara),
				getString(R.string.exp_iskcon_akshay_patra)
				};
		
		mList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, item));

		
		mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String s=null;

				switch (position)
				{
					case 0:
					{
						s="one";
						break;

					}
					case 1:
					{
						s="two";
						break;

					}
					case 2:
					{
						s="three";
						break;

					}
					case 3:
					{
						s="four";
						break;

					}
					case 4:
					{
						s="five";
						break;

					}
					case 5:
					{
						s="six";
					break;

					}
					case 6:
					{
						s="seven";

						break;

					}
					case 7:
					{
						s="eight";

						break;

					}
					case 8:
					{
						s="nine";
						break;
					}
					case 9:
					{
						s="ten";
						break;
					}
					case 10:
					{
						s="eleven";
						break;

					}
					default:
						break;
				}


				Bundle bundle1 = new Bundle();
				String saveVal="no";
				bundle1.putString("saved", saveVal);
				bundle1.putString("param1", s);

				Intent myintent = new Intent();
				myintent.setClass(getApplicationContext(), AudioPage.class);
				myintent.putExtras(bundle1);
				startActivityForResult(myintent, 0);
				setResult(RESULT_OK, myintent);
				finish();
			}});

    }}

        

