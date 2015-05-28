package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ElseifTag extends LogicalTag {

    private String cond;

    @Override
    public void startTag() {
        Boolean isLogicalBlockExecuted = isExecutePeek();
        Boolean isElseIfConditionTrue = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        Boolean isExecute = !isLogicalBlockExecuted && isElseIfConditionTrue && isAllParentIfConditionsAreTrue();
        
        toggleExecute(isExecute);
    }
}
