package com.vxml.tag;

import com.vxml.core.OutputType;
import com.vxml.core.VxmlExecutionContext;

public class AudioTag extends AbstractTag {

    private String src;
    
    @Override
    public void execute() {
        if (src != null) {
            VxmlExecutionContext.ioHandler.recordOutput(OutputType.AUDIO, src);
        }
    }
    
}
