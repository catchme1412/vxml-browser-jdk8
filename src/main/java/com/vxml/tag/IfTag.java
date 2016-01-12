package com.vxml.tag;


public class IfTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isIfConditionTrue = false;
        Object r = getVxmlExecutor().executeScript(cond);
        isIfConditionTrue = (r instanceof Boolean) ? (Boolean)r : false;
    	ifConditionState(isIfConditionTrue);
        Boolean isExecute = isIfConditionTrue && isExecutePeek();
        isExecute(isExecute);
    }

    @Override
    public void endTag() {
        clearTopIfCondition();
        clearTopExecuteFlag();
    }
}
