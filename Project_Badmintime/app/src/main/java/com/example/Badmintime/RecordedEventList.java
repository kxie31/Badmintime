/*
 * Copyright notice: all rights reserved to Kun Xie, Craig Damon.
 */
package com.example.Badmintime;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Class: RecordedEventList. RecordedEventList class queries the room database to get all club events marked as either notify or recorded
 * display in the listview in this activity.
 * User can interact with events in the list using the check box and the delete button.
 * Checking and un-checking the notify box will change the event's state in local data base.
 * Clicking delete button will move the event into recycle bin.
 */
public class RecordedEventList extends AppCompatActivity {
    private NotificationManagerCompat notificationManager; // notification manager
    private ArrayList<String> columns; // columns contains the list display
    private CursorAdapter cursorAdapter; // the cursor adapter will inflates the list
    private ContentResolver cr; // content resolver to access content provider
    private Uri uri; // uri
    private Boolean check; // check box value
    private ListView list; // the list

    /**
     * the on create method of recorded event list.
     * all fields are initialized here.
     * if user interacted with the notification, an intent may gets sent to this class and is also handled in this method
     * @param savedInstanceState savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notificationManager = NotificationManagerCompat.from(getApplicationContext());
        setContentView(R.layout.activity_club_event_list__d);
        list = findViewById(R.id.eventList);
        cr = getContentResolver();
        uri = Uri.parse("content://com.example.notificationtest.DataManager/ClubEvent"); //uriAll
        setTitle("Club Events");

        ContentValues newVals = new ContentValues();

        Intent i = getIntent();
        if (i != null) {
            String action = i.getStringExtra("action");
            if (action != null) {
                int id = i.getIntExtra("id", -1);
                int noteID = i.getIntExtra("noteID",-1);
                if (action.equals("record")) {
                    newVals.put(DataManager.notify,false);
                    Uri uriSingle = Uri.parse("content://com.example.notificationtest.DataManager/ClubEvent/" + id);
                    cr.update(uriSingle,newVals,null,null);
                    notificationManager.cancel(noteID);
                }
                else{
                    Log.i("Kun Recordlist ","Unexpected action");
                }
            }
        }
    }

    /**
     * on start method from activity
     */
    protected void onStart() {
        super.onStart();
        findEvents(list);
    }

    /**
     * this method queries the data base and retrieve target events then inflates the list view.
     * @param list the list displays all desired events
     */
    private void findEvents(ListView list) {

        Cursor cursor = cr.query(uri,null,"deleted = false",null,null);

        //List of all columns
        columns = new ArrayList<>();
        columns.add(DataManager.ID);
        columns.add(DataManager.clubName);
        columns.add(DataManager.date);
        columns.add(DataManager.startTime);
        columns.add(DataManager.endTime);
        columns.add(DataManager.notify);
        columns.add(DataManager.deleted);
        //Horizontal linear layout for column display
        //Listview for row display
        //for each text view entry

        cursorAdapter = new RecordedListAdapter(this,R.layout.recorded_list,cursor, columns.toArray(new String[columns.size()]) ,
                new int []{R.id.recordedid,R.id.recorded1,R.id.recorded2,R.id.recorded3,R.id.recorded4,R.id.checkRecord,R.id.checkDelete},0);
        list.setAdapter(cursorAdapter);
    }

    /**
     * this method handles checking and un-checking action of the notify check box
     * @param v view
     */
    public void recordedClick(View v){
        ContentValues newVals = new ContentValues();
        ListView list = findViewById(R.id.eventList);
        ViewGroup parent = (ViewGroup)v.getParent();
        TextView idField = (TextView)parent.getChildAt(0);
        String id = idField.getText().toString();
        check = ((CheckBox) findViewById(R.id.checkRecord)).isChecked();
        newVals.put(DataManager.notify,check);
        Uri uriSingle = Uri.parse("content://com.example.notificationtest.DataManager/ClubEvent/" + id);
        cr.update(uriSingle,newVals,null,null);
    }

    /**
     * this method handles the delete button action to each event
     * @param v view
     */
    public void deletedClick(View v) {
        ContentValues newVals = new ContentValues();
        ViewGroup parent = (ViewGroup)v.getParent();
        TextView idField = (TextView)parent.getChildAt(0);
        String id = idField.getText().toString();
        newVals.put(DataManager.deleted,true);
        newVals.put(DataManager.notify,false);
        Uri uriSingle = Uri.parse("content://com.example.notificationtest.DataManager/ClubEvent/" + id);
        cr.update(uriSingle,newVals,null,null);
    }

    /**
     * the onclick method of recycle bin button
     * @param v view
     */
    public void toDeletedlist(View v) {
        Intent toDeletedlist = new Intent(this, DeletedEventList.class);
        startActivity(toDeletedlist);
    }

    /**
     * the onclick method of home button
     * @param v view
     */
    public void toMain(View v) {
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
    }

}