package org.anurag.bookview;

import java.io.IOException;
import java.io.InputStream;
import android.widget.TextView;

public class ChangePageContent implements Runnable {
	private int mpage_number;
	private TextView mtext;
	private int mscreenWidth;
	private String mselecteditem;
	private int mscreenHeight;
	private int maxlines;

	public ChangePageContent(int page_number, int screenWidth, int screenHeight, TextView text) {
	     this.mpage_number = page_number;
		 this.mtext = text;
		 mselecteditem = (String) text.getTag();
		 this.mscreenWidth = screenWidth;
		 this.mscreenHeight = screenHeight;
		 this.maxlines = screenHeight/text.getLineHeight();
	}
	public void run() {
		if (mpage_number == 0) {
			return;
		}
        String text = null;
        try {
 		   InputStream input = mtext.getContext().getAssets().open(mselecteditem.toString() + mpage_number + ".txt");
 		   int size = input.available();
 		   byte[] buffer = new byte[size];
 		   input.read(buffer);
 		   input.close();
 		   text = new String(buffer);
 		} catch (IOException e) {
 		   e.printStackTrace();
 	    }
 	    mtext.setMaxHeight(mscreenHeight);
 	    mtext.setMaxWidth(mscreenWidth);	    
        mtext.setLines(maxlines);
		mtext.setText(text);
	}

}
