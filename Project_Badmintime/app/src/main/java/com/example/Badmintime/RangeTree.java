/*
 * Copyright notice: all rights reserved to Kun Xie, Craig Damon.
 */
package com.example.Badmintime;

import android.os.Build;
import android.util.Log;

import java.time.LocalDateTime;
import androidx.annotation.RequiresApi;

/**
 * Class: RangeTree. RangeTree class creates time stamp between the two tree nodes passed in.
 */
public class RangeTree extends TreeNode {
    private LocalDateTime timeStamp; // time stamp
    private TreeNode before; // begin time tree node
    private TreeNode after; // end time tree node

    /**
     * the performAction method of rangeTree class
     * @param e event
     * @return ActionNode
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ActionNode performAction(Event e) {
        // TODO Auto-generated method stub
        if ( e.getTimeStamp().isBefore(timeStamp) ) {
            return before.performAction(e);
        }
        return after.performAction(e);
    }

    /**
     * prints simtStamp and the two tree nodes to logcat
     * @param tag
     */
    @Override
    public void toLog(String tag) {
        Log.i(tag,"<" + timeStamp + " " + before + " " + after);
    }

    /**
     * constructor of RangeTree
     * @param timeStamp timeStamp in LocaDateTime
     * @param before beginning treeNode
     * @param after ending treeNode
     */
    public RangeTree(LocalDateTime timeStamp, TreeNode before, TreeNode after) {
        super();
        this.timeStamp = timeStamp;
        this.before = before;
        this.after = after;
    }

    /**
     * toString method which converts all fields into string
     * @return concatenated string result
     */
    public String toString() {
        return "<" + timeStamp + "\n" + before + "\n" + after;
    }
}
