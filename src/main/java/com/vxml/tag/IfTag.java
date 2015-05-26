package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class IfTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isIfConditionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        if (!isSkipExecutePeek()) {
            setLogicalBlockExecuted(isIfConditionTrue);
            isSkipExecute(!isIfConditionTrue);
        } else {
            setLogicalBlockExecuted(isIfConditionTrue);
            isSkipExecute(true);
        }
    }

    @Override
    public void endTag() {
        if (!isSkipExecutePeek()) {
            clearLogicalBlockStatus();
            clearTopSkipExecuteFlag();
        }
    }
}
