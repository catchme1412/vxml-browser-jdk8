package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class VarTag extends AbstractTag {

    private String name;
    private String expr;

    @Override
    public void execute() {
        String varName = name;
        Object exprVal = expr;
        VxmlBrowser.getVxmlExecutionContext().assignVar(varName, exprVal);
    }

    private Object getValueFromSubdialogScope() {
        return VxmlBrowser.getVxmlExecutionContext().getScriptVar(name);
    }

}
