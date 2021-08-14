/*
 * Copyright notice: all rights reserved to Kun Xie, Craig Damon.
 */
package com.example.Badmintime;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SimpleCursorAdapter;

/**
 * Class: RecordedListAdapter. RecordedListAdapter class setup the functional check boxes and buttons in the recorded event list and deleted event list.
 */
public class RecordedListAdapter extends SimpleCursorAdapter {
    private Activity ctx;

    /**
     * constructor of the RecordedListAdapter
     * @param context context from activity
     * @param layout layout
     * @param c cursor
     * @param from string array
     * @param to integer array
     * @param flags flags
     */
    public RecordedListAdapter(Activity context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        ctx = context;
        setViewBinder(new UpdateCheckBox());
    }

    /**
     * Class: UpdateCheckBox. This inner class implements the SimpleCursorAdapter.ViewBinder interface to handle visual changes of he check box
     * and setup the visual effect of the button in the list
     */
    private static class UpdateCheckBox implements SimpleCursorAdapter.ViewBinder{

        /**
         * this method setup the check box's state and the look of buttons in the list
         * @param view view
         * @param cursor cursor
         * @param columnIndex column position
         * @return setting result
         */
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            if (view instanceof CheckBox){
                CheckBox cBox = (CheckBox)view;
                cBox.setChecked(Boolean.valueOf(cursor.getString(columnIndex)));
                return true;
            }
            if (view instanceof Button){
                return true;
            }
            return false;
        }
    }


//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = ctx.getLayoutInflater().inflate(R.layout.recorded_list, parent, false);
//        }
//
//        CheckBox cBox = convertView.findViewById(R.id.checkRecord);
//        cBox.setChecked((Boolean) getItem(position));
//
//        return convertView;
//    }


}
