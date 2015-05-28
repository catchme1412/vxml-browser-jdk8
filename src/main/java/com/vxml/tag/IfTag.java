package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class IfTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isIfConditionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
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
