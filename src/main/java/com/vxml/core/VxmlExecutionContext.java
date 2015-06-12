package com.vxml.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

import com.vxml.tag.Tag;

public class VxmlExecutionContext {

    private VxmlScriptEngine vxmlScriptEngine;
    // mainly for form when referred from goto
    private Map<String, Tag> tagMap;

    private static Stack<String> isSubdialogStack;

    static final String SUBDIALOG_NAME = ".subdialogName";
    public static IOHandler ioHandler;

    public VxmlExecutionContext() {
        vxmlScriptEngine = new VxmlScriptEngine();
        tagMap = new HashMap<String, Tag>();
        ioHandler = new IOHandler();
        ioHandler.setDtmfInputQueue(new LinkedBlockingDeque<String>());
        ioHandler.setOutputQueue(new LinkedBlockingDeque<OutputWrapper>());

        isSubdialogStack = new Stack<String>();
        isSubdialogStack.push(null);
    }

    public void executeScriptFile(String src) {
        vxmlScriptEngine.executeScriptFile(src);

    }

    public Object executeScript(String script) {
        return vxmlScriptEngine.eval(script);
    }

    public String getDtmlInput() {
        return (String) vxmlScriptEngine.getScriptVar(VxmlScriptEngine.SCRIPT_EXECUTION_NAME_SPACE + ".dtmfInput");
    }

    public String requestDtmfInput() {
        // if (!dtmfInputQueue.isEmpty()) {
        // return dtmfInputQueue.remove();
        // } else {
        return null;
        // }

    }

    public void storeTag(String id, Tag tag) {
        tagMap.put(id, tag);
    }

    public Tag retrieveTag(String id) {
        return tagMap.get(id);
    }

    public void assignVar(String var, Object val) {
        String subdialogName = getCurrentSubdialogName();
        String varNameKey = subdialogName != null ? subdialogName + "." + var : var;
        vxmlScriptEngine.assignScriptVar(varNameKey, val);
    }

    public void assignVarWithoutSubdialog(String var, Object val) {
        vxmlScriptEngine.assignScriptVar(var, val);
    }

    public static void main(String[] args) {
        VxmlExecutionContext t = new VxmlExecutionContext();
        t.executeScriptFile("http://localhost:8585/ivr/common/js/parseXmlWithAttrToObject.js");
        t.executeScript("parseXmlWithAttrToObject('<?xml version=\"1.0\" encoding=\"UTF-8\"?> <?access-control allow=\"*\"?> <response>   <loyaltyTier>SILVER</loyaltyTier> </response> ')");
    }

    public Object getScriptVar(String var) {

        String subdialogName = getCurrentSubdialogName();
        
        String varNameKey = subdialogName != null ? subdialogName + "." + var : var;
        return vxmlScriptEngine.getScriptVar(varNameKey);
    }

    public String getCurrentSubdialogName() {
        return isSubdialogPeek();
    }

    public static void markSubdialog(String subdialogName) {
        isSubdialogStack.push(subdialogName);
    }

    public static void clearTopSubdialogFlag() {
        if (!isSubdialogStack.isEmpty()) {
            isSubdialogStack.pop();
        }
    }

    public static String isSubdialogPeek() {
        return isSubdialogStack.peek();
    }

    public static boolean isInsideSubdialog() {
        return isSubdialogPeek() != null;
    }
    
    public String getScriptVars() {
        return vxmlScriptEngine.scopeVars();
    }

}
