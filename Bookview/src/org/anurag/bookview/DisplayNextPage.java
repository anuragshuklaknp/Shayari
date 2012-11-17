package org.anurag.bookview;

import android.view.animation.Animation;
import android.widget.TextView;
public final class DisplayNextPage implements Animation.AnimationListener {
	private int mpage_number = 0;
	private TextView mtext;
	private int screenWidth;
	private int screenHeight;
	public DisplayNextPage(int page_number, int screenWidth, int screenHeight, TextView text) {
	this.mpage_number = page_number;
	this.mtext = text;
	this.screenWidth = screenWidth;
	this.screenHeight = screenHeight;
	}

	public void onAnimationStart(Animation animation) {
	}

	public void onAnimationEnd(Animation animation) {
		mtext.post((new ChangePageContent(mpage_number, screenWidth, screenHeight, mtext)));
	}

	public void onAnimationRepeat(Animation animation) {
	}
}