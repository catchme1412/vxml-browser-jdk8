package com.vxml.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.vxml.store.DocumentStore;
import com.vxml.store.VxmlException;

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
    
    public Object evalWithoutException(String script) {
        String scriptForEval = script;
        Object eval = null;
        try {
            if (isJsonFormat(script)) {
                eval = evalJson(scriptForEval);
            } else {
                scriptForEval = scriptForEval.replaceAll("'", "\\'");
                scriptForEval  = scriptForEval + ";";
                eval = scriptEngine.eval(scriptForEval);
            }
        } catch (ScriptException e) {
            //NOP
        }
        return eval;
    }

    public Object eval(String script) {
        String scriptForEval = script;
        Object eval = null;
        try {
            if (isJsonFormat(script)) {
                eval = evalJson(scriptForEval);
            } else {
                scriptForEval = scriptForEval.replaceAll("'", "\\'");
                scriptForEval  = scriptForEval + ";";
                eval = scriptEngine.eval(scriptForEval);
            }
        } catch (ScriptException e) {
            System.err.println("EVALUATION FAIURE : " + scriptForEval + " :: " + e.getMessage());
            throw new VxmlException(e);
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
        Object finalVal = val;
        if (val instanceof String) {
            finalVal = evalWithoutException(String.valueOf(val));
            finalVal = finalVal == null ? val : finalVal;
        }
        scriptEngine.put(var, finalVal);
    }

    public Object getScriptVar(String var) {
        return scriptEngine.get(var);
    }
    
    public Object invokeFunction (String name, Object... args) throws NoSuchMethodException, ScriptException {
        return ((Invocable)scriptEngine).invokeFunction(name, args);
    }
    private void initalizeScriptEngine() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("nashorn");
        scriptEngine = engine;
        scriptEngine.eval(FUNCTION_JSON_PARSE);

        // defaults
        scriptEngine.put("application", new Object());
        scriptEngine.put("application.lastresult$", new Object());
        scriptEngine.put("application.ANI", "197336895001");
        scriptEngine.put("application.UUID" , "684CB6BA3CCC11E4B810B0FAEB421300");
        scriptEngine.put("application.lastresult$.inputmode", "'dtmf'");
//        scriptEngine.put("var session={};session.telephone={};");
//        scriptEngine.put("session.telephone.dnis = 9090900;");
        scriptEngine.eval("var " + SCRIPT_EXECUTION_NAME_SPACE + " = {};");
        scriptEngine.eval("var _event;");
        scriptEngine.eval("var _message;");
    }

    private boolean isJsonFormat(String script) {
        return script.startsWith("{") && script.endsWith("}");
    }

    public Object executeScriptFile(String src) {
//        StringBuilder script = new DocumentStore().getData(src);
        URI fullUrl = DocumentStore.getFullUri(src);
        return eval("load('"+ fullUrl.toString() +"');");
    }
    
    public static String getSubdialogNameKey() {
        return VxmlScriptEngine.SCRIPT_EXECUTION_NAME_SPACE + ".subdialogName";
    }
    
    public String scopeVars() {
        return scriptEngine.getFactory().getNames().toString();
    }
}
