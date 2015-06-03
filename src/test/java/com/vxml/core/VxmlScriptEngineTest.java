package com.vxml.core;

import java.io.File;
import java.io.FileNotFoundException;

import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sun.org.mozilla.javascript.internal.NativeObject;


public class VxmlScriptEngineTest {

    private VxmlScriptEngine vxmlScriptEngine;

    @Before
    public void init() {
        vxmlScriptEngine = new VxmlScriptEngine();
    }

    @Test
    public void testEvalNumber() throws ScriptException {
        Object r = vxmlScriptEngine.eval("1");
        Assert.assertEquals(r, new Double(1));
    }

    @Test
    public void testEvalJson() throws ScriptException {
        Object r = vxmlScriptEngine.eval("{'key':'value'}");
        Assert.assertEquals(r.getClass(), NativeObject.class);
    }

    @Test
    public void testBoolean() throws ScriptException {
        Object r = vxmlScriptEngine.eval("true");
        Assert.assertEquals(r.getClass(), Boolean.class);
    }

    @Test
    public void testBooleanString() throws ScriptException {
        Object r = vxmlScriptEngine.eval("'true'");
        Assert.assertEquals(r.getClass(), String.class);
    }
    
    
    
    @Test
    public void testScriptFile() throws ScriptException, FileNotFoundException {
        System.out.println(new File("").getAbsolutePath());
        Object r = vxmlScriptEngine.executeScript(new File("/opt/orbitz/code/com.vxml.browser/src/main/webapp/js/test.js"));
        r = vxmlScriptEngine.eval("test()");
        Assert.assertEquals(r.toString(), "1.0");
        r = vxmlScriptEngine.executeScript(new File("/opt/orbitz/code/web-ivr/src/main/webapp/ivr/common/js/parseXmlToObject.js"));
        r = vxmlScriptEngine.eval("parseXmlToObject()");
        Assert.assertEquals(r.toString(), "1.0");
        
    }
    
    @Test
    public void testScriptExecuteAndVarAccess() {
        
        vxmlScriptEngine.eval("var a = 1");
        vxmlScriptEngine.eval("var b = a");
        Object r = vxmlScriptEngine.getScriptVar("b");
        Assert.assertEquals(new Double(1d), r);
    }
    
    @Test
    public void testVarWithQuotes() {
        vxmlScriptEngine.eval("var a = '';");
        vxmlScriptEngine.eval("var menuResults = {}");
        vxmlScriptEngine.eval("menuResults.holdMusic = {}");
        vxmlScriptEngine.eval("var menuResults.holdMusic = 'http://audio.en-US.tellme.com/common-audio/intellipause.wav'");
    }
    
    @Test
    public void testVarWithJavascriptExprn() {
        vxmlScriptEngine.eval("function foo(arg){return arg;}");
        vxmlScriptEngine.eval("var r = foo (12);");
        Object r = vxmlScriptEngine.eval("r");
        System.out.println(r);
    }
    
    @Test
    public void testVarWithJavascriptExprnUsingMap() {
        vxmlScriptEngine.eval("function foo(arg){return arg;}");
        vxmlScriptEngine.assignScriptVar("a", "foo(12)");
        Object r = vxmlScriptEngine.eval("a");
        Assert.assertEquals(new Double(12), r);
    }
    

    @Test
    public void testVarWithJavascriptExprnUsingMap2() {
        vxmlScriptEngine.eval("function foo(arg){return arg;}");
        vxmlScriptEngine.assignScriptVar("a", "foo(12)");
        Object r = vxmlScriptEngine.eval("a");
        System.out.println(r);
    }
    
    @Test
    public  void testAssign() {
        vxmlScriptEngine.assignScriptVar("a", 10);
        vxmlScriptEngine.assignScriptVar("b", "a");
        Object r = vxmlScriptEngine.getScriptVar("b");
        System.out.println(r);
    }
    
    @Test
    public  void testExecuteAndGet() {
        vxmlScriptEngine.assignScriptVar("a", "string");
        Object r = vxmlScriptEngine.getScriptVar("a");
    }
    
    @Test
    public  void testExeucuteStringVal() {
        vxmlScriptEngine.assignScriptVar("a", 10);
        vxmlScriptEngine.assignScriptVar("a.b", "20");
        Object r = vxmlScriptEngine.getScriptVar("a.b");
        System.out.println(r);
    }
    
    
}
