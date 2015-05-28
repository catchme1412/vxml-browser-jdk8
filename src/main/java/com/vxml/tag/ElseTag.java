package com.vxml.tag;

public class ElseTag extends LogicalTag {

    @Override
    public void startTag() {
        boolean isLogicalBlockExecuted = isExecutePeek();
        boolean isExecute = !isLogicalBlockExecuted && isAllParentIfConditionsAreTrue();
        if (isExecute) {
            toggleExecute(true);
        } else {
            toggleExecute(false);
        }
    }
}
