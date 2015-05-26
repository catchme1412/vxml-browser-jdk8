package com.vxml.tag;

public class ElseTag extends LogicalTag {

    @Override
    public void startTag() {
        Boolean ifConditionState = isLogicalBlockExecuted();
    	if (!isSkipExecutePeek() && !ifConditionState) {
    		if (ifConditionState) {
    			toggleSkipExecute(true);
    		} else {
    			toggleSkipExecute(false);
    		}
    		toggleLogicalBlockStatus(!ifConditionState);
    	}
    }
}
