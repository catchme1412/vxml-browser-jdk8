package com.vxml.tag;

import com.vxml.core.OutputType;
import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlExecutionContext;

public class AudioTag extends AbstractTag {

    private String src;
    private String expr;
    
    @Override
    public void execute() {
        String audioFile = src != null ? src : (String) VxmlBrowser.getVxmlExecutionContext().executeScript(expr);
        if (audioFile != null) {
            VxmlExecutionContext.ioHandler.recordOutput(OutputType.AUDIO, audioFile);
        }
    }
    
}
