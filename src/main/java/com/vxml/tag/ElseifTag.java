package com.vxml.tag;


public class ElseifTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isLogicalBlockExecuted = isExecutePeek();
        Boolean isElseIfConditionTrue = (Boolean) getVxmlExecutor().executeScript(cond);
        Boolean isExecute = !isLogicalBlockExecuted && isElseIfConditionTrue && isAllParentIfConditionsAreTrue();
        
        toggleExecute(isExecute);
    }
}
