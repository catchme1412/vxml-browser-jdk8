package com.vxml.tag;

public class ElseTag extends LogicalTag {

    @Override
    public void startTag() {
        Boolean ifConditionState = isLogicalBlockExecuted();
        toggleLogicalBlockStatus(!ifConditionState);
    }
}
