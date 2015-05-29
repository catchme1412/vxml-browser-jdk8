package com.vxml.tag;

import com.vxml.core.VxmlBrowser;
import com.vxml.core.VxmlExecutionContext;

public class ValueTag extends AbstractTag {

    private String expr;

    @Override
    public void execute() {
        Object value = VxmlBrowser.getVxmlExecutionContext().executeScript(expr);
        VxmlExecutionContext.ioHandler.recordOutput(value.toString());
    }


}
