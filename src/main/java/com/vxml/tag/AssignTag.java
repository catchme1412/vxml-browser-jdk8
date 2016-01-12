package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class AssignTag extends AbstractTag {

    private String name;
    private String expr;

    @Override
    public void startTag() {
//        getVxmlExecutor().assignVar(name, null);
    }

    @Override
    public void execute() {
        Object val = expr;
        Boolean r = (Boolean) getVxmlExecutor().executeScript("'string' === typeof "+ expr);
        if (r != true) {
            val = getVxmlExecutor().getScriptVar(expr);
            if (val == null) {
                val = getVxmlExecutor().executeScript(expr);
            }
        }
        getVxmlExecutor().assignVar(name, val);
    }

}
