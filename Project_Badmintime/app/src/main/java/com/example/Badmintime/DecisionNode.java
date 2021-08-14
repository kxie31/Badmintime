/*
 * Copyright notice: all rights reserved to Kun Xie, Craig Damon.
 */
package com.example.Badmintime;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/**
 * Class: DecisionNode. DecisionNode class stores the decision of the event attribute
 */
public class DecisionNode extends TreeNode {

    private String attribute; // attribute
    //private TreeNode defaultAction;
    Map<String,TreeNode> children = new HashMap<String,TreeNode>(); // hashmap of the tree

    /**
     * constructor of DecisionNode
     * @param attribute attribute of this node
     */
    public DecisionNode(String attribute) {
        this.attribute = attribute;
    }

    /**
     * perform action on the event
     * @param e event
     * @return the resullt ActionNode of the event
     */
    @Override
    public ActionNode performAction(Event e) {
        // TODO Auto-generated method stub

        String value = e.getValue(attribute);
        TreeNode child = children.get(value);
        if(child == null) {
            return defaultAction.performAction(e);
        }
        else {
            return child.performAction(e);
        }
    }

    /**
     * this method prints out node attribute and children key to the logcat
     * @param tag tag of the log
     */
    @Override
    public void toLog(String tag) {
        Log.i(tag,"Decision Node " + attribute);
        for (String k : children.keySet())
        {
            Log.i(tag,"Key = " + k);
            children.get(k).toLog(tag);
        }
    }

    /**
     * this method add child to the TreeNode
     * @param key
     * @param Action
     */
    public void addChild(String key, TreeNode Action) {
        children.put(key, Action);
    }

    /**
     * to string method that converts field values to string
     * @return string values
     */
    public String toString() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(attribute);
        buffer.append("=");

        for (String k : children.keySet())
        {
            buffer.append(k);
            buffer.append("\n  ");
            buffer.append(children.get(k));
            buffer.append("\n");
        }

        return new String(buffer);
    }
}
