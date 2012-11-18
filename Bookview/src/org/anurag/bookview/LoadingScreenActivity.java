package org.anurag.bookview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadingScreenActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.loadingscreen);
	/** set time to splash out */
	final int loadingdisplaytime = 3000;
	/** create a thread to show splash up to splash time */
	Thread welcomeThread = new Thread() {
	int wait = 0;
	@Override
	public void run() {
		try {
			super.run();
			/**
			* use while to get the splash time. Use sleep() to increase
			* the wait variable for every 100L.
			*/
			while (wait < loadingdisplaytime) {
				sleep(100);
				wait += 100;
			}
		} catch (Exception e) {
			System.out.println("EXc=" + e);
		} finally {
			//Here we moved to another main activity class
			startActivity(new Intent(LoadingScreenActivity.this, ListViewActivity.class));
			finish();
		}}
	};
	welcomeThread.start();
	}
}