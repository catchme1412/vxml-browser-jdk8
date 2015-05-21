package com.vxml.tag;

import java.util.Stack;

import org.w3c.dom.Node;

import com.vxml.core.IOHandler;

public abstract class AbstractTag implements Tag {

    private Node node;
    private static Stack<Boolean> isSkipExecuteStack;

    static {
        isSkipExecuteStack = new Stack<Boolean>();
    }

    @Override
    public void startTag() {
    }

    @Override
    public void execute() {
        if (this instanceof LogicalTag) {
            LogicalTag logical = (LogicalTag)this;
            if (logical.isLogicalBlockExecuted()) {
                return;
            }
        }
        System.out.println("AbstractTag.execute..." + node);
    }

    @Override
    public void endTag() {

    }

    public void tryExecute() {
        if (((AbstractTag) this).isSkipExecutePeek()) {
            System.out.println("SKIPPING:"+ ((AbstractTag) this));
        } else {
            execute();
        }
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void toggleSkipExecute(Boolean isTrue) {
        Boolean r = isSkipExecuteStack.pop();
        isSkipExecuteStack.push(isTrue);
    }

    public void isSkipExecute(boolean isSkip) {
        isSkipExecuteStack.push(isSkip);
    }

    public boolean isSkipExecute() {
        if (!isSkipExecuteStack.isEmpty()) {
            return isSkipExecuteStack.pop();
        } else {
            return false;
        }
    }

    public void clearTopSkipExecuteFlag() {
        if (!isSkipExecuteStack.isEmpty()) {
            isSkipExecuteStack.pop();
        }
    }

    public boolean isSkipExecutePeek() {
        if (!isSkipExecuteStack.isEmpty()) {
            return isSkipExecuteStack.peek();
        } else {
            return false;
        }
    }

}
