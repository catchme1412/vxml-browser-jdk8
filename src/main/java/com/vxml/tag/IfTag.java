package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class IfTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isSkip = true;
        Boolean isIfConditionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        if (!isSkipExecutePeek()) {
            isSkip = !isIfConditionTrue;
        }
        setLogicalBlockExecuted(isIfConditionTrue);
        isSkipExecute(isSkip);
    }

    @Override
    public void endTag() {
        clearLogicalBlockStatus();
        clearTopSkipExecuteFlag();
    }
}
