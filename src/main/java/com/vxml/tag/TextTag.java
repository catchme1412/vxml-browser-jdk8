package com.vxml.tag;

import com.vxml.core.VxmlExecutionContext;

public class TextTag extends AbstractTag {

    @Override
    public void execute() {
        String text = getNode().getTextContent().trim();
//        System.out.println("TTS:" + text);
        VxmlExecutionContext.ioHandler.recordOutput(text);
    }
}
