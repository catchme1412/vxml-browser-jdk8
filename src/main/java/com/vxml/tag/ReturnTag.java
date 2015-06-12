package com.vxml.tag;

import com.vxml.core.VxmlBrowser;

public class ReturnTag extends AbstractTag {

    private String namelist;

    @Override
    public void execute() {
        
        for (String name : namelist.split(" ")) {
            String scriptVar = (String) VxmlBrowser.getVxmlExecutionContext().getScriptVar(name);
            System.out.println(scriptVar);
            Object val = VxmlBrowser.getVxmlExecutionContext().getScriptVar(scriptVar);
            VxmlBrowser.getVxmlExecutionContext().assignVar(scriptVar, val);
//            if (scriptVar instanceof String) {
//                VxmlBrowser.getVxmlExecutionContext().assignScriptVar(subDialogVariableName, "'" +scriptVar +"'");
//            } else {
//                VxmlBrowser.getVxmlExecutionContext().assignScriptVar(subDialogVariableName, scriptVar);
//            }
//            System.err.println("In Return: " + subDialogVariableName + ":" + scriptVar);
        }
//        throw new ReturnFromSubdialogEvent();
    }


}
