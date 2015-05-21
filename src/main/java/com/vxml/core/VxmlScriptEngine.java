package com.vxml.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.vxml.store.DocumentStore;

public class VxmlScriptEngine {
    private static final String FUNCTION_JSON_PARSE = "function simpleJsonParse(j) {"
            + "var ar = j.toString().substring(1,j.length-1);" + "var arr=ar.split(':');" + "var m={};"
            + "m[arr[0]]=m[arr[1]];" + "return m;}";

    public static final String SCRIPT_EXECUTION_NAME_SPACE = "_scriptExecutionNameSpace$";

    private ScriptEngine scriptEngine;

    public VxmlScriptEngine() {
        try {
            initalizeScriptEngine();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public Object eval(String script) {
        Object eval = null;
        try {
            if (isJsonFormat(script)) {
                eval = evalJson(script);
            } else {
                eval = scriptEngine.eval(script);
            }
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return eval;
    }

    public Object evalJson(String script) throws ScriptException {
        return scriptEngine.eval("simpleJsonParse(" + script + ");");
    }

    public Object executeScript(File file) throws FileNotFoundException, ScriptException {
        return scriptEngine.eval(new FileReader(file));
    }

    
    public void assignScriptVar(String var, Object val) {
        val = "true".equals(val) ? true : val; 
        scriptEngine.put(var, val);
    }

    public Object getScriptVar(String var) {
        return scriptEngine.get(var);
    }
    
    private void initalizeScriptEngine() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        scriptEngine = engine;
        scriptEngine.eval(FUNCTION_JSON_PARSE);
    }

    private boolean isJsonFormat(String script) {
        return script.startsWith("{") && script.endsWith("}");
    }

    public Object executeScriptFile(String src) {
        StringBuilder script = new DocumentStore().getData(src);
        return eval(script.toString());
    }
}
