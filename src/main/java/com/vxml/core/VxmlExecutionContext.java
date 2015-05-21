package com.vxml.core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

import com.vxml.tag.Tag;

public class VxmlExecutionContext {

    private VxmlScriptEngine vxmlScriptEngine;
    // mainly for form when referred from goto
    private Map<String, Tag> tagMap;
    public static IOHandler ioHandler;

    public VxmlExecutionContext() {
        vxmlScriptEngine = new VxmlScriptEngine();
        tagMap = new HashMap<String, Tag>();
        ioHandler = new IOHandler();
        ioHandler.setDtmfInputQueue(new LinkedBlockingDeque<String>());
        ioHandler.setOutputQueue(new LinkedBlockingDeque<String>());
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

    public void assignVar(String var, String val) {
        vxmlScriptEngine.assignScriptVar(var, val);
        
    }

}
