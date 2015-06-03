package com.vxml.tag;

import com.vxml.core.OutputType;
import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlExecutionContext;

public class ValueTag extends AbstractTag {

    private String expr;

    @Override
    public void startTag() {
        if ("prompt".equals(getParentTag())) {
            isExecute(true);
        } else {
            isExecute(false);
        }
    }
    
    @Override
    public void execute() {
        Object value = VxmlBrowser.getVxmlExecutionContext().getScriptVar(expr);
        if (value != null) {
            VxmlExecutionContext.ioHandler.recordOutput(OutputType.TTS, value.toString());
        }
    }

    @Override
    public void endTag() {
       clearTopExecuteFlag();
    }

}
