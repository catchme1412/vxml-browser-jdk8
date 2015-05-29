package com.vxml.tag;

import com.vxml.core.VxmlExecutionContext;

public class TextTag extends AbstractTag {

    @Override
    public void startTag() {
        if ("script".equals(getParentTag())) {
            isExecute(false);
        } else {
            isExecute(true);
        }
    }

    @Override
    public void execute() {
        String text = getNode().getTextContent().trim();
        // System.out.println("TTS:" + text);
        VxmlExecutionContext.ioHandler.recordOutput(text);
    }
    
    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }
}
