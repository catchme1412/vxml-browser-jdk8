package com.vxml.tag;

public class ElseTag extends LogicalTag {

    @Override
    public void startTag() {
        Boolean isLogicalBlockExecuted = isLogicalBlockExecuted();
    	if (!isLogicalBlockExecuted && !isSkipExecutePeek()) {
    	    toggleSkipExecute(false);
    	} else {
    	    if (!isLogicalBlockExecuted) {
    	        toggleSkipExecute(false);
    	    } else {
    	        toggleSkipExecute(true);
    	    }
    	}
    }
}
