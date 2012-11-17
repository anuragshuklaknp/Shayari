package org.anurag.bookview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class BookviewActivity extends Activity {
	private ImageView mimage;
	private TextView mtext;
	private int mpage_number;
	private int screenWidth;
	private int screenHeight;
    private GestureDetector gestureDetector;
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt("mpage_number", mpage_number);
        savedInstanceState.putString("page_text", (String) mtext.getText());
        
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mimage = (ImageView) findViewById(R.id.pageimage);
        mtext = (TextView) findViewById(R.id.textview);
        if (savedInstanceState != null) {
        	mpage_number = savedInstanceState.getInt("mpage_number");
        	mtext.setText(savedInstanceState.getString("page_text"));
        } else {
        	mpage_number = 0;
        	mimage.setImageResource(R.drawable.frontcover);
        }
        if (mpage_number == 0) {
        	mimage.setImageResource(R.drawable.frontcover);
        }
        Intent intent = getIntent();
        String selecteditem = intent.getStringExtra("selected item");
        mtext.setTag(selecteditem);
        
        class MyGestureDetector extends SimpleOnGestureListener {
        	private static final int SWIPE_MIN_DISTANCE = 120;
            private static final int SWIPE_MAX_OFF_PATH = 250;
            private static final int SWIPE_THRESHOLD_VELOCITY = 200;
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                	if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                       return false;
                    // right to left swipe
                    if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    	if (mpage_number < 50) {
	                    	if (mpage_number == 0) {
	                			mimage.setImageResource(R.drawable.frontcover);
	                    	} else {
	                    		mimage.setImageResource(R.drawable.pageimage);
	                    	}
	                    	mpage_number++;
	                        applyRotation(0, -90);
                    	}
                    }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    	if (mpage_number > 0) {
	                    	if (mpage_number == 1) {
	                			mimage.setImageResource(R.drawable.frontcover);
	                    	} else {
	                    		mimage.setImageResource(R.drawable.pageimage);
	                    	}
	                    	mpage_number--;
	                        applyRotation(270, 360);
                    	}
                    }
                } catch (Exception e) {
                   	e.printStackTrace();
                }
                return true;
            }
        }
		Display display = getWindowManager().getDefaultDisplay(); 
	    screenWidth = display.getWidth();
	    screenHeight = display.getHeight();

        gestureDetector = new GestureDetector(new MyGestureDetector());
        mimage.setOnTouchListener(new View.OnTouchListener() {			
			public boolean onTouch(View v, MotionEvent event) {
		        gestureDetector.onTouchEvent(event);
				return true;
			}
		});
        mtext.setOnTouchListener(new View.OnTouchListener() {			
			public boolean onTouch(View v, MotionEvent event) {
		        gestureDetector.onTouchEvent(event);
				return true;
			}
		});
        mtext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
			}
		});
    }
    
    private void applyRotation(float start, float end) {
    	mtext.setText("");
		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Flippage rotation = new Flippage(start, end);
		rotation.setDuration(1000);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextPage(mpage_number, screenWidth, screenHeight, mtext));
		mimage.startAnimation(rotation);
    }
}