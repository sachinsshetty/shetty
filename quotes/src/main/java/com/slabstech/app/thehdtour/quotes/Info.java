package com.slabstech.app.thehdtour.quotes;





import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Info extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		TextView tx = (TextView) findViewById(R.id.tex);
		// ///////////
		Bundle bundle = this.getIntent().getExtras();
		String para = bundle.getString("param1");

	
		
				
		tx.setText(para);

	}

}