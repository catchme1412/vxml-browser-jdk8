package com.vxml.tag;

import com.vxml.core.OutputType;
import com.vxml.core.VxmlExecutionContext;

public class TextTag extends AbstractTag {

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
        String text = getNode().getTextContent().trim();
        // System.out.println("TTS:" + text);
        VxmlExecutionContext.ioHandler.recordOutput(OutputType.TTS, text);
    }
    
    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }
}
