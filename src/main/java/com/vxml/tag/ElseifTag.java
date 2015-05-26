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
            toggleSkipExecute(true);
        }
        if (isElseIfCondtionTrue && !isLogicalBlockExecuted && isNoSkip) {
            toggleLogicalBlockStatus(true);
            toggleSkipExecute(false);
        }

    }
}
