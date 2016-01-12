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
             exprVal = getVxmlExecutor().getScriptVar(expr);
             exprVal = exprVal != null ? exprVal : getVxmlExecutor().executeScript(expr);
             exprVal = exprVal == null ? expr : exprVal;
        } else {
            exprVal = getVxmlExecutor().getScriptVar(varName);
        }
        getVxmlExecutor().assignVar(varName, exprVal);
    }

}
