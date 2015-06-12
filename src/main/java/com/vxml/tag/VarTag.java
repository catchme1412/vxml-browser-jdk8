package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class VarTag extends AbstractTag {

    private String name;
    private String expr;

    @Override
    public void execute() {
        String varName = name;
        Object exprVal = expr;
        if (expr != null) {
             exprVal = VxmlBrowser.getVxmlExecutionContext().getScriptVar(expr);
             exprVal = exprVal != null ? exprVal : VxmlBrowser.getVxmlExecutionContext().executeScript(expr);
             exprVal = exprVal == null ? expr : exprVal;
        } else {
            exprVal = VxmlBrowser.getVxmlExecutionContext().getScriptVar(varName);
        }
        VxmlBrowser.getVxmlExecutionContext().assignVar(varName, exprVal);
    }

}
