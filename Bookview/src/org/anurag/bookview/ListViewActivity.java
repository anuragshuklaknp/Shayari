package org.anurag.bookview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewActivity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        String[] values = new String[] { "Love", "Sad", "Emotion",
                "Life", "Happiness", "Dream", "Party", "Loffer",
                "X", "Y" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            R.layout.rowlayout, R.id.label, values);
        setListAdapter(adapter);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intent = new Intent(this, BookviewActivity.class);
        intent.putExtra("selected item", item);
    	startActivity(intent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menushareoption:  
            	Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            	   shareIntent.setType("text/plain");
            	   shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, new String("Shayari"));
            	   shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, new String("Share it!"));

            	   startActivity(Intent.createChooser(shareIntent, "Share the App"));
            	break;
        }
        return true;
    }
}