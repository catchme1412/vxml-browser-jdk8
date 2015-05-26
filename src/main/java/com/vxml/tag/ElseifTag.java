package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ElseifTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isLogicalBlockExecuted = isLogicalBlockExecuted();
        Boolean isNoSkip = !isSkipExecutePeek();
        Boolean isElseIfCondtionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        if (isNoSkip && !isLogicalBlockExecuted && isElseIfCondtionTrue) {
            toggleSkipExecute(false);
        }
        if (isNoSkip && isLogicalBlockExecuted) {
            toggleSkipExecute(true);
        }
        if (isNoSkip && !isLogicalBlockExecuted && !isElseIfCondtionTrue) {
            toggleSkipExecute(true);
        }
//        if (!isSkip) {
//            
//        }
//        if (!isLogicalBlockExecuted) {
//            if (!isLogicalBlockExecuted && isElseIfCondtionTrue) {
//                toggleLogicalBlockStatus(true);
//                toggleSkipExecute(false);
//            } else if (isLogicalBlockExecuted) {
//                toggleSkipExecute(true);
//            }
//        }
    }
}
