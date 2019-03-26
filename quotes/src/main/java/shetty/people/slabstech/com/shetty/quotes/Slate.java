package com.proj.quoteforaday;
import java.io.File;
import java.util.Calendar;


 
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
 
public class Slate extends Activity {
 
    LinearLayout mContent;
    slate mSlate;
    Button mClear, mGetSign, mCancel;
    public static String tempDir;
    public int count = 1;
    public String current = null;
 
    View mView;
    File mypath;
 
 
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.slate);
        
		Bundle bundle = this.getIntent().getExtras();
		String param1 = bundle.getString("param1");

 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND,5);
		
		Intent intent = new Intent(this, NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
            12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = 
            (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                pendingIntent);
		
	
		
		
        mContent = (LinearLayout) findViewById(R.id.linearLayout);
        mSlate = new slate(this, null);
       
        if (param1.equals("a")) {
        	 mSlate.setBackgroundResource(R.drawable.a);
             
		}else if (param1.equals("b")) {
        	 mSlate.setBackgroundResource(R.drawable.b);
             
		}else if (param1.equals("c")) {
        	 mSlate.setBackgroundResource(R.drawable.c);
             
		}else if (param1.equals("d")) {
        	 mSlate.setBackgroundResource(R.drawable.d);
             
		}else if (param1.equals("e")) {
        	 mSlate.setBackgroundResource(R.drawable.e);
             
		}else if (param1.equals("f")) {
        	 mSlate.setBackgroundResource(R.drawable.f);
             
		}else if (param1.equals("g")) {
        	 mSlate.setBackgroundResource(R.drawable.g);
             
		}else if (param1.equals("h")) {
        	 mSlate.setBackgroundResource(R.drawable.h);
             
		}else if (param1.equals("a0")) {
        	 mSlate.setBackgroundResource(R.drawable.a0);
             
		}else if (param1.equals("a1")) {
        	 mSlate.setBackgroundResource(R.drawable.a1);
             
		}else if (param1.equals("a2")) {
        	 mSlate.setBackgroundResource(R.drawable.a2);
             
		}else if (param1.equals("a3")) {
        	 mSlate.setBackgroundResource(R.drawable.a3);
             
		}else if (param1.equals("a4")) {
        	 mSlate.setBackgroundResource(R.drawable.a4);
             
		}else if (param1.equals("a5")) {
        	 mSlate.setBackgroundResource(R.drawable.a6);
             
		}else if (param1.equals("a7")) {
        	 mSlate.setBackgroundResource(R.drawable.a7);
             
		}else if (param1.equals("a8")) {
        	 mSlate.setBackgroundResource(R.drawable.a8);
             
		}else if (param1.equals("a9")) {
        	 mSlate.setBackgroundResource(R.drawable.a9);
             
		}
        
        mContent.addView(mSlate, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        mClear = (Button)findViewById(R.id.clear);
        mGetSign = (Button)findViewById(R.id.getsign);
       mGetSign.setEnabled(false);
        mCancel = (Button)findViewById(R.id.cancel);
        mView = mContent;
 
 
 
        
    }
 
    @Override
    protected void onDestroy() {
        Log.w("GetSignature", "onDestory");
        super.onDestroy();
    }
 
     public class slate extends View
    {
        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();
 
        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();
 
        public slate(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }
 
        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawPath(path, paint);
        }
 
        @Override
        public boolean onTouchEvent(MotionEvent event)
        {
            float eventX = event.getX();
            float eventY = event.getY();
           
            switch (event.getAction())
            {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                lastTouchX = eventX;
                lastTouchY = eventY;
                return true;
 
            case MotionEvent.ACTION_MOVE:
 
            case MotionEvent.ACTION_UP:
 
                resetDirtyRect(eventX, eventY);
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++)
                {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    expandDirtyRect(historicalX, historicalY);
                    path.lineTo(historicalX, historicalY);
                }
                path.lineTo(eventX, eventY);
                break;
 
            default:
                debug("Ignored touch event: " + event.toString());
                return false;
            }
 
            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));
 
            lastTouchX = eventX;
            lastTouchY = eventY;
 
            return true;
        }
 
        private void debug(String string){
        }
 
        private void expandDirtyRect(float historicalX, float historicalY)
        {
            if (historicalX < dirtyRect.left)
            {
                dirtyRect.left = historicalX;
            }
            else if (historicalX > dirtyRect.right)
            {
                dirtyRect.right = historicalX;
            }
 
            if (historicalY < dirtyRect.top)
            {
                dirtyRect.top = historicalY;
            }
            else if (historicalY > dirtyRect.bottom)
            {
                dirtyRect.bottom = historicalY;
            }
        }
 
        private void resetDirtyRect(float eventX, float eventY)
        {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }
}