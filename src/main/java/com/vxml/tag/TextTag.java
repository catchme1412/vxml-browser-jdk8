package com.vxml.tag;

import com.vxml.core.OutputType;

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
        System.out.println("TTS:" + text);
        getVxmlExecutor().recordOutput(OutputType.TTS, text, isBargeinPeek());
    }

    @Override
    public void endTag() {
        clearTopExecuteFlag();
    }
}
