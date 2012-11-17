package org.anurag.bookview;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class App1Activity extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] values = new String[] { "Love", "Sad", "Emotion",
                "Life", "Happiness", "Dream", "Party", "Loffer",
                "X", "Y" };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.rowlayout, R.id.label, values);
            setListAdapter(adapter);
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Intent intent = new Intent(this, BookviewActivity.class);
        intent.putExtra("selected item", item);
        try {
        	startActivity(intent);
        }
        catch(android.content.ActivityNotFoundException e){
        	e.printStackTrace();
        }
    }
}