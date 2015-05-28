package com.vxml.tag;

import java.util.Iterator;
import java.util.Stack;

public class LogicalTag extends AbstractTag {
    private static Stack<Boolean> logicalConditionStateStack;

    static {
        logicalConditionStateStack = new Stack<Boolean>();
    }

    
    public void ifConditionState(Boolean isTrue) {
        logicalConditionStateStack.push(isTrue);
    }
   
    
    public void clearTopIfCondition() {
        logicalConditionStateStack.pop();
    }
    
    public boolean isAllParentIfConditionsAreTrue() {
        Iterator<Boolean> itr = logicalConditionStateStack.iterator();
        while(itr.hasNext()) {
            Boolean top = itr.next();
            if (itr.hasNext()) {
                System.out.println(top);
                if (!top) {
                    return false;
                }
            }
        }
        return true;
    }
    
//    public boolean isSkipExecute() {
//        if (!isSkipExecuteStack.isEmpty()) {
//            return isSkipExecuteStack.pop();
//        } else {
//            return false;
//        }
//    }


}
