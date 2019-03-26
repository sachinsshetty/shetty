package shetty.people.slabstech.com.shetty.quotes;



import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class VSlate extends Activity {
	Integer[] pics = { R.drawable.a0

	/*		R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, R.drawable.f,
			R.drawable.g, R.drawable.h, R.drawable.a0,
			 R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4,
			 R.drawable.a5, R.drawable.a6, R.drawable.a7, R.drawable.a8, R.drawable.a9,
	*/
	};
	ImageView imageView;

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	
		Dialog d=new Dialog(VSlate.this);
		d.setTitle("Alphabets and Numbers");
		d.setContentView(R.layout.help_alphanum);
		d.show();
		
		
		return super.onCreateOptionsMenu(menu);
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vslate);

		Gallery ga = (Gallery) findViewById(R.id.Gallery01);
		ga.setAdapter(new ImageAdapter(this));

		imageView = (ImageView) findViewById(R.id.ImageView01);
		ga.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				imageView.setImageResource(pics[arg2]);

				String s = null;

				if (arg2 == 0) {

					s = "a";
				} else if (arg2 == 1) {
					s = "b";
				} else if (arg2 == 2)

				{
					s = "c";
				} else if (arg2 == 3) {
					s = "d";
				} else if (arg2 == 4) {
					s = "e";
				} else if (arg2 == 5) {
					s = "f";
				} else if (arg2 == 6) {
					s = "g";
				} else if (arg2 == 7) {
					s = "h";
				} else if (arg2 == 8) {
					s = "a0";
				} else if (arg2 == 9) {
					s = "a1";
				}else if (arg2 == 10) {
					s = "a2";
				}else if (arg2 == 11) {
					s = "a3";
				}else if (arg2 == 12) {
					s = "a4";
				}else if (arg2 == 13) {
					s = "a5";
				}else if (arg2 == 15) {
					s = "a6";
				}else if (arg2 == 16) {
					s = "a7";
				}else if (arg2 == 17) {
					s = "a8";
				}else if (arg2 == 18) {
					s = "a9";
				}

				Bundle bundle1 = new Bundle();
				String saveVal="no";
				bundle1.putString("saved", saveVal);
				bundle1.putString("param1", s);

				Intent myintent = new Intent();
				myintent.setClass(getApplicationContext(), Slate.class);
				myintent.putExtras(bundle1);
				startActivityForResult(myintent, 0);
				setResult(RESULT_OK, myintent);
				finish();

			}

		});

	}

	public class ImageAdapter extends BaseAdapter {

		private Context ctx;
		int imageBackground;

		public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(
					R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		public int getCount() {

			return pics.length;
		}

		public Object getItem(int arg0) {

			return arg0;
		}

		public long getItemId(int arg0) {

			return arg0;
		}

		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ImageView iv = new ImageView(ctx);
			iv.setImageResource(pics[arg0]);
			iv.setScaleType(ImageView.ScaleType.FIT_XY);
			iv.setLayoutParams(new Gallery.LayoutParams(150, 120));
			iv.setBackgroundResource(imageBackground);
			return iv;
		}

	}
}