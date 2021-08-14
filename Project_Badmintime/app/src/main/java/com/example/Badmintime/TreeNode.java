/*
 * Copyright notice: all rights reserved to Kun Xie, Craig Damon.
 */
package com.example.Badmintime;

/**
 * Class: TreeNode. Tree node class is abstract class contains methods for action node and decision node.
 */
public abstract class TreeNode {
    protected static ActionNode defaultAction = new ActionNode("N",10);

    /**
     * ActionNode contains the action of the event
     * @param e event
     * @return result action node
     */
    public abstract ActionNode performAction(Event e);

    /**
     * the method that will print out message to logcat for debuggin purpose
     * @param tag tag message of the log
     */
    public abstract void toLog(String tag);
}
