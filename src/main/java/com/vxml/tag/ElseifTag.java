package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ElseifTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isLogicalBlockExecuted = isLogicalBlockExecuted();
        Boolean isNoSkip = !isSkipExecutePeek();
        Boolean isElseIfCondtionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        if (isLogicalBlockExecuted) {
            return;
        }
        if (isElseIfCondtionTrue && !isLogicalBlockExecuted) {
            toggleLogicalBlockStatus(true);
            toggleSkipExecute(false);
        }

    }
}
