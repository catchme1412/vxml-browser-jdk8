package com.vxml.tag;

import com.vxml.core.OutputType;

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
        Object value = getVxmlExecutor().getScriptVar(expr);
        if (value != null) {
            getVxmlExecutor().recordOutput(OutputType.TTS, value.toString(), isBargeinPeek());
        }
    }

    @Override
    public void endTag() {
       clearTopExecuteFlag();
    }

}
