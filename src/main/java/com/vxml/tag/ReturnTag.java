package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ReturnTag extends AbstractTag {

    private String namelist;

    @Override
    public void execute() {
        
        for (String name : namelist.split(" ")) {
            String scriptVar = (String) getVxmlExecutor().getScriptVar(name);
            Object val = getVxmlExecutor().getScriptVar(scriptVar);
            getVxmlExecutor().assignVar(scriptVar, val);
//            if (scriptVar instanceof String) {
//                getVxmlExecutor().assignScriptVar(subDialogVariableName, "'" +scriptVar +"'");
//            } else {
//                getVxmlExecutor().assignScriptVar(subDialogVariableName, scriptVar);
//            }
//            System.err.println("In Return: " + subDialogVariableName + ":" + scriptVar);
        }
//        throw new ReturnFromSubdialogEvent();
    }


}
