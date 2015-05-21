package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class IfTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isIfConditionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        setLogicalBlockExecuted(isIfConditionTrue);
    }

    @Override
    public void endTag() {
        removelogicalBlockStatus();
    }
}
