package shetty.people.slabstech.com.shetty.quotes;

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
	//	mList.setBackgroundResource(R.color.gray);
		
		String item[]={"1.The Lion and the Mouse","2.The Goose with the Golden Eggs","3.The Hare and the Tortoise","4.The Fox and the Stork"
				,"5.The Monkey and the Dolphin","6.Bundle of sticks","7.The Thirsty Crow"};
		
		mList.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, item));

		
		mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				 String s=null;		
				if(position ==0)
				{
				s="one";
				}
				else	if(position ==1)
				{
				s="two";	
				}
				else	if(position ==2)
				{
					s="three";
					
				}
				else	if(position ==3)
				{
s="four";
				}
				else if(position==4)
				{
					s="five";
				}else if(position==5)
				{
					s="six";
				}
				else if(position==6)
				{
					s="seven";
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

        

