package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class PromptTag extends AbstractTag {

    private String cond;
    private boolean isSkipBackup;

    @Override
    public void execute() {
        Boolean shouldExecute = true;
        if (cond != null) {
            shouldExecute = (Boolean) VxmlBrowser.getVxmlExecutionContext().executeScript(cond);
        }
        if (!shouldExecute) {
            isSkipBackup = isSkipExecute();
            isSkipExecute(true);
        }
    }
    
    @Override
    public void endTag() {
       isSkipExecute(isSkipBackup);
    }

}
