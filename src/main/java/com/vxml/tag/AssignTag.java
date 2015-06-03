package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class AssignTag extends AbstractTag {

    private String name;
    private String expr;

    @Override
    public void startTag() {
//        VxmlBrowser.getVxmlExecutionContext().assignVar(name, null);
    }

    @Override
    public void execute() {
        Object val = VxmlBrowser.getVxmlExecutionContext().getScriptVar(expr);
        if (val == null) {
            val = VxmlBrowser.getVxmlExecutionContext().getScriptVar(expr);
        }
        VxmlBrowser.getVxmlExecutionContext().assignVar(name, val);
    }

}
