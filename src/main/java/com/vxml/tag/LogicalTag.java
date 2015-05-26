package com.vxml.tag;

import java.util.Stack;

public class LogicalTag extends AbstractTag {
    private static Stack<Boolean> logicalBlockExecuted;

    static {
        logicalBlockExecuted = new Stack<Boolean>();
    }

    public Boolean isLogicalBlockExecuted() {
        return logicalBlockExecuted.peek();
    }

    public void setLogicalBlockExecuted(boolean isTrue) {
        logicalBlockExecuted.add(isTrue);
        isSkipExecute(!isTrue);
    }

    public void toggleLogicalBlockStatus(Boolean isTrue) {
        Boolean r = logicalBlockExecuted.pop();
        logicalBlockExecuted.push(isTrue);
        toggleSkipExecute(!isTrue);
    }

    public void clearLogicalBlockStatus() {
        logicalBlockExecuted.pop();
        clearTopSkipExecuteFlag();
    }
    
    
//    public boolean isSkipExecute() {
//        if (!isSkipExecuteStack.isEmpty()) {
//            return isSkipExecuteStack.pop();
//        } else {
//            return false;
//        }
//    }


}
